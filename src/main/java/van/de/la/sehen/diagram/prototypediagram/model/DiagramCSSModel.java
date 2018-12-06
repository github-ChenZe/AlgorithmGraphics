package van.de.la.sehen.diagram.prototypediagram.model;

import van.de.la.sehen.diagram.prototypediagram.Diagram;
import van.de.la.sehen.diagramstyle.DiagramStyle;
import van.de.la.sehen.xmldiagramtree.CSSClassDiagramStyleList;
import van.de.la.sehen.xmldiagramtree.CSSDiagramStyleList;
import van.de.la.sehen.xmldiagramtree.CSSIdDiagramStyleList;
import van.de.la.sehen.xmldiagramtree.CSSTagDiagramStyleList;

public interface DiagramCSSModel {
    boolean hasIdInStyle(String id);

    DiagramStyle getStyleById(String id);

    boolean hasClassInStyle(String className);

    DiagramStyle getStyleByClass(String className);
}
