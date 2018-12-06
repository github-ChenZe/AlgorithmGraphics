package van.de.la.sehen.diagram.readerinterface.fieldreading;

import van.de.la.sehen.diagram.readerinterface.attributereading.DiagramAttributeCluster;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DiagramFieldsCollectionBuilder implements DiagramFieldsCollection {
    /* metadata */

    private List<String> classes;
    private String id;

    public DiagramFieldsCollectionBuilder addClass(String classname) {
        this.classes.add(classname);
        return this;
    }

    public DiagramFieldsCollectionBuilder setId(String id) {
        this.id = id;
        return this;
    }

    @Override
    public Iterable<String> getClasses() {
        return classes;
    }

    @Override
    public String getId() {
        return id;
    }

    /* attributes */

    private Map<String, DiagramAttributeCluster> attributes;

    @Override
    public Map<String, DiagramAttributeCluster> getAttributes() {
        return this.attributes;
    }

    public DiagramFieldsCollectionBuilder putAttribute(String key, DiagramAttributeCluster cluster) {
        attributes.put(key, cluster);
        return this;
    }

    public DiagramFieldsCollectionBuilder() {
        this.attributes = new HashMap<>();
    }
}
