package van.de.la.sehen.dimensionparticle.positionparticle;

import math.geom2d.Vector2D;

public class PositionOffset extends IntVector2D implements OffsetParticle<AbsolutePosition, Vector2D, PositionOffset> {
    public PositionOffset() {
        this(0, 0);
    }

    public PositionOffset(Integer x, Integer y) {
        super(x, y);
    }

    public PositionOffset(CoordinateOffset offsetX, CoordinateOffset offsetY) {
        this(offsetX.getValue(), offsetY.getValue());
    }

    @Override
    public PositionOffset add(PositionOffset arg) {
        return new PositionOffset(this.getIntX() + arg.getIntX(), this.getIntY() + arg.getIntY());
    }

    @Override
    public PositionOffset subtract(PositionOffset arg) {
        return new PositionOffset(this.getIntX() - arg.getIntX(), this.getIntY() - arg.getIntY());
    }

    @Override
    public AbsolutePosition addToAbsolute(AbsolutePosition arg) {
        return new AbsolutePosition(arg.getIntX() + this.getIntX(), arg.getIntY() + this.getIntY());
    }

    public static PositionOffset zero() {
        return new PositionOffset();
    }
}
