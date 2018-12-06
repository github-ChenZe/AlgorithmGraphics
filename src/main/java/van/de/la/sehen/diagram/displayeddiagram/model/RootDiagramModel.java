package van.de.la.sehen.diagram.displayeddiagram.model;

import van.de.la.sehen.diagram.prototypediagram.Diagram;
import van.de.la.sehen.diagram.prototypediagram.model.DiagramCSSModel;
import van.de.la.sehen.diagramstyle.DiagramStyle;
import van.de.la.sehen.warning.WarningStream;
import van.de.la.sehen.warning.WarningType;
import van.de.la.sehen.xmldiagramtree.CSSClassDiagramStyleList;
import van.de.la.sehen.xmldiagramtree.CSSDiagramStyleList;
import van.de.la.sehen.xmldiagramtree.CSSIdDiagramStyleList;
import van.de.la.sehen.xmldiagramtree.CSSTagDiagramStyleList;

import java.util.Map;

public interface RootDiagramModel<DiagramT> extends DiagramCSSModel {
    Map<String, DiagramT> getIdTable();
    Map<String, DiagramStyle> getIdToStyle();
    Map<String, DiagramStyle> getClassToStyle();
    Map<String, DiagramStyle> getTagToStyle();

    default void pushStyle(CSSClassDiagramStyleList list) {
        getClassToStyle().put(list.getKey(), new DiagramStyle(list.getAttributes()));
    }

    default void pushStyle(CSSIdDiagramStyleList list) {
        getIdToStyle().put(list.getKey(), new DiagramStyle(list.getAttributes()));
    }

    default void pushStyle(CSSTagDiagramStyleList list) {
        getTagToStyle().put(list.getKey(), new DiagramStyle(list.getAttributes()));
    }

    default void pushStyle(CSSDiagramStyleList list) {
        if (list instanceof CSSClassDiagramStyleList) {
            pushStyle((CSSClassDiagramStyleList) list);
        } else if (list instanceof CSSIdDiagramStyleList) {
            pushStyle((CSSIdDiagramStyleList) list);
        } else if (list instanceof CSSTagDiagramStyleList) {
            pushStyle((CSSTagDiagramStyleList) list);
        } else {
            WarningStream.putWarning("Unknown StyleList type.", this);
        }
    }

    default void putId(String id, DiagramT diagram) {
        getIdTable().put(id, diagram);
    }

    default DiagramT getElementById(String id) {
        if (!getIdTable().containsKey(id)) {
            WarningStream.putWarning("Id '" + id + "' " + "not found.", this);
        }
        return getIdTable().get(id);
    }

    default boolean hasIdInStyle(String id) {
        return getIdToStyle().containsKey(id);
    }

    default DiagramStyle getStyleById(String id) {
        if (!getIdToStyle().containsKey(id)) {
            WarningStream.putWarning("No such id in CSS sheet: '" + id + "'.", this);
            return null;
        }
        return getIdToStyle().get(id);
    }

    default boolean hasClassInStyle(String className) {
        return getClassToStyle().containsKey(className);
    }

    default DiagramStyle getStyleByClass(String className) {
        if (!getClassToStyle().containsKey(className)) {
            WarningStream.putWarning("No such class in CSS sheet: '" + className + "'.", this);
            return null;
        }
        return getClassToStyle().get(className);
    }

    default boolean hasTagInStyle(String tag) {
        return getTagToStyle().containsKey(tag);
    }

    default DiagramStyle getStyleByTag(String tag) {
        if (!getTagToStyle().containsKey(tag)) {
            WarningStream.putWarning("No such tag in CSS sheet: '" + tag + "'.", this);
            return null;
        }
        return getTagToStyle().get(tag);
    }
}
