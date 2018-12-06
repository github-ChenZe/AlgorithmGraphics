package van.de.la.sehen.dimensionparticle.sizeparticle;

public class IntDimensionTuple implements DimensionTuple<IntDimensionComponent> {
    private IntDimensionComponent width;
    private IntDimensionComponent height;

    public IntDimensionTuple(IntDimensionComponent width, IntDimensionComponent height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public IntDimensionComponent getWidth() {
        return width;
    }

    @Override
    public IntDimensionComponent getHeight() {
        return height;
    }
}
