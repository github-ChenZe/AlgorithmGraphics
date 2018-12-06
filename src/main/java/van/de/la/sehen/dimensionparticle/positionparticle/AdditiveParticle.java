package van.de.la.sehen.dimensionparticle.positionparticle;
import javax.annotation.CheckReturnValue;

@CheckReturnValue
public interface AdditiveParticle<T extends AdditiveParticle<T>> {
    T add(T arg);
    T subtract(T arg);
}
