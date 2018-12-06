package van.de.la.sehen.diagram.pseudodiagram;

import van.de.la.sehen.diagram.prototypediagram.AbstractDiagram;
import van.de.la.sehen.diagramstyle.DiagramStyle;
import van.de.la.sehen.dimensionparticle.positionparticle.AbsoluteCoordinate;
import van.de.la.sehen.dimensionparticle.positionparticle.CoordinateOffset;
import van.de.la.sehen.warning.WarningStream;

public class FixedPseudoDiagram extends PseudoDiagram {
    private double x;
    private double y;

    public FixedPseudoDiagram(AbstractDiagram parent, DiagramStyle style, double x, double y) {
        super(parent, style);
        this.x = x;
        this.y = y;
    }

    @Override
    public CoordinateOffset getX() {
        return new CoordinateOffset((int) x);
    }

    @Override
    public CoordinateOffset getY() {
        return new CoordinateOffset((int) y);
    }
}
