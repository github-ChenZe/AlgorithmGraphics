package van.de.la.sehen.diagram.prototypediagram;

import van.de.la.sehen.diagram.displayeddiagram.EmptyDiagram;
import van.de.la.sehen.diagram.displayeddiagram.RootDiagram;
import van.de.la.sehen.diagram.prototypediagram.model.DiagramModel;
import van.de.la.sehen.diagram.prototypediagram.model.GeneralAbstractDiagram;
import van.de.la.sehen.diagram.prototypediagram.model.StyleModel;
import van.de.la.sehen.diagram.pseudodiagram.FreePseudoDiagram;
import van.de.la.sehen.diagram.pseudodiagram.PseudoDiagram;
import van.de.la.sehen.diagram.readerinterface.attributereading.*;
import van.de.la.sehen.diagram.readerinterface.fieldreading.DiagramFieldsCollection;
import van.de.la.sehen.diagramattributeparticle.StyleInheritWrapper;
import van.de.la.sehen.diagramattributeparticle.diagramparticle.DiagramLambdaPointer;
import van.de.la.sehen.diagramattributeparticle.diagramparticle.PseudoDiagramAccessor;
import van.de.la.sehen.diagramattributeparticle.enumeratedparticle.*;
import van.de.la.sehen.diagramstyle.DiagramStyle;
import van.de.la.sehen.dimensionparticle.positionparticle.CoordinateOffset;
import van.de.la.sehen.dimensionparticle.sizeparticle.IntDimensionComponent;
import van.de.la.sehen.util.Util;
import van.de.la.sehen.warning.WarningStream;
import van.de.la.sehen.warning.WarningType;

import javax.swing.text.Style;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class Diagram extends AbstractDiagram implements DiagramModel<Diagram, AbstractDiagram> {
    private String id = null;
    private Map<String, PseudoDiagram> pseudos = new HashMap<>();
    private List<String> classes;
    private RootDiagram root;

    @Override
    public RootDiagram getRoot() {
        return root;
    }

    @Override
    public Iterable<String> getClasses() {
        return classes;
    }

    @Override
    public void setWidth(IntDimensionComponent width) {
        if (!(this instanceof EmptyDiagram) && width.getValue() == 0) {
            WarningStream.putWarning("Setting a zero width.", this, WarningType.InvalidSize);
        }
        super.setWidth(width);
    }

    @Override
    public void setHeight(IntDimensionComponent height) {
        if (!(this instanceof EmptyDiagram) && height.getValue() == 0) {
            WarningStream.putWarning("Setting a zero height.", this, WarningType.InvalidSize);
        }
        super.setHeight(height);
    }

    public Diagram getParent() {
        return (Diagram) super.getParent();
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

    public void putId(String id, Diagram diagram) {
        root.putId(id, diagram);
    }

    protected void pushPseudo(String name, PseudoDiagram pseudoDiagram) {
        pseudos.put(name, pseudoDiagram);
    }

    public PseudoDiagram getPseudo(String name) {
        if (pseudos.containsKey(name)) {
            return pseudos.get(name);
        }
        else if (name.matches(FREE_PSEUDO_DIAGRAM_PATTERN)) {
            String[] xy = name.split(",");
            double xRatio = Util.calculateDivision(xy[0]);
            double yRatio = Util.calculateDivision(xy[1]);
            return new FreePseudoDiagram(xRatio, yRatio, this, null);
        } else {
            WarningStream.putWarning("Invalid pseudo name '" + name + "'.", this);
            return null;
        }
    }

    public Diagram(Diagram parent, DiagramStyle style) {
        super(parent, style);
        if (parent != null) this.root = parent.root;
        else this.root = (RootDiagram) this;
        classes = new ArrayList<>();
        pushPseudos();
    }

    public Diagram(Diagram parent, DiagramFieldsCollection fieldsCollection) {
        super(parent, new DiagramStyle(fieldsCollection));
        if (parent != null) { // Not a RootDiagram
            this.root = parent.root;
            if (fieldsCollection != null) {
                id = fieldsCollection.getId();
                putId(fieldsCollection.getId());
            }
        } else { // RootDiagram
            this.root = (RootDiagram) this;
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
        pushPseudo("N", PseudoDiagram.N.apply(this)); // new NPseudoDiagram(this, null));
        pushPseudo("NW", PseudoDiagram.NW.apply(this)); //new NWPseudoDiagram(this, null));
        pushPseudo("W", PseudoDiagram.W.apply(this)); //new WPseudoDiagram(this, null));
        pushPseudo("SW", PseudoDiagram.SW.apply(this)); //new SWPseudoDiagram(this, null));
        pushPseudo("S", PseudoDiagram.S.apply(this)); //new SPseudoDiagram(this, null));
        pushPseudo("SE", PseudoDiagram.SE.apply(this)); //new SEPseudoDiagram(this, null));
        pushPseudo("E", PseudoDiagram.E.apply(this)); // new EPseudoDiagram(this, null));
        pushPseudo("NE", PseudoDiagram.NE.apply(this)); //new NEPseudoDiagram(this, null));
        pushPseudo("C", PseudoDiagram.C.apply(this)); //new CPseudoDiagram(this, null));
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

    public Diagram putStyle(String key, DiagramAttributeCluster value) {
        getStyle().putStyle(key, value);
        return this;
    }

    public StyleInheritWrapper getWrapper(String fieldName) {
        return new StyleInheritWrapper(() -> this, fieldName);
    }

    public static DiagramAttributeParticle attributeToObject(String key, Object value) {
        return StyleModel.attributeToObject(key, value);
    }

    private static final int SCALE = 2;

    public static boolean isAttributeKey(String key) {
        return StyleModel.isAttributeKey(key);
    }

    public DiagramAttributeParticle defaultAttribute(String key) {
        switch (key) {
            case "LineThickness":
                return new DiagramAttributeIntegerParticle(4 * SCALE);
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
                return new DiagramAttributeIntegerParticle(5 * SCALE);
            case "ArrowStyle":
                return ArrowStyle.ARROW;
            case "LineStyle":
                return LineStyle.SOLID;
            case "Padding":
                return new DiagramAttributeIntegerParticle(0);
            case "RootSeparation":
                return new DiagramAttributeIntegerParticle(40 * SCALE);
            case "BranchSeparation":
                return new DiagramAttributeIntegerParticle(40 * SCALE);
            case "VerticalAlign":
                return VerticalAlign.CENTER;
            case "HorizontalAlign":
                return HorizontalAlign.CENTER;
            case "VerticalAlignAnchor":
                return new PseudoDiagramAccessor(this, "N");
            case "TreeRootAnchor":
                return DEFAULT;
            case "TreeBranchAnchor":
                return DEFAULT;
            case "TreeRootArrowPerch":
                return new DiagramLambdaPointer<>(() -> this);
            case "IgnoreEmpty":
                return BooleanStyle.TRUE;
            case "Draw":
                return BooleanStyle.TRUE;
            case SWAP:
            case EMERGE:
            case EMERGE_REVERSE:
                return BooleanStyle.FALSE;
            case SWAP_PROGRESS:
            case EMERGE_PROGRESS:
            case ANIMATION_PROGRESS:
                if (getParent() != null)
                    return new StyleInheritWrapper(this::getParent, key).get(0);
                else {
                    WarningStream.putWarning("Came to root diagram with no " + key + " found.", this);
                    return new DiagramAttributeDoubleParticle(0.0);
                }
            case ANIMATION_STEP:
                return new DiagramAttributeDoubleParticle(1.0 / 16);
        }
        WarningStream.putWarning("Invalid or no default attribute key name " + key + ".", Diagram.class);
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

    protected CoordinateOffset getPadding() {
        return new CoordinateOffset(getStyleOf("Padding"));
    }

    protected PseudoDiagram getFrom() {
        return getStyleOf("From");
    }

    protected PseudoDiagram getTo() {
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

    public PseudoDiagram getVerticalAlignAnchor() {
        return getStyleOf("VerticalAlignAnchor");
    }

    public PseudoDiagram getTreeRootAnchor() {
        return getStyleOf("TreeRootAnchor");
    }

    public PseudoDiagram getTreeBranchAnchor() {
        return getStyleOf("TreeBranchAnchor");
    }

    //TODO: getStyleOf may return UNSET.
    public Diagram getTreeRootArrowPerch() {
        return getStyleOf("TreeRootArrowPerch");
    }

    protected Boolean getIgnoreEmpty() {
        return ((BooleanStyle) (getStyle("IgnoreEmpty").get(0))).toBoolean();
    }

    protected Boolean getDraw() {
        return ((BooleanStyle) (getStyle("Draw").get(0))).toBoolean();
    }

    protected boolean getSwap() {
        return ((BooleanStyle) (getStyle(SWAP).get(0))).toBoolean();
    }

    protected DiagramAttributeSwapAnimationParticle getSwapParameter() {
        return getStyleOf(SWAP_PARAMETER);
    }

    protected int getSwapRowFrom() {
        return getSwapParameter().getRowFrom();
    }

    protected int getSwapRowTo() {
        return getSwapParameter().getRowTo();
    }

    protected int getSwapColumnFrom() {
        return getSwapParameter().getColumnFrom();
    }

    protected int getSwapColumnTo() {
        return getSwapParameter().getColumnTo();
    }

    protected double getSwapProgress() {
        return ((DiagramAttributeDoubleParticle) getStyle(SWAP_PROGRESS).get(0)).get();
    }

    protected boolean getEmerge() {
        return ((BooleanStyle) (getStyle(EMERGE).get(0))).toBoolean();
    }

    protected boolean getEmergeReverse() {
        return ((BooleanStyle) (getStyle(EMERGE_REVERSE).get(0))).toBoolean();
    }

    protected double getEmergeProgress() {
        return ((DiagramAttributeDoubleParticle) getStyle(EMERGE_PROGRESS).get(0)).get();
    }

    protected double getAnimationStep() {
        return getStyleOf(ANIMATION_STEP);
    }

    protected double getAnimationProgress() {
        return ((DiagramAttributeDoubleParticle) getStyle(ANIMATION_PROGRESS).get(0)).get();
    }

    @Override
    public void layout() {
    }
}

