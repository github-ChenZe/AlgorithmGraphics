package van.de.la.sehen.diagram.displayeddiagram;

import van.de.la.sehen.diagram.displayeddiagram.model.RootDiagramModel;
import van.de.la.sehen.diagram.prototypediagram.CompositeDiagram;
import van.de.la.sehen.diagram.prototypediagram.Diagram;
import van.de.la.sehen.diagram.prototypediagram.model.DiagramCSSModel;
import van.de.la.sehen.diagram.readerinterface.DiagramNode;
import van.de.la.sehen.diagram.readerinterface.diagrambuilder.DiagramBuilder;
import van.de.la.sehen.diagram.readerinterface.fieldreading.DiagramFieldsCollection;
import van.de.la.sehen.diagramimage.PortableDiagramCanvas;
import van.de.la.sehen.diagramstyle.DiagramStyle;
import van.de.la.sehen.dimensionparticle.positionparticle.PositionOffset;
import van.de.la.sehen.warning.WarningStream;
import van.de.la.sehen.xmldiagramtree.CSSClassDiagramStyleList;
import van.de.la.sehen.xmldiagramtree.CSSDiagramStyleList;
import van.de.la.sehen.xmldiagramtree.CSSIdDiagramStyleList;
import van.de.la.sehen.xmldiagramtree.CSSTagDiagramStyleList;

import java.util.HashMap;
import java.util.Map;

public class RootDiagram extends CompositeDiagram implements RootDiagramModel<Diagram> {
    private Diagram onlyElement;
    private Map<String, Diagram> idTable;
    private Map<String, DiagramStyle> idToStyle;
    private Map<String, DiagramStyle> classToStyle;
    private Map<String, DiagramStyle> tagToStyle;

    public RootDiagram(DiagramBuilder<? extends Diagram> builder, DiagramFieldsCollection fields) {
        super(null, fields);
        idTable = new HashMap<>();
        idToStyle = new HashMap<>();
        classToStyle = new HashMap<>();
        tagToStyle = new HashMap<>();
        onlyElement = builder.buildDiagram(this); // children.get(0).buildDiagram(this);
    }

    public RootDiagram(DiagramNode node) {
        this(node, node);
    }

    @Override
    public Map<String, Diagram> getIdTable() {
        return idTable;
    }

    @Override
    public Map<String, DiagramStyle> getIdToStyle() {
        return idToStyle;
    }

    @Override
    public Map<String, DiagramStyle> getClassToStyle() {
        return classToStyle;
    }

    @Override
    public Map<String, DiagramStyle> getTagToStyle() {
        return tagToStyle;
    }

    public void putId(String id, Diagram diagram) {
        RootDiagramModel.super.putId(id, diagram);
    }

    public boolean hasIdInStyle(String id) {
        return RootDiagramModel.super.hasIdInStyle(id);
    }

    public DiagramStyle getStyleById(String id) {
        return RootDiagramModel.super.getStyleById(id);
    }

    public boolean hasClassInStyle(String className) {
        return RootDiagramModel.super.hasClassInStyle(className);
    }

    public DiagramStyle getStyleByClass(String className) {
        return RootDiagramModel.super.getStyleByClass(className);
    }

    public boolean hasTagInStyle(String tag) {
        return RootDiagramModel.super.hasTagInStyle(tag);
    }

    public DiagramStyle getStyleByTag(String tag) {
        return RootDiagramModel.super.getStyleByTag(tag);
    }


    @Override
    public Diagram getChildByIndex(int i) {
        if (i != 0) WarningStream.putWarning("Getting index > 0 from derived van.de.la.sehen.diagram.", this);
        return onlyElement;
    }

    @Override
    public void layoutChildren() {
        onlyElement.layout();
    }

    @Override
    public void setChildrenPosition() {
        onlyElement.setPosition(PositionOffset.zero());
    }

    @Override
    public void paintDiagram(PortableDiagramCanvas canvas) {
        onlyElement.paintDiagram(canvas);
    }

    @Override
    public void calculateSize() {
        setWidth(onlyElement.getWidth());
        setHeight(onlyElement.getHeight());
    }
}
