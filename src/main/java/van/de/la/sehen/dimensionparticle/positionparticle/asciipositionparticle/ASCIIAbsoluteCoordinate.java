package van.de.la.sehen.dimensionparticle.positionparticle.asciipositionparticle;

import van.de.la.sehen.dimensionparticle.positionparticle.AbsoluteParticle;
import van.de.la.sehen.dimensionparticle.positionparticle.OffsetParticle;

public class ASCIIAbsoluteCoordinate implements AbsoluteParticle<ASCIIAbsoluteCoordinate, Integer> {
    private Integer value;

    public ASCIIAbsoluteCoordinate(Integer value) {
        this.value = value;
    }

    @Override
    public <S extends OffsetParticle<ASCIIAbsoluteCoordinate, Integer, S>> ASCIIAbsoluteCoordinate addByOffset(S offset) {
        return offset.addToAbsolute(this);
    }

    @Override
    public Integer getValue() {
        return value;
    }
    public static ASCIIAbsoluteCoordinate zero() {
        return new ASCIIAbsoluteCoordinate(0);
    }

}
