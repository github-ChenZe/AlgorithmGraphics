package van.de.la.sehen.dimensionparticle.sizeparticle;

import van.de.la.sehen.dimensionparticle.positionparticle.CoordinateOffset;

public class IntDimensionComponent implements DimensionComponent<Integer, CoordinateOffset> {
    private Integer value;

    public IntDimensionComponent(Integer value) {
        this.value = value;
    }

    @Override
    public Integer getValue() {
        return value;
    }

    @Override
    public CoordinateOffset toOffset() {
        return new CoordinateOffset(value);
    }

    @Override
    public CoordinateOffset toOffset(double scale) {
        return new CoordinateOffset((int) (value * scale));
    }

    @Override
    public CoordinateOffset toOffset(Integer offBy) {
        return new CoordinateOffset(value + offBy);
    }

    @Override
    public CoordinateOffset infimum() {
        return new CoordinateOffset(0);
    }

    @Override
    public CoordinateOffset supremum() {
        return new CoordinateOffset(value - 1);
    }

    public static IntDimensionComponent zero() {
        return new IntDimensionComponent(0);
    }
}
