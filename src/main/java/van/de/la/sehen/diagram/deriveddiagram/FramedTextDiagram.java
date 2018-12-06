package van.de.la.sehen.diagram.deriveddiagram;

import van.de.la.sehen.diagram.displayeddiagram.TableDiagram;
import van.de.la.sehen.diagram.prototypediagram.Diagram;
import van.de.la.sehen.diagram.readerinterface.DiagramNode;
import van.de.la.sehen.diagram.readerinterface.diagrambuilder.DiagramBuilder;
import van.de.la.sehen.diagram.readerinterface.diagrambuilder.instancebuilder.CellDiagramBuilder;
import van.de.la.sehen.diagram.readerinterface.diagrambuilder.instancebuilder.TableDiagramBuilder;
import van.de.la.sehen.diagram.readerinterface.elementreading.DiagramElementCollectionBuilder;
import van.de.la.sehen.diagram.readerinterface.elementreading.DiagramElementsCollection;
import van.de.la.sehen.diagram.readerinterface.fieldreading.DiagramFieldsCollectionBuilder;
import van.de.la.sehen.diagramstyle.DiagramStyle;
import van.de.la.sehen.warning.WarningStream;

public class FramedTextDiagram extends DerivedDiagram<TableDiagram> {
    public FramedTextDiagram(Diagram parent, DiagramStyle style) {
        super(parent, style);
        WarningStream.putWarning("Call to a invalid constructor in FramedText.", this);
    }

    public FramedTextDiagram(Diagram parent, DiagramNode node) {
        super(parent, node);
        setSkeleton(new TableDiagramBuilder<>()
                .setFields(
                        new DiagramFieldsCollectionBuilder()
                        .putAttribute(LINE_COLOR, (INHERIT).toCluster())
                        .putAttribute(LINE_THICKNESS, (INHERIT).toCluster())
                        .putAttribute(FONT_NAME, (INHERIT).toCluster())
                        .putAttribute(FONT_COLOR, (INHERIT).toCluster())
                        .putAttribute(FONT_SIZE, (INHERIT).toCluster())
                        .putAttribute(PADDING, (INHERIT).toCluster()))
                .setElements(new DiagramElementCollectionBuilder<DiagramElementsCollection<DiagramBuilder>>()
                    .addChild(new DiagramElementCollectionBuilder<DiagramBuilder>().addChild(
                            new CellDiagramBuilder<>().setElements(node)
                                    .setFields(new DiagramFieldsCollectionBuilder()
                                .putAttribute(FONT_NAME, (INHERIT).toCluster())
                                .putAttribute(FONT_COLOR, (INHERIT).toCluster())
                                .putAttribute(FONT_SIZE, (INHERIT).toCluster())
                                .putAttribute(PADDING, (INHERIT).toCluster()))))).buildDiagram(this));
    }
}
