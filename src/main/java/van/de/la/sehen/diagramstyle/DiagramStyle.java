package van.de.la.sehen.diagramstyle;

import org.w3c.dom.NamedNodeMap;
import van.de.la.sehen.diagram.prototypediagram.Diagram;
import van.de.la.sehen.diagram.prototypediagram.model.StyleModel;
import van.de.la.sehen.diagram.readerinterface.attributereading.DiagramAttributeCluster;
import van.de.la.sehen.diagram.readerinterface.attributereading.DiagramAttributeDoubleParticle;
import van.de.la.sehen.diagram.readerinterface.attributereading.DiagramAttributeSingleCluster;
import van.de.la.sehen.diagram.readerinterface.attributereading.DiagramAttributesCollection;
import van.de.la.sehen.warning.WarningStream;

import java.util.HashMap;
import java.util.Map;

public class DiagramStyle {
    private HashMap<String, DiagramAttributeCluster> map = new HashMap<>();

    public DiagramStyle() { }

    @Deprecated
    public DiagramStyle(NamedNodeMap map) {
        WarningStream.putWarning("Invoking a invalid constructor no longer supported.", this);
    }

    @Deprecated
    public DiagramStyle(Map<String, String> attributeMap) {
        WarningStream.putWarning("Invoking a invalid constructor no longer supported.", this);
    }

    public DiagramStyle(DiagramAttributesCollection attributesCollection) {
        Map<String, DiagramAttributeCluster> attributeMap = attributesCollection == null ? new HashMap<>() : attributesCollection.getAttributes();
        for (Map.Entry<String, DiagramAttributeCluster> e: attributeMap.entrySet()) {
            map.put(e.getKey(), e.getValue());
        }
    }

    public DiagramStyle putStyle(String key, DiagramAttributeCluster value) {
        if (!Diagram.isAttributeKey(key)) {
            WarningStream.putWarning("Invalid attribute name in putStyle '" + key + "'.", this);
        }
        map.put(key, value);
        return this;
    }

    public DiagramAttributeCluster getStyle(String key) {
        return map.get(key);
    }

    public static DiagramStyle emptyStyle() {
        return new DiagramStyle();
    }
}
