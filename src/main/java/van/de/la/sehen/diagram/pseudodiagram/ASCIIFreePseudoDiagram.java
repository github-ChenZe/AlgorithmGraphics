package van.de.la.sehen.diagram.pseudodiagram;

import van.de.la.sehen.diagram.prototypediagram.asciiprototypediagram.ASCIIAbstractDiagram;
import van.de.la.sehen.diagramstyle.DiagramStyle;
import van.de.la.sehen.dimensionparticle.positionparticle.asciipositionparticle.ASCIICoordinateOffset;

public class ASCIIFreePseudoDiagram extends ASCIIPseudoDiagram {
    private double xRatio;
    private double yRatio;

    public ASCIIFreePseudoDiagram(double xRatio, double yRatio, ASCIIAbstractDiagram parent, DiagramStyle style) {
        super(parent, style);
        this.xRatio = xRatio;
        this.yRatio = yRatio;
    }

    @Override
    public ASCIICoordinateOffset getX() {
        return getParent().getWidth().toOffset(xRatio);
    }

    @Override
    public ASCIICoordinateOffset getY() {
        return getParent().getHeight().toOffset(yRatio);
    }
}
