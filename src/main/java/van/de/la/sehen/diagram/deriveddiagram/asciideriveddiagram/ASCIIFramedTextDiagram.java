package van.de.la.sehen.diagram.deriveddiagram.asciideriveddiagram;

import van.de.la.sehen.diagram.displayeddiagram.asciidisplayeddiagram.ASCIITableDiagram;
import van.de.la.sehen.diagram.prototypediagram.asciiprototypediagram.ASCIIDiagram;
import van.de.la.sehen.diagram.readerinterface.asciireaderinterface.ASCIIDiagramNode;
import van.de.la.sehen.diagram.readerinterface.diagrambuilder.asciidiagrambuilder.ASCIIDiagramBuilder;
import van.de.la.sehen.diagram.readerinterface.diagrambuilder.instancebuilder.asciidiagrambuilder.ASCIICellDiagramBuilder;
import van.de.la.sehen.diagram.readerinterface.diagrambuilder.instancebuilder.asciidiagrambuilder.ASCIITableDiagramBuilder;
import van.de.la.sehen.diagram.readerinterface.elementreading.DiagramElementCollectionBuilder;
import van.de.la.sehen.diagram.readerinterface.elementreading.DiagramElementsCollection;
import van.de.la.sehen.diagram.readerinterface.fieldreading.DiagramFieldsCollectionBuilder;
import van.de.la.sehen.diagramstyle.DiagramStyle;
import van.de.la.sehen.warning.WarningStream;

public class ASCIIFramedTextDiagram extends ASCIIDerivedDiagram<ASCIITableDiagram> {
    public ASCIIFramedTextDiagram(ASCIIDiagram parent, DiagramStyle style) {
        super(parent, style);
        WarningStream.putWarning("Call to a invalid constructor in FramedText.", this);
    }

    public ASCIIFramedTextDiagram(ASCIIDiagram parent, ASCIIDiagramNode node) {
        super(parent, node);
        setSkeleton(new ASCIITableDiagramBuilder<>()
                .setFields(
                        new DiagramFieldsCollectionBuilder()
                        .putAttribute(LINE_COLOR, (INHERIT).toCluster())
                        .putAttribute(LINE_THICKNESS, (INHERIT).toCluster())
                        .putAttribute(FONT_NAME, (INHERIT).toCluster())
                        .putAttribute(FONT_COLOR, (INHERIT).toCluster())
                        .putAttribute(FONT_SIZE, (INHERIT).toCluster())
                        .putAttribute(PADDING, (INHERIT).toCluster()))
                .setElements(new DiagramElementCollectionBuilder<DiagramElementsCollection<ASCIIDiagramBuilder>>()
                    .addChild(new DiagramElementCollectionBuilder<ASCIIDiagramBuilder>().addChild(
                            new ASCIICellDiagramBuilder<>().setElements(node)
                                    .setFields(new DiagramFieldsCollectionBuilder()
                                .putAttribute(FONT_NAME, (INHERIT).toCluster())
                                .putAttribute(FONT_COLOR, (INHERIT).toCluster())
                                .putAttribute(FONT_SIZE, (INHERIT).toCluster())
                                .putAttribute(PADDING, (INHERIT).toCluster()))))).buildDiagram(this));
    }
}
