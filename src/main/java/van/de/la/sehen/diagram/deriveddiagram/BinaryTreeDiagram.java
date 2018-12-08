package van.de.la.sehen.diagram.deriveddiagram;

import van.de.la.sehen.diagram.displayeddiagram.ArrowDiagram;
import van.de.la.sehen.diagram.displayeddiagram.PaneDiagram;
import van.de.la.sehen.diagram.prototypediagram.Diagram;
import van.de.la.sehen.diagram.readerinterface.DiagramNode;
import van.de.la.sehen.diagram.readerinterface.attributereading.DiagramAttributeParticle;
import van.de.la.sehen.diagram.readerinterface.diagrambuilder.DiagramBuilder;
import van.de.la.sehen.diagram.readerinterface.diagrambuilder.WrappedDiagramBuilder;
import van.de.la.sehen.diagram.readerinterface.diagrambuilder.instancebuilder.ArrowDiagramBuilder;
import van.de.la.sehen.diagram.readerinterface.diagrambuilder.instancebuilder.PaneDiagramBuilder;
import van.de.la.sehen.diagram.readerinterface.diagrambuilder.instancebuilder.TableDiagramBuilder;
import van.de.la.sehen.diagram.readerinterface.diagrambuilder.instancebuilder.VerticalAlignDiagramBuilder;
import van.de.la.sehen.diagram.readerinterface.elementreading.DiagramElementCollectionBuilder;
import van.de.la.sehen.diagram.readerinterface.elementreading.DiagramElementsCollection;
import van.de.la.sehen.diagram.readerinterface.fieldreading.DiagramFieldsCollectionBuilder;
import van.de.la.sehen.diagramattributeparticle.diagramparticle.DiagramLambdaPointer;
import van.de.la.sehen.diagramattributeparticle.diagramparticle.PseudoDiagramAccessor;
import van.de.la.sehen.diagramattributeparticle.enumeratedparticle.BooleanStyle;
import van.de.la.sehen.diagramattributeparticle.enumeratedparticle.VerticalAlign;
import van.de.la.sehen.diagramstyle.DiagramStyle;
import van.de.la.sehen.warning.WarningStream;

import java.util.ArrayList;
import java.util.Queue;

public class BinaryTreeDiagram extends DerivedDiagram<PaneDiagram> {
    private Diagram root;
    private ArrayList<Diagram> children = new ArrayList<>();
    private ArrayList<ArrowDiagram> arrows = new ArrayList<>();

    class DelayedScaleParticle implements DiagramAttributeParticle<Double> {
        private int index;

        public DelayedScaleParticle(int index) {
            this.index = index;
        }

        @Override
        public Double get() {
            if (!getBranchEmerge()) return 1.0;
            int targetIndex = getBranchEmergeIndex();
            if (targetIndex != index) return 1.0;
            double progress = getBranchEmergeProgress();
            return getBranchEmergeReverse() ? (1 - progress): progress;
        }
    }

    class DelayedBranchSeparationParticle implements DiagramAttributeParticle<Integer> {
        @Override
        public Integer get() {
            if (!getBranchEmerge()) return getBranchSeparation();
            return (int)(getBranchEmergeProgress() * getBranchSeparation());
        }
    }

    @Override
    public DiagramAttributeParticle defaultAttribute(String key) {
        if (key.equals(TREE_ROOT_ARROW_PERCH)) return new DiagramLambdaPointer<>(() -> root);
        return super.defaultAttribute(key);
    }

    public BinaryTreeDiagram(Diagram parent, DiagramStyle style) {
        super(parent, style);
    }

    public <T extends DiagramNode> BinaryTreeDiagram(Diagram parent, DiagramNode<T, Diagram> node) {
        super(parent, node);

        if (!node.hasChildren()) {
            WarningStream.putWarning("Empty children when building BinaryTreeDiagram.", this);
            return;
        }

        DiagramNode<?, ?> rootNode = node.getChildren().poll();

        DiagramBuilder<Diagram> rootBuilder = new WrappedDiagramBuilder(rootNode)
                .setFields(new DiagramFieldsCollectionBuilder()
                        .putAttribute(VERTICAL_ALIGN_ANCHOR, getWrapper(TREE_ROOT_ANCHOR)))
                .setAfterBuilt(diagram -> root = diagram);

        if (node.childrenCount() == 0) { // root only
            DiagramBuilder<?> paneBuilder = new PaneDiagramBuilder<>().setElements(
                    new DiagramElementCollectionBuilder<DiagramBuilder>().addChild(rootBuilder));
            setSkeleton((PaneDiagram) paneBuilder.buildDiagram(this));
            return;
        }

        Queue<T> children = node.getChildren();

        DiagramElementCollectionBuilder<DiagramBuilder> inPaneCollection = new DiagramElementCollectionBuilder<>();

        DiagramElementCollectionBuilder<WrappedDiagramBuilder> generalChildrenCollection = new DiagramElementCollectionBuilder<>();

        if (children.size() == 1) {
            insertChildAndArrow(node.getChildren().poll(), inPaneCollection, generalChildrenCollection, 0.5);
        } else if (children.size() == 2) {
            insertChildAndArrow(node.getChildren().poll(), inPaneCollection, generalChildrenCollection, 0);
            insertChildAndArrow(node.getChildren().poll(), inPaneCollection, generalChildrenCollection, 1);
        } else {
            WarningStream.putWarning("BinaryTree could have no more than 2 children, while this node has " + children.size() + ".", this);
            return;
        }

        DiagramElementsCollection<DiagramBuilder> branchBuilders =
                new DiagramElementCollectionBuilder<DiagramBuilder>().addFrom(generalChildrenCollection.getChildren());
        DiagramElementCollectionBuilder<DiagramElementsCollection<DiagramBuilder>> branchRow =
                new DiagramElementCollectionBuilder<DiagramElementsCollection<DiagramBuilder>>().addChild(branchBuilders);
        DiagramBuilder branchTableBuilder = new TableDiagramBuilder<>().setElements(branchRow)
                .setFields(new DiagramFieldsCollectionBuilder()
                        .putAttribute(SEPARATION, new DelayedBranchSeparationParticle().toCluster())
                        .putAttribute(GRID, (BooleanStyle.FALSE).toCluster())
                        .putAttribute(VERTICAL_ALIGN, (VerticalAlign.TOP).toCluster())
                        .putAttribute(VERTICAL_ALIGN_ANCHOR, getWrapper(TREE_BRANCH_ANCHOR)));
        DiagramElementCollectionBuilder<DiagramBuilder> rowCollectionBuilder =
                new DiagramElementCollectionBuilder<DiagramBuilder>()
                        .addChild(rootBuilder);
        rowCollectionBuilder.addChild(branchTableBuilder);
        DiagramBuilder alignBuilder = new VerticalAlignDiagramBuilder<>().setElements(rowCollectionBuilder)
                .setFields(new DiagramFieldsCollectionBuilder().putAttribute(SEPARATION, getWrapper(ROOT_SEPARATION)));
        DiagramBuilder<PaneDiagram> paneBuilder = new PaneDiagramBuilder<>().setElements(
                inPaneCollection.addChild(alignBuilder));
        setSkeleton(paneBuilder.buildDiagram(this));
    }

    private void insertChildAndArrow(
            DiagramNode<?, ?> childNode,
            DiagramElementCollectionBuilder<DiagramBuilder> inPaneCollection,
            DiagramElementCollectionBuilder<WrappedDiagramBuilder> generalChildrenCollection,
            double positionX) {
        generalChildrenCollection.addChild(
                new WrappedDiagramBuilder(childNode)
                        .setAfterBuilt(
                                diagram -> {
                                    inPaneCollection.getChildren().add(
                                            new ArrowDiagramBuilder<>()
                                                    .setFields(new DiagramFieldsCollectionBuilder()
                                                            .putAttribute(FROM,
                                                                    (new PseudoDiagramAccessor(getTreeRootArrowPerch(),
                                                                            getPseudoPostfix(positionX, 1))).toCluster())
                                                            .putAttribute(TO, diagram instanceof BinaryTreeDiagram ?
                                                                    (new PseudoDiagramAccessor(diagram.getTreeRootArrowPerch(), "N").toCluster()) :
                                                                    (new PseudoDiagramAccessor(diagram, "N")).toCluster())
                                                            // (int) positionX gets 0 if positionX is 0.0 or 0.5, or 1 if positionX is 1.0
                                                            .putAttribute(SCALE_FACTOR, new DelayedScaleParticle((int) positionX).toCluster()))
                                                    .setAfterBuilt(arrowDiagram -> {
                                                        arrows.add((ArrowDiagram) arrowDiagram);
                                                    })
                                    );
                                    children.add(diagram);
                                }
                        )
        );
    }
}
