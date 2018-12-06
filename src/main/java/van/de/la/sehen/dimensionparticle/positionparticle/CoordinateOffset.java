package van.de.la.sehen.dimensionparticle.positionparticle;

public class CoordinateOffset implements OffsetParticle<AbsoluteCoordinate, Integer, CoordinateOffset> {
    private Integer value;

    public CoordinateOffset(Integer value) {
        this.value = value;
    }

    @Override
    public CoordinateOffset add(CoordinateOffset arg) {
        return new CoordinateOffset(value + arg.value);
    }

    public Integer getValue() {
        return value;
    }

    @Override
    public CoordinateOffset subtract(CoordinateOffset arg) {
        return new CoordinateOffset(value - arg.value);
    }

    @Override
    public AbsoluteCoordinate addToAbsolute(AbsoluteCoordinate arg) {
        return new AbsoluteCoordinate(value + arg.getValue());
    }

    public static CoordinateOffset zero() {
        return new CoordinateOffset(0);
    }
}
