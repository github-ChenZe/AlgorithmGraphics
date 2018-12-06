package van.de.la.sehen.dimensionparticle.sizeparticle;

public interface DimensionTuple<ComponentT extends DimensionComponent<?, ?>> {
    ComponentT getWidth();
    ComponentT getHeight();
}
