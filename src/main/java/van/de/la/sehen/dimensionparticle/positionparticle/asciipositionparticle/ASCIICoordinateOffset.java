package van.de.la.sehen.dimensionparticle.positionparticle.asciipositionparticle;

import van.de.la.sehen.dimensionparticle.positionparticle.OffsetParticle;

public class ASCIICoordinateOffset implements OffsetParticle<ASCIIAbsoluteCoordinate, Integer, ASCIICoordinateOffset> {
    private Integer value;

    public Integer getValue() {
        return value;
    }

    public ASCIICoordinateOffset(Integer value) {
        this.value = value;
    }

    @Override
    public ASCIICoordinateOffset add(ASCIICoordinateOffset arg) {
        return new ASCIICoordinateOffset(value + arg.value);
    }

    @Override
    public ASCIICoordinateOffset subtract(ASCIICoordinateOffset arg) {
        return new ASCIICoordinateOffset(value - arg.value);
    }

    @Override
    public ASCIIAbsoluteCoordinate addToAbsolute(ASCIIAbsoluteCoordinate arg) {
        return new ASCIIAbsoluteCoordinate(value + arg.getValue());
    }


    public static ASCIICoordinateOffset zero() {
        return new ASCIICoordinateOffset(0);
    }
}
