package van.de.la.sehen.diagram.deriveddiagram.asciideriveddiagram;

import van.de.la.sehen.diagram.displayeddiagram.asciidisplayeddiagram.ASCIIEmptyDiagram;
import van.de.la.sehen.diagram.displayeddiagram.asciidisplayeddiagram.ASCIIPaneDiagram;
import van.de.la.sehen.diagram.prototypediagram.asciiprototypediagram.ASCIIDiagram;
import van.de.la.sehen.diagram.readerinterface.asciireaderinterface.ASCIIDiagramNode;
import van.de.la.sehen.diagram.readerinterface.diagrambuilder.asciidiagrambuilder.ASCIIDiagramBuilder;
import van.de.la.sehen.diagram.readerinterface.diagrambuilder.asciidiagrambuilder.ASCIIWrappedDiagramBuilder;
import van.de.la.sehen.diagram.readerinterface.diagrambuilder.instancebuilder.asciidiagrambuilder.ASCIIArrowDiagramBuilder;
import van.de.la.sehen.diagram.readerinterface.diagrambuilder.instancebuilder.asciidiagrambuilder.ASCIIPaneDiagramBuilder;
import van.de.la.sehen.diagram.readerinterface.diagrambuilder.instancebuilder.asciidiagrambuilder.ASCIITableDiagramBuilder;
import van.de.la.sehen.diagram.readerinterface.diagrambuilder.instancebuilder.asciidiagrambuilder.ASCIIVerticalAlignDiagramBuilder;
import van.de.la.sehen.diagram.readerinterface.elementreading.DiagramElementCollectionBuilder;
import van.de.la.sehen.diagram.readerinterface.elementreading.DiagramElementsCollection;
import van.de.la.sehen.diagram.readerinterface.fieldreading.DiagramFieldsCollectionBuilder;
import van.de.la.sehen.diagramattributeparticle.diagramparticle.asciidiagramparticle.ASCIIPseudoDiagramAccessor;
import van.de.la.sehen.diagramattributeparticle.enumeratedparticle.BooleanStyle;
import van.de.la.sehen.diagramattributeparticle.enumeratedparticle.VerticalAlign;
import van.de.la.sehen.diagramstyle.DiagramStyle;
import van.de.la.sehen.warning.WarningStream;

import java.util.Queue;

public class ASCIITreeDiagram extends ASCIIDerivedDiagram<ASCIIPaneDiagram> {
    public ASCIITreeDiagram(ASCIIDiagram parent, DiagramStyle style) {
        super(parent, style);
    }

    public <T extends ASCIIDiagramNode> ASCIITreeDiagram(ASCIIDiagram parent, ASCIIDiagramNode<T, ASCIIDiagram> node) {
        super(parent, node);

        if (!node.hasChildren()) {
            WarningStream.putWarning("Empty children when building TreeDiagram.", this);
            return;
        }

        ASCIIDiagramNode rootNode = node.getChildren().poll();

        Queue<T> children = node.getChildren();

        int denominator = children.size() - 1;

        DiagramElementCollectionBuilder<ASCIIDiagramBuilder> inPaneCollection = new DiagramElementCollectionBuilder<>();

        DiagramElementCollectionBuilder<ASCIIWrappedDiagramBuilder> generalChildrenCollection = new DiagramElementCollectionBuilder<>();
        int i = 0;
        while (node.hasChildren()) {
            i++;
            final int j = i;
            generalChildrenCollection.addChild(new ASCIIWrappedDiagramBuilder(node.getChildren().poll())
            .setAfterBuilt(diagram -> {
                if (!(diagram instanceof ASCIIEmptyDiagram))
                    inPaneCollection.getChildren().add(
                            new ASCIIArrowDiagramBuilder()
                    .setFields(new DiagramFieldsCollectionBuilder()
                    .putAttribute(FROM,
                            (
                            new ASCIIPseudoDiagramAccessor(getTreeRootArrowPerch(),
                            getPseudoPostfix(denominator != 0 ? (double)(j - 1) / denominator : (j - 1), 1))).toCluster())
                    .putAttribute(TO, diagram instanceof ASCIITreeDiagram ?
                            (new ASCIIPseudoDiagramAccessor(diagram.getTreeRootArrowPerch(), "N").toCluster()):
                            (new ASCIIPseudoDiagramAccessor(diagram, "N")).toCluster()))
                    );
            }));
        }

        //TODO: fix the bug here: rowTable still built even empty
        ASCIIDiagramBuilder<ASCIIDiagram> rootBuilder = new ASCIIWrappedDiagramBuilder(rootNode)
                .setFields(new DiagramFieldsCollectionBuilder()
                        .putAttribute(VERTICAL_ALIGN_ANCHOR, getWrapper(TREE_ROOT_ANCHOR)));
        DiagramElementsCollection<ASCIIDiagramBuilder> branchBuilders =
                new DiagramElementCollectionBuilder<ASCIIDiagramBuilder>().addFrom(generalChildrenCollection.getChildren());
        DiagramElementCollectionBuilder<DiagramElementsCollection<ASCIIDiagramBuilder>> branchRow =
                new DiagramElementCollectionBuilder<DiagramElementsCollection<ASCIIDiagramBuilder>>().addChild(branchBuilders);
        ASCIIDiagramBuilder branchTableBuilder = new ASCIITableDiagramBuilder<>().setElements(branchRow)
                .setFields(new DiagramFieldsCollectionBuilder()
                .putAttribute(SEPARATION, getWrapper(BRANCH_SEPARATION))
                .putAttribute(GRID, (BooleanStyle.FALSE).toCluster())
                .putAttribute(VERTICAL_ALIGN, (VerticalAlign.TOP).toCluster())
                .putAttribute(VERTICAL_ALIGN_ANCHOR, getWrapper(TREE_BRANCH_ANCHOR)));
        DiagramElementCollectionBuilder<ASCIIDiagramBuilder> rowCollectionBuilder =
                new DiagramElementCollectionBuilder<ASCIIDiagramBuilder>()
                .addChild(rootBuilder);
        if (denominator > 0) rowCollectionBuilder.addChild(branchTableBuilder);
        ASCIIDiagramBuilder alignBuilder= new ASCIIVerticalAlignDiagramBuilder<>().setElements(rowCollectionBuilder)
                .setFields(new DiagramFieldsCollectionBuilder().putAttribute(SEPARATION, getWrapper(ROOT_SEPARATION)));
        ASCIIDiagramBuilder paneBuilder = new ASCIIPaneDiagramBuilder<>().setElements(
                inPaneCollection.addChild(alignBuilder));
        setSkeleton((ASCIIPaneDiagram) paneBuilder.buildDiagram(this));
    }
}
