package van.de.la.sehen.xmldiagramtree;

import van.de.la.sehen.diagram.readerinterface.attributereading.DiagramAttributeCollectionBuilder;

public class CSSDiagramStyleList {
    private String key;
    private DiagramAttributeCollectionBuilder attributes = new DiagramAttributeCollectionBuilder();

    public CSSDiagramStyleList(String key) {
        this.key = key;
    }

    public DiagramAttributeCollectionBuilder getAttributes() {
        return attributes;
    }

    public String getKey() {
        return key;
    }

    public void put(String key, String value) {
        attributes.putAttribute(key, van.de.la.sehen.diagram.prototypediagram.Diagram.attributeToObject(key, value).toCluster());
    }

    @Override
    public String toString() {
        return this.getClass() + "  " + key + ": " + attributes.toString();
    }
}
