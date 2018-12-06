package van.de.la.sehen.diagram.readerinterface.attributereading;

import van.de.la.sehen.diagram.prototypediagram.Diagram;
import van.de.la.sehen.warning.WarningStream;

import java.util.Map;

public interface DiagramAttributesCollection {
    Map<String, DiagramAttributeCluster> getAttributes();

    default DiagramAttributeCluster clusterFromString(String key, String value) {
        DiagramAttributeParticle particle = Diagram.attributeToObject(key, value);
        if (particle == null) {
            WarningStream.putWarning("Method clusterFromString produced null particle.", this);
            return null;
        } else {
            return particle.toCluster();
        }
    }
}
