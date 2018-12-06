package van.de.la.sehen.dimensionparticle.positionparticle;

import math.geom2d.Vector2D;

public class AbsolutePosition extends IntVector2D implements AbsoluteTuple<AbsolutePosition, Vector2D, AbsoluteCoordinate> {
    public AbsolutePosition() {
        this(0, 0);
    }

    public AbsolutePosition(int x, int y) {
        super(x, y);
    }

    public AbsoluteCoordinate getXCoordinate() {
        return new AbsoluteCoordinate(getIntX());
    }

    public AbsoluteCoordinate getYCoordinate() {
        return new AbsoluteCoordinate(getIntY());
    }

    @Override
    public AbsoluteCoordinate getXComponent() {
        return getXCoordinate();
    }

    @Override
    public AbsoluteCoordinate getYComponent() {
        return getYCoordinate();
    }

    @Override
    public <S extends OffsetParticle<AbsolutePosition, Vector2D, S>> AbsolutePosition addByOffset(S offset) {
        return offset.addToAbsolute(this);
    }

    @Override
    public Vector2D getValue() {
        return this;
    }

    public static AbsolutePosition zero() {
        return new AbsolutePosition();
    }
}
