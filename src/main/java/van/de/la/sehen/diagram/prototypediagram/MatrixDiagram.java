package van.de.la.sehen.diagram.prototypediagram;

import van.de.la.sehen.diagram.readerinterface.DiagramNode;
import van.de.la.sehen.diagram.readerinterface.fieldreading.DiagramFieldsCollection;
import van.de.la.sehen.diagramstyle.DiagramStyle;

public abstract class MatrixDiagram extends CompositeDiagram {
    public MatrixDiagram(Diagram parent, DiagramStyle style) {
        super(parent, style);
    }
    public MatrixDiagram(Diagram parent, DiagramFieldsCollection node) {
        super(parent, node);
    }
    public MatrixDiagram(Diagram parent, DiagramNode node) {
        super(parent, node);
    }
}
