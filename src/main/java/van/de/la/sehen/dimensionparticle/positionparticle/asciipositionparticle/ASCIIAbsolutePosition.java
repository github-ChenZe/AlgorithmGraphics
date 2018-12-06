package van.de.la.sehen.dimensionparticle.positionparticle.asciipositionparticle;

import van.de.la.sehen.dimensionparticle.positionparticle.AbsoluteTuple;
import van.de.la.sehen.dimensionparticle.positionparticle.IntVector2D;
import van.de.la.sehen.dimensionparticle.positionparticle.OffsetParticle;

public class ASCIIAbsolutePosition extends IntVector2D implements AbsoluteTuple<ASCIIAbsolutePosition, IntVector2D, ASCIIAbsoluteCoordinate> {
    public ASCIIAbsolutePosition() {
    }

    public ASCIIAbsolutePosition(int x, int y) {
        super(x, y);
    }

    public ASCIIAbsoluteCoordinate getXCoordinate() {
        return new ASCIIAbsoluteCoordinate(getIntX());
    }

    public ASCIIAbsoluteCoordinate getYCoordinate() {
        return new ASCIIAbsoluteCoordinate(getIntY());
    }

    @Override
    public ASCIIAbsoluteCoordinate getXComponent() {
        return getXCoordinate();
    }

    @Override
    public ASCIIAbsoluteCoordinate getYComponent() {
        return getYCoordinate();
    }

    @Override
    public <S extends OffsetParticle<ASCIIAbsolutePosition, IntVector2D, S>> ASCIIAbsolutePosition addByOffset(S offset) {
        return offset.addToAbsolute(this);
    }

    @Override
    public IntVector2D getValue() {
        return this;
    }

    public static ASCIIAbsolutePosition zero() {
        return new ASCIIAbsolutePosition(0, 0);
    }
}
