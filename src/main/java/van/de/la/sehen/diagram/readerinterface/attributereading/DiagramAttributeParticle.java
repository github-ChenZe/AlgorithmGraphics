package van.de.la.sehen.diagram.readerinterface.attributereading;

import java.util.function.Supplier;

public interface DiagramAttributeParticle<T> extends Supplier<T> {
    default DiagramAttributeCluster toCluster() {
        return new DiagramAttributeSingleCluster(this);
    }
}
