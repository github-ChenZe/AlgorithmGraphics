package van.de.la.sehen.dimensionparticle.positionparticle;
import javax.annotation.CheckReturnValue;

@CheckReturnValue
public interface OffsetParticle<T extends AbsoluteParticle<T, V>, V, ThisT extends AdditiveParticle<ThisT>> extends AdditiveParticle<ThisT> {
    T addToAbsolute(T arg);
}
