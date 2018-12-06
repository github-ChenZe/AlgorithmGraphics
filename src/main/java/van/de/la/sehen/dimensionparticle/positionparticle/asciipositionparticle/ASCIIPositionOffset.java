package van.de.la.sehen.dimensionparticle.positionparticle.asciipositionparticle;

import van.de.la.sehen.dimensionparticle.positionparticle.IntVector2D;
import van.de.la.sehen.dimensionparticle.positionparticle.OffsetParticle;

public class ASCIIPositionOffset extends IntVector2D implements OffsetParticle<ASCIIAbsolutePosition, IntVector2D, ASCIIPositionOffset> {
    public ASCIIPositionOffset() {
    }

    public ASCIIPositionOffset(Integer x, Integer y) {
        super(x, y);
    }

    public ASCIIPositionOffset(ASCIICoordinateOffset offsetX, ASCIICoordinateOffset offsetY) {
        this(offsetX.getValue(), offsetY.getValue());
    }

    @Override
    public ASCIIPositionOffset add(ASCIIPositionOffset arg) {
        return new ASCIIPositionOffset(this.getIntX() + arg.getIntX(), this.getIntY() + arg.getIntY());
    }

    @Override
    public ASCIIPositionOffset subtract(ASCIIPositionOffset arg) {
        return new ASCIIPositionOffset(this.getIntX() - arg.getIntX(), this.getIntY() - arg.getIntY());
    }

    @Override
    public ASCIIAbsolutePosition addToAbsolute(ASCIIAbsolutePosition arg) {
        return new ASCIIAbsolutePosition(arg.getIntX() + this.getIntX(), arg.getIntY() + this.getIntY());
    }

    public static ASCIIPositionOffset zero() {
        return new ASCIIPositionOffset(0, 0);
    }
}
