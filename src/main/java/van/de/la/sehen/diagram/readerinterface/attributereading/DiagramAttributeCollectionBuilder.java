package van.de.la.sehen.diagram.readerinterface.attributereading;

import java.util.HashMap;
import java.util.Map;

public class DiagramAttributeCollectionBuilder implements DiagramAttributesCollection {
    private Map<String, DiagramAttributeCluster> attributes;

    @Override
    public Map<String, DiagramAttributeCluster> getAttributes() {
        return this.attributes;
    }

    public DiagramAttributeCollectionBuilder putAttribute(String key, DiagramAttributeCluster cluster) {
        attributes.put(key, cluster);
        return this;
    }

    public DiagramAttributeCollectionBuilder() {
        this.attributes = new HashMap<>();
    }
}
