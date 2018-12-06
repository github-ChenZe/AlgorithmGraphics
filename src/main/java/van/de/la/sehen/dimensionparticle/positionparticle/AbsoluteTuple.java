package van.de.la.sehen.dimensionparticle.positionparticle;
import javax.annotation.CheckReturnValue;

@CheckReturnValue
public interface AbsoluteTuple<ThisT extends AbsoluteTuple<ThisT, V, ComponentT>, V, ComponentT extends AbsoluteParticle<ComponentT, ?>> extends AbsoluteParticle<ThisT, V> {
    ComponentT getXComponent();
    ComponentT getYComponent();
}
