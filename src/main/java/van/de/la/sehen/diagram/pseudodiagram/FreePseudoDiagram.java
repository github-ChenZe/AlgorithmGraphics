package van.de.la.sehen.diagram.pseudodiagram;

import van.de.la.sehen.diagram.prototypediagram.AbstractDiagram;
import van.de.la.sehen.diagramstyle.DiagramStyle;
import van.de.la.sehen.dimensionparticle.positionparticle.CoordinateOffset;

public class FreePseudoDiagram extends PseudoDiagram {
    private double xRatio;
    private double yRatio;

    public FreePseudoDiagram(double xRatio, double yRatio, AbstractDiagram parent, DiagramStyle style) {
        super(parent, style);
        this.xRatio = xRatio;
        this.yRatio = yRatio;
    }

    @Override
    public CoordinateOffset getX() {
        return getParent().getWidth().toOffset(xRatio);
    }

    @Override
    public CoordinateOffset getY() {
        return getParent().getHeight().toOffset(yRatio);
    }
}
