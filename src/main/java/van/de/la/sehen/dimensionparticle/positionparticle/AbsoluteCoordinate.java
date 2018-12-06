package van.de.la.sehen.dimensionparticle.positionparticle;

public class AbsoluteCoordinate implements AbsoluteParticle<AbsoluteCoordinate, Integer> {
    private Integer value;

    public AbsoluteCoordinate(Integer value) {
        this.value = value;
    }

    @Override
    public <S extends OffsetParticle<AbsoluteCoordinate, Integer, S>> AbsoluteCoordinate addByOffset(S offset) {
        return offset.addToAbsolute(this);
    }

    @Override
    public Integer getValue() {
        return value;
    }

    public static AbsoluteCoordinate zero() {
        return new AbsoluteCoordinate(0);
    }
}
