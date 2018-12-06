package van.de.la.sehen.diagram.displayeddiagram;

import van.de.la.sehen.diagram.prototypediagram.Diagram;
import van.de.la.sehen.diagram.readerinterface.DiagramNode;
import van.de.la.sehen.diagram.readerinterface.fieldreading.DiagramFieldsCollection;
import van.de.la.sehen.diagramimage.PortableDiagramCanvas;
import van.de.la.sehen.diagramstyle.DiagramStyle;
import van.de.la.sehen.dimensionparticle.sizeparticle.IntDimensionComponent;

public class EmptyDiagram extends Diagram {
    public EmptyDiagram(Diagram parent, DiagramStyle style) {
        super(parent, style);
    }

    public EmptyDiagram(Diagram parent, DiagramNode node) {
        super(parent, node);
    }

    public EmptyDiagram(Diagram parent, DiagramFieldsCollection fields) {
        super(parent, fields);
    }

    @Override
    public void paintDiagram(PortableDiagramCanvas canvas) {

    }

    @Override
    public void calculateSize() {
        setWidth(IntDimensionComponent.zero());
        setHeight(IntDimensionComponent.zero());
    }
}
