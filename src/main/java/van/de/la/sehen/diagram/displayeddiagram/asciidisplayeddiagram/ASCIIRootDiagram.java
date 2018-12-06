package van.de.la.sehen.diagram.displayeddiagram.asciidisplayeddiagram;

import van.de.la.sehen.diagram.displayeddiagram.RootDiagram;
import van.de.la.sehen.diagram.displayeddiagram.model.RootDiagramModel;
import van.de.la.sehen.diagram.prototypediagram.asciiprototypediagram.ASCIICompositeDiagram;
import van.de.la.sehen.diagram.prototypediagram.asciiprototypediagram.ASCIIDiagram;
import van.de.la.sehen.diagram.readerinterface.asciireaderinterface.ASCIIDiagramNode;
import van.de.la.sehen.diagram.readerinterface.diagrambuilder.asciidiagrambuilder.ASCIIDiagramBuilder;
import van.de.la.sehen.diagram.readerinterface.fieldreading.DiagramFieldsCollection;
import van.de.la.sehen.diagramimage.asciidiagramimage.ASCIICanvas;
import van.de.la.sehen.diagramstyle.DiagramStyle;
import van.de.la.sehen.dimensionparticle.positionparticle.asciipositionparticle.ASCIIPositionOffset;
import van.de.la.sehen.warning.WarningStream;
import van.de.la.sehen.xmldiagramtree.CSSClassDiagramStyleList;
import van.de.la.sehen.xmldiagramtree.CSSDiagramStyleList;
import van.de.la.sehen.xmldiagramtree.CSSIdDiagramStyleList;

import java.util.HashMap;
import java.util.Map;

public class ASCIIRootDiagram extends ASCIICompositeDiagram implements RootDiagramModel<ASCIIDiagram> {
    private ASCIIDiagram onlyElement;
    private Map<String, ASCIIDiagram> idTable;
    private Map<String, DiagramStyle> idToStyle;
    private Map<String, DiagramStyle> classToStyle;
    private Map<String, DiagramStyle> tagToStyle;

    public ASCIIRootDiagram(ASCIIDiagramBuilder<? extends ASCIIDiagram> builder, DiagramFieldsCollection fields) {
        super(null, fields);
        // List<XMLDiagramTreeNode> children = node.getChildren();
        // if (children.size() > 1) WarningStream.putWarning("More than one child in root.", this);
        idTable = new HashMap<>();
        idToStyle = new HashMap<>();
        classToStyle = new HashMap<>();
        tagToStyle = new HashMap<>();
        onlyElement = builder.buildDiagram(this); // children.get(0).buildDiagram(this);
    }

    public ASCIIRootDiagram(ASCIIDiagramNode node) {
        this(node, node);
    }

    @Override
    public Map<String, ASCIIDiagram> getIdTable() {
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

    public void pushStyle(CSSClassDiagramStyleList list) {
        RootDiagramModel.super.pushStyle(list);
    }

    public void pushStyle(CSSIdDiagramStyleList list) {
        RootDiagramModel.super.pushStyle(list);
    }

    public void pushStyle(CSSDiagramStyleList list) {
        RootDiagramModel.super.pushStyle(list);
    }

    @Override
    public void putId(String id, ASCIIDiagram diagram) {
         RootDiagramModel.super.putId(id, diagram);
    }

    @Override
    public boolean hasIdInStyle(String id) {
        return RootDiagramModel.super.hasIdInStyle(id);
    }

    @Override
    public DiagramStyle getStyleById(String id) {
        return RootDiagramModel.super.getStyleById(id);
    }

    @Override
    public boolean hasClassInStyle(String className) {
        return RootDiagramModel.super.hasClassInStyle(className);
    }

    @Override
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
    public ASCIIDiagram getChildByIndex(int i) {
        if (i != 0) WarningStream.putWarning("Getting index > 0 from derived van.de.la.sehen.diagram.", this);
        return onlyElement;
    }

    @Override
    public void layoutChildren() {
        onlyElement.layout();
    }

    @Override
    public void setChildrenPosition() {
        onlyElement.setPosition(ASCIIPositionOffset.zero());
    }

    @Override
    public void paintDiagram(ASCIICanvas canvas) {
        onlyElement.paintDiagram(canvas);
    }

    @Override
    public void calculateSize() {
        setWidth(onlyElement.getWidth());
        setHeight(onlyElement.getHeight());
    }
}
