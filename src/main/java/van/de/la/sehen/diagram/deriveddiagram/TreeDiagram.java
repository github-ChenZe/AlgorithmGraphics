package van.de.la.sehen.diagram.deriveddiagram;

import van.de.la.sehen.diagram.displayeddiagram.EmptyDiagram;
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

import java.util.Queue;

public class TreeDiagram extends DerivedDiagram<PaneDiagram> {
    private Diagram root;

    public TreeDiagram(Diagram parent, DiagramStyle style) {
        super(parent, style);
    }

    @Override
    public DiagramAttributeParticle defaultAttribute(String key) {
        if (key.equals(TREE_ROOT_ARROW_PERCH)) return new DiagramLambdaPointer<>(() -> root);
        return super.defaultAttribute(key);
    }

    public <T extends DiagramNode> TreeDiagram(Diagram parent, DiagramNode<T, Diagram> node) {
        super(parent, node);

        if (!node.hasChildren()) {
            WarningStream.putWarning("Empty children when building TreeDiagram.", this);
            return;
        }

        DiagramNode rootNode = node.getChildren().poll();

        Queue<T> children = node.getChildren();

        int denominator = children.size() - 1;

        DiagramElementCollectionBuilder<DiagramBuilder> inPaneCollection = new DiagramElementCollectionBuilder<>();

        DiagramElementCollectionBuilder<WrappedDiagramBuilder> generalChildrenCollection = new DiagramElementCollectionBuilder<>();
        int i = 0;
        while (node.hasChildren()) {
            i++;
            final int j = i;
            generalChildrenCollection.addChild(new WrappedDiagramBuilder(node.getChildren().poll())
            .setAfterBuilt(diagram -> {
                if (!(diagram instanceof EmptyDiagram))
                    inPaneCollection.getChildren().add(
                            new ArrowDiagramBuilder()
                    .setFields(new DiagramFieldsCollectionBuilder()
                    .putAttribute(FROM,
                            (
                            new PseudoDiagramAccessor(getTreeRootArrowPerch(),
                            getPseudoPostfix(denominator != 0 ? (double)(j - 1) / denominator : (j - 1), 1))).toCluster())
                    .putAttribute(TO, diagram instanceof TreeDiagram ?
                            (new PseudoDiagramAccessor(diagram.getTreeRootArrowPerch(), "N").toCluster()):
                            (new PseudoDiagramAccessor(diagram, "N")).toCluster()))
                    );
            }));
        }

        //TODO: fix the bug here: rowTable still built even empty
        DiagramBuilder<Diagram> rootBuilder = new WrappedDiagramBuilder(rootNode)
                .setFields(new DiagramFieldsCollectionBuilder()
                        .putAttribute(VERTICAL_ALIGN_ANCHOR, getWrapper(TREE_ROOT_ANCHOR)))
                .setAfterBuilt(diagram -> root = diagram);
        DiagramElementsCollection<DiagramBuilder> branchBuilders =
                new DiagramElementCollectionBuilder<DiagramBuilder>().addFrom(generalChildrenCollection.getChildren());
        DiagramElementCollectionBuilder<DiagramElementsCollection<DiagramBuilder>> branchRow =
                new DiagramElementCollectionBuilder<DiagramElementsCollection<DiagramBuilder>>().addChild(branchBuilders);
        DiagramBuilder branchTableBuilder = new TableDiagramBuilder<>().setElements(branchRow)
                .setFields(new DiagramFieldsCollectionBuilder()
                .putAttribute(SEPARATION, getWrapper(BRANCH_SEPARATION))
                .putAttribute(GRID, (BooleanStyle.FALSE).toCluster())
                .putAttribute(VERTICAL_ALIGN, (VerticalAlign.TOP).toCluster())
                .putAttribute(VERTICAL_ALIGN_ANCHOR, getWrapper(TREE_BRANCH_ANCHOR)));
        DiagramElementCollectionBuilder<DiagramBuilder> rowCollectionBuilder =
                new DiagramElementCollectionBuilder<DiagramBuilder>()
                .addChild(rootBuilder);
        if (denominator > 0) rowCollectionBuilder.addChild(branchTableBuilder);
        DiagramBuilder alignBuilder= new VerticalAlignDiagramBuilder<>().setElements(rowCollectionBuilder)
                .setFields(new DiagramFieldsCollectionBuilder().putAttribute(SEPARATION, getWrapper(ROOT_SEPARATION)));
        DiagramBuilder paneBuilder = new PaneDiagramBuilder<>().setElements(
                inPaneCollection.addChild(alignBuilder));
        setSkeleton((PaneDiagram) paneBuilder.buildDiagram(this));
    }
}
