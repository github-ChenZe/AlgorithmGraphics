package van.de.la.sehen.diagram.displayeddiagram.asciidisplayeddiagram;

import van.de.la.sehen.diagram.prototypediagram.asciiprototypediagram.ASCIIDiagram;
import van.de.la.sehen.diagram.readerinterface.DiagramNode;
import van.de.la.sehen.diagram.readerinterface.fieldreading.DiagramFieldsCollection;
import van.de.la.sehen.diagramimage.asciidiagramimage.ASCIICanvas;
import van.de.la.sehen.diagramstyle.DiagramStyle;
import van.de.la.sehen.dimensionparticle.sizeparticle.asciisizeparticle.ASCIIIntDimensionComponent;

public class ASCIIEmptyDiagram extends ASCIIDiagram {
    public ASCIIEmptyDiagram(ASCIIDiagram parent, DiagramStyle style) {
        super(parent, style);
    }

    public ASCIIEmptyDiagram(ASCIIDiagram parent, DiagramNode node) {
        super(parent, node);
    }

    public ASCIIEmptyDiagram(ASCIIDiagram parent, DiagramFieldsCollection fields) {
        super(parent, fields);
    }

    @Override
    public void paintDiagram(ASCIICanvas canvas) {

    }

    @Override
    public void calculateSize() {
        setWidth(ASCIIIntDimensionComponent.zero());
        setHeight(ASCIIIntDimensionComponent.zero());
    }
}
