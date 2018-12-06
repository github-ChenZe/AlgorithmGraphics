package van.de.la.sehen.dimensionparticle.positionparticle;
import javax.annotation.CheckReturnValue;

@CheckReturnValue
public interface AbsoluteParticle<T extends AbsoluteParticle<T, V>, V> {
    <S extends OffsetParticle<T, V, S>> T addByOffset(S offset);
    V getValue();
}
