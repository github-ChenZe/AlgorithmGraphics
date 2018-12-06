package van.de.la.sehen.diagram.pseudodiagram;

import van.de.la.sehen.diagram.prototypediagram.asciiprototypediagram.ASCIIAbstractDiagram;
import van.de.la.sehen.diagramstyle.DiagramStyle;
import van.de.la.sehen.dimensionparticle.positionparticle.asciipositionparticle.ASCIICoordinateOffset;
import van.de.la.sehen.warning.WarningStream;

public class ASCIIFixedPseudoDiagram extends ASCIIPseudoDiagram {
    private double x;
    private double y;

    public ASCIIFixedPseudoDiagram(ASCIIAbstractDiagram parent, DiagramStyle style, double x, double y) {
        super(parent, style);
        this.x = x;
        this.y = y;
    }

    @Override
    public ASCIICoordinateOffset getX() {
        return new ASCIICoordinateOffset((int) x);
    }

    @Override
    public ASCIICoordinateOffset getY() {
        return new ASCIICoordinateOffset((int) y);
    }
}
