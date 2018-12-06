package van.de.la.sehen.diagram.readerinterface.attributereading;

public class DiagramAttributeSingleCluster extends DiagramAttributeCluster {
    public DiagramAttributeSingleCluster(DiagramAttributeParticle particle) {
        add(particle);
    }

    public DiagramAttributeParticle getUnique() {
        return get(0);
    }
}
