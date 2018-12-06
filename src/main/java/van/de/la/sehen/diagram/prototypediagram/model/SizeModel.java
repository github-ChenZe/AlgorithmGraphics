package van.de.la.sehen.diagram.prototypediagram.model;

import van.de.la.sehen.dimensionparticle.positionparticle.OffsetParticle;
import van.de.la.sehen.dimensionparticle.sizeparticle.DimensionComponent;

public interface SizeModel<
        ComponentT extends DimensionComponent<?, OffsetT>,
        OffsetT extends OffsetParticle<?, ?, OffsetT>
        > {
    ComponentT getWidth();
    void setWidth(ComponentT width);
    ComponentT getHeight();
    void setHeight(ComponentT height);

    default OffsetT minX() {
        return getWidth().infimum();
    }

    default OffsetT midX() {
        return getWidth().midOffset();
    }

    default OffsetT maxX() {
        return getWidth().supremum();
    }

    default OffsetT minY() {
        return getHeight().infimum();
    }

    default OffsetT midY() {
        return getHeight().midOffset();
    }

    default OffsetT maxY() {
        return getHeight().supremum();
    }
}
