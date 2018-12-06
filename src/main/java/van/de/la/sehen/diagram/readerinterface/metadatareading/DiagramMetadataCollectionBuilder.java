package van.de.la.sehen.diagram.readerinterface.metadatareading;

import java.util.List;

public class DiagramMetadataCollectionBuilder implements DiagramMetadataCollection {
    private List<String> classes;
    private String id;

    public DiagramMetadataCollectionBuilder addClass(String classname) {
        this.classes.add(classname);
        return this;
    }

    public DiagramMetadataCollectionBuilder setId(String id) {
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
}
