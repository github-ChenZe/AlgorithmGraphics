package van.de.la.sehen.dimensionparticle.sizeparticle.asciisizeparticle;

import van.de.la.sehen.dimensionparticle.positionparticle.asciipositionparticle.ASCIICoordinateOffset;
import van.de.la.sehen.dimensionparticle.sizeparticle.DimensionComponent;

public class ASCIIIntDimensionComponent implements DimensionComponent<Integer, ASCIICoordinateOffset> {
    private Integer value;

    public ASCIIIntDimensionComponent(Integer value) {
        this.value = value;
    }

    @Override
    public Integer getValue() {
        return value;
    }

    @Override
    public ASCIICoordinateOffset toOffset() {
        return new ASCIICoordinateOffset(value);
    }

    @Override
    public ASCIICoordinateOffset toOffset(double scale) {
        return new ASCIICoordinateOffset((int) ((value > 0 ? value - 1 : 0) * scale));
    }

    @Override
    public ASCIICoordinateOffset toOffset(Integer offBy) {
        return new ASCIICoordinateOffset(value + offBy);
    }

    @Override
    public ASCIICoordinateOffset infimum() {
        return new ASCIICoordinateOffset(0);
    }

    @Override
    public ASCIICoordinateOffset supremum() {
        return new ASCIICoordinateOffset(value - 1);
    }

    public static ASCIIIntDimensionComponent zero() {
        return new ASCIIIntDimensionComponent(0);
    }
}
