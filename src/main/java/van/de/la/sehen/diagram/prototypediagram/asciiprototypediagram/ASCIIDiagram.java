package van.de.la.sehen.diagram.prototypediagram.asciiprototypediagram;

import van.de.la.sehen.diagram.displayeddiagram.asciidisplayeddiagram.ASCIIEmptyDiagram;
import van.de.la.sehen.diagram.displayeddiagram.asciidisplayeddiagram.ASCIIRootDiagram;
import van.de.la.sehen.diagram.prototypediagram.model.DiagramModel;
import van.de.la.sehen.diagram.prototypediagram.model.StyleModel;
import van.de.la.sehen.diagram.pseudodiagram.ASCIIFreePseudoDiagram;
import van.de.la.sehen.diagram.pseudodiagram.ASCIIPseudoDiagram;
import van.de.la.sehen.diagram.readerinterface.attributereading.*;
import van.de.la.sehen.diagram.readerinterface.diagrambuilder.WrappedDiagramBuilder;
import van.de.la.sehen.diagram.readerinterface.fieldreading.DiagramFieldsCollection;
import van.de.la.sehen.diagramattributeparticle.ASCIIStyleInheritWrapper;
import van.de.la.sehen.diagramattributeparticle.diagramparticle.asciidiagramparticle.ASCIIPseudoDiagramAccessor;
import van.de.la.sehen.diagramattributeparticle.enumeratedparticle.*;
import van.de.la.sehen.diagramstyle.DiagramStyle;
import van.de.la.sehen.dimensionparticle.positionparticle.asciipositionparticle.ASCIICoordinateOffset;
import van.de.la.sehen.dimensionparticle.sizeparticle.asciisizeparticle.ASCIIIntDimensionComponent;
import van.de.la.sehen.util.Util;
import van.de.la.sehen.warning.WarningStream;
import van.de.la.sehen.warning.WarningType;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// For ASCIIDiagram, The size should be just fit to wrap the diagram

public abstract class ASCIIDiagram extends ASCIIAbstractDiagram implements DiagramModel<ASCIIDiagram, ASCIIAbstractDiagram> {
    private String id = null;
    private Map<String, ASCIIPseudoDiagram> pseudos = new HashMap<>();
    private List<String> classes;
    private ASCIIRootDiagram root;

    @Override
    public ASCIIRootDiagram getRoot() {
        return root;
    }

    @Override
    public Iterable<String> getClasses() {
        return classes;
    }

    @Override
    public void setWidth(ASCIIIntDimensionComponent width) {
        if (!(this instanceof ASCIIEmptyDiagram) && width.getValue() <= 0) {
            WarningStream.putWarning("Setting a zero or negative width " + width.getValue() + ".", this, WarningType.InvalidSize);
        }
        super.setWidth(width);
    }

    @Override
    public void setHeight(ASCIIIntDimensionComponent height) {
        if (!(this instanceof ASCIIEmptyDiagram) && height.getValue() <= 0) {
            WarningStream.putWarning("Setting a zero or negative height " + height.getValue() + ".", this, WarningType.InvalidSize);
        }
        super.setHeight(height);
    }

    public ASCIIDiagram getParent() {
        return (ASCIIDiagram) super.getParent();
    }

    public static final String FREE_PSEUDO_DIAGRAM_PATTERN = "[0-9.]*(/[0-9.]*)?,[0-9.]*(/[0-9.]*)?";

    public String getId() {
        if (id == null || id.isEmpty()) {
            id = "@" + System.identityHashCode(this);
            putId(id);
        }
        return id;
    }

    public void putId(String id) {
        root.putId(id, this);
    }

    public void putId(String id, ASCIIDiagram diagram) {
        root.putId(id, diagram);
    }

    protected void pushPseudo(String name, ASCIIPseudoDiagram pseudoDiagram) {
        pseudos.put(name, pseudoDiagram);
    }

    public ASCIIPseudoDiagram getPseudo(String name) {
        if (pseudos.containsKey(name)) {
            return pseudos.get(name);
        }
        else if (name.matches(FREE_PSEUDO_DIAGRAM_PATTERN)) {
            String[] xy = name.split(",");
            double xRatio = Util.calculateDivision(xy[0]);
            double yRatio = Util.calculateDivision(xy[1]);
            return new ASCIIFreePseudoDiagram(xRatio, yRatio, this, null);
        } else {
            WarningStream.putWarning("Invalid pseudo name '" + name + "'.", this);
            return null;
        }
    }

    public ASCIIDiagram(ASCIIDiagram parent, DiagramStyle style) {
        super(parent, style);
        if (parent != null) this.root = parent.root;
        else this.root = (ASCIIRootDiagram) this;
        classes = new ArrayList<>();
        pushPseudos();
    }

    public ASCIIDiagram(ASCIIDiagram parent, DiagramFieldsCollection fieldsCollection) {
        super(parent, new DiagramStyle(fieldsCollection));
        if (parent != null) { // Not a RootDiagram
            this.root = parent.root;
            if (fieldsCollection != null) {
                id = fieldsCollection.getId();
                putId(fieldsCollection.getId());
            }
        } else { // RootDiagram
            this.root = (ASCIIRootDiagram) this;
        }
        classes = new ArrayList<>();
        if (fieldsCollection != null && fieldsCollection.getClasses() != null) {
            for (String classname: fieldsCollection.getClasses()) {
                classes.add(classname);
            }
        }
        pushPseudos();
    }

    public void pushPseudos() {
        pushPseudo("N", ASCIIPseudoDiagram.N.apply(this)); // new NPseudoDiagram(this, null));
        pushPseudo("NW", ASCIIPseudoDiagram.NW.apply(this)); //new NWPseudoDiagram(this, null));
        pushPseudo("W", ASCIIPseudoDiagram.W.apply(this)); //new WPseudoDiagram(this, null));
        pushPseudo("SW", ASCIIPseudoDiagram.SW.apply(this)); //new SWPseudoDiagram(this, null));
        pushPseudo("S", ASCIIPseudoDiagram.S.apply(this)); //new SPseudoDiagram(this, null));
        pushPseudo("SE", ASCIIPseudoDiagram.SE.apply(this)); //new SEPseudoDiagram(this, null));
        pushPseudo("E", ASCIIPseudoDiagram.E.apply(this)); // new EPseudoDiagram(this, null));
        pushPseudo("NE", ASCIIPseudoDiagram.NE.apply(this)); //new NEPseudoDiagram(this, null));
        pushPseudo("C", ASCIIPseudoDiagram.C.apply(this)); //new CPseudoDiagram(this, null));
    }

    public String getPseudoName(String direction) {
        return getId() + "::" + direction;
    }

    public String getPseudoName(double x, double y) {
        return getId() + "::" + x + "," + y;
    }

    public static String getPseudoPostfix(double x, double y) {
        return x + "," + y;
    }

    public static String parsePseudoParentId(String pseudoname) {
        return pseudoname.split("::")[0];
    }

    public static String parsePseudoPostfix(String pseudoname) {
        return pseudoname.split("::")[1];
    }

    /* Style related */

    public ASCIIDiagram putStyle(String key, DiagramAttributeCluster value) {
        getStyle().putStyle(key, value);
        return this;
    }

    public ASCIIStyleInheritWrapper getWrapper(String fieldName) {
        return new ASCIIStyleInheritWrapper(() -> this, fieldName);
    }

    public static DiagramAttributeParticle attributeToObject(String key, Object value) {
        return StyleModel.attributeToObject(key, value);
    }

    private static final int SCALE = 1;

    public static boolean isAttributeKey(String key) {
        return StyleModel.isAttributeKey(key);
    }

    public DiagramAttributeParticle defaultAttribute(String key) {
        switch (key) {
            case "LineThickness":
                return new DiagramAttributeIntegerParticle(1 * SCALE);
            case "LineColor":
                return new DiagramAttributeColorParticle(Color.BLACK);
            case "FontSize":
                return new DiagramAttributeIntegerParticle(40 * SCALE);
            case "FontName":
                return new DiagramAttributeStringParticle("Arial");
            case "FontColor":
                return new DiagramAttributeColorParticle(Color.BLACK);
            case "Grid":
                return BooleanStyle.TRUE;
            case "Separation":
                return new DiagramAttributeIntegerParticle(2 * SCALE);
            case "ArrowStyle":
                return ArrowStyle.ARROW;
            case "LineStyle":
                return LineStyle.SOLID;
            case "Padding":
                return new DiagramAttributeIntegerParticle(0);
            case "RootSeparation":
                return new DiagramAttributeIntegerParticle(2 * SCALE);
            case "BranchSeparation":
                return new DiagramAttributeIntegerParticle(7 * SCALE);
            case "VerticalAlign":
                return VerticalAlign.CENTER;
            case "HorizontalAlign":
                return HorizontalAlign.CENTER;
            case "VerticalAlignAnchor":
                return new ASCIIPseudoDiagramAccessor(this, "N");
            case "TreeRootAnchor":
                return DEFAULT;
            case "TreeBranchAnchor":
                return DEFAULT;
            case "TreeRootArrowPerch":
                return UNSET;
            case "IgnoreEmpty":
                return BooleanStyle.TRUE;
            case "Draw":
                return BooleanStyle.TRUE;
        }
        WarningStream.putWarning("Invalid or no default attribute key name " + key + ".", ASCIIDiagram.class);
        return null;
    }

    public <T> T getStyleOf(String key) {
        DiagramAttributeCluster cluster = getStyle(key);
        if (!(cluster instanceof DiagramAttributeSingleCluster)) {
            WarningStream.putWarning("Getting single particle from non-single cluster", this);
        }
        DiagramAttributeParticle particleRaw = cluster.get(0);
        Object result = particleRaw.get();
        return (T) result;
    }

    protected int getLineThickness() {
        return getStyleOf("LineThickness");
    }

    protected Color getLineColor() {
        return getStyleOf("LineColor");
    }

    protected int getFontSize() {
        return getStyleOf("FontSize");
    }

    protected String getFontName() {
        return getStyleOf("FontName");
    }

    protected Color getFontColor() {
        return getStyleOf("FontColor");
    }

    protected Boolean getGrid() {
        return getStyleOf("Grid");
    }

    protected int getSeparation() {
        return getStyleOf("Separation");
    }

    protected ASCIICoordinateOffset getPadding() {
        return new ASCIICoordinateOffset(getStyleOf("Padding"));
    }

    protected ASCIIPseudoDiagram getFrom() {
        return getStyleOf("From");
    }

    protected ASCIIPseudoDiagram getTo() {
        return getStyleOf("To");
    }

    protected ArrowStyle getArrowStyle() {
        return getStyleOf("ArrowStyle");
    }

    protected LineStyle getLineStyle() {
        return getStyleOf("LineStyle");
    }

    protected int getRootSeparation() {
        return getStyleOf("RootSeparation");
    }

    protected int getBranchSeparation() {
        return getStyleOf("BranchSeparation");
    }

    protected VerticalAlign getVerticalAlign() {
        return getStyleOf("VerticalAlign");
    }

    protected HorizontalAlign getHorizontalAlign() {
        return getStyleOf("HorizontalAlign");
    }

    public ASCIIPseudoDiagram getVerticalAlignAnchor() {
        return getStyleOf("VerticalAlignAnchor");
    }

    public ASCIIPseudoDiagram getTreeRootAnchor() {
        return getStyleOf("TreeRootAnchor");
    }

    public ASCIIPseudoDiagram getTreeBranchAnchor() {
        return getStyleOf("TreeBranchAnchor");
    }

    //TODO: getStyleOf may return UNSET.
    public ASCIIDiagram getTreeRootArrowPerch() {
        return getStyleOf("TreeRootArrowPerch");
    }

    protected Boolean getIgnoreEmpty() {
        return ((BooleanStyle) (getStyle("IgnoreEmpty").get(0))).toBoolean();
    }

    protected Boolean getDraw() {
        return ((BooleanStyle) (getStyle("Draw").get(0))).toBoolean();
    }

    @Override
    public void layout() {
    }
}

