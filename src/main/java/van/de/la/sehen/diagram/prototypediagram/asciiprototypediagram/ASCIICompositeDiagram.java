package van.de.la.sehen.diagram.prototypediagram.asciiprototypediagram;

import van.de.la.sehen.diagram.prototypediagram.model.CompositeDiagramModel;
import van.de.la.sehen.diagram.readerinterface.DiagramNode;
import van.de.la.sehen.diagram.readerinterface.fieldreading.DiagramFieldsCollection;
import van.de.la.sehen.diagramstyle.DiagramStyle;

public abstract class ASCIICompositeDiagram extends ASCIIDiagram implements CompositeDiagramModel<ASCIIDiagram> {
    public ASCIICompositeDiagram(ASCIIDiagram parent, DiagramStyle style) {
        super(parent, style);
    }
    public ASCIICompositeDiagram(ASCIIDiagram parent, DiagramFieldsCollection node) {
        super(parent, node);
    }
    public ASCIICompositeDiagram(ASCIIDiagram parent, DiagramNode node) {
        super(parent, node);
    }

    @Override
    public void layout() {
        CompositeDiagramModel.super.layout();
    }
}
