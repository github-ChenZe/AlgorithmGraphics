package van.de.la.sehen.diagram.readerinterface.attributereading;

import van.de.la.sehen.warning.WarningStream;

import java.util.ArrayList;

public abstract class DiagramAttributeCluster extends ArrayList<DiagramAttributeParticle> {
    public DiagramAttributeCluster() { }

    public DiagramAttributeCluster(DiagramAttributeParticle particle) {
        add(particle);
    }

    @Override
    public String toString() {
        WarningStream.putWarning("Using toString on Cluster.", DiagramAttributeCluster.class);
        return get(0).toString();
    }
}

