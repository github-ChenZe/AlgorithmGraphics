package van.de.la.sehen.diagram.prototypediagram;

import van.de.la.sehen.diagram.prototypediagram.model.CompositeDiagramModel;
import van.de.la.sehen.diagram.readerinterface.DiagramNode;
import van.de.la.sehen.diagram.readerinterface.fieldreading.DiagramFieldsCollection;
import van.de.la.sehen.diagramstyle.DiagramStyle;

public abstract class CompositeDiagram extends Diagram implements CompositeDiagramModel<Diagram> {
    public CompositeDiagram(Diagram parent, DiagramStyle style) {
        super(parent, style);
    }
    public CompositeDiagram(Diagram parent, DiagramFieldsCollection node) {
        super(parent, node);
    }
    public CompositeDiagram(Diagram parent, DiagramNode node) {
        super(parent, node);
    }

    @Override
    public void layout() {
        CompositeDiagramModel.super.layout();
    }
}
