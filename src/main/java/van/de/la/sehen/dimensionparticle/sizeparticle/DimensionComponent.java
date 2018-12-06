package van.de.la.sehen.dimensionparticle.sizeparticle;

import van.de.la.sehen.dimensionparticle.positionparticle.OffsetParticle;

public interface DimensionComponent<V, OffsetT extends OffsetParticle<?, ?, OffsetT>> {
    V getValue();
    OffsetT toOffset();
    OffsetT toOffset(double scale);
    OffsetT toOffset(V offBy);
    default OffsetT midOffset() {
        return toOffset(0.5);
    }
    OffsetT infimum();
    OffsetT supremum();
}
