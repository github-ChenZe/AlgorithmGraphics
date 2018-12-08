package van.de.la.sehen.diagram.prototypediagram;

import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import van.de.la.sehen.diagram.displayeddiagram.EmptyDiagram;
import van.de.la.sehen.diagram.displayeddiagram.RootDiagram;
import van.de.la.sehen.diagram.prototypediagram.model.DiagramModel;
import van.de.la.sehen.diagram.prototypediagram.model.StyleModel;
import van.de.la.sehen.diagram.pseudodiagram.FixedPseudoDiagram;
import van.de.la.sehen.diagram.pseudodiagram.FreePseudoDiagram;
import van.de.la.sehen.diagram.pseudodiagram.PseudoDiagram;
import van.de.la.sehen.diagram.readerinterface.attributereading.*;
import van.de.la.sehen.diagram.readerinterface.fieldreading.DiagramFieldsCollection;
import van.de.la.sehen.diagramattributeparticle.StyleInheritWrapper;
import van.de.la.sehen.diagramattributeparticle.diagramparticle.DiagramLambdaPointer;
import van.de.la.sehen.diagramattributeparticle.diagramparticle.PseudoDiagramAccessor;
import van.de.la.sehen.diagramattributeparticle.enumeratedparticle.*;
import van.de.la.sehen.diagramstyle.DiagramStyle;
import van.de.la.sehen.diagramtopology.ChildIndex;
import van.de.la.sehen.diagramtopology.FixedChildIndex;
import van.de.la.sehen.diagramtopology.FreeChildIndex;
import van.de.la.sehen.diagramtopology.PseudoChildIndex;
import van.de.la.sehen.dimensionparticle.positionparticle.AbsoluteCoordinate;
import van.de.la.sehen.dimensionparticle.positionparticle.AbsoluteParticle;
import van.de.la.sehen.dimensionparticle.positionparticle.AbsolutePosition;
import van.de.la.sehen.dimensionparticle.positionparticle.CoordinateOffset;
import van.de.la.sehen.dimensionparticle.sizeparticle.IntDimensionComponent;
import van.de.la.sehen.util.Util;
import van.de.la.sehen.warning.WarningStream;
import van.de.la.sehen.warning.WarningType;

import javax.annotation.RegEx;
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

    public static final @RegEx String FREE_PSEUDO_DIAGRAM_PATTERN = "[0-9.]*(/[0-9.]*)?,[0-9.]*(/[0-9.]*)?";
    public static final @RegEx String FIXED_PSEUDO_DIAGRAM_PATTERN = "\\[[0-9]+,[0-9]+]";

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
        pseudos.put(name, putTopologicalChild(new PseudoChildIndex(name), pseudoDiagram));
    }

    public PseudoDiagram getPseudo(String name) {
        if (pseudos.containsKey(name)) {
            return pseudos.get(name);
        } else if (name.matches(FIXED_PSEUDO_DIAGRAM_PATTERN)) {
            name = name.substring(1, name.length() - 1);
            String[] xy = name.split(",");
            double xCoor = Util.calculateDivision(xy[0]);
            double yCoor = Util.calculateDivision(xy[1]);
            return new FixedPseudoDiagram(this, null, xCoor, yCoor);
        } else if (name.matches(FREE_PSEUDO_DIAGRAM_PATTERN)) {
            String[] xy = name.split(",");
            double xRatio = Util.calculateDivision(xy[0]);
            double yRatio = Util.calculateDivision(xy[1]);
            return new FreePseudoDiagram(xRatio, yRatio, this, null);
        } else {
            WarningStream.putWarning("Invalid pseudo name '" + name + "'.", this);
            return null;
        }
    }

    @Override
    protected AbstractDiagram getWildChildFromTopologicalIndex(ChildIndex index) {
        if (index instanceof FixedChildIndex) {
            return new FixedPseudoDiagram(this, null, ((FixedChildIndex) index).getX(), ((FixedChildIndex) index).getY());
        } else if (index instanceof FreeChildIndex) {
            return new FreePseudoDiagram(((FreeChildIndex) index).getXRation(), ((FreeChildIndex) index).getYRation(), this, null);
        }
        return null;
    }

    @Override
    protected ChildIndex getTopologicalIndexOfWildChild(AbstractDiagram wildChild) {
        if (wildChild instanceof FixedPseudoDiagram || wildChild instanceof FreePseudoDiagram) {
            return wildChild.getTopologicalIndex();
        } return null;
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
            case BRANCH_EMERGE:
            case BRANCH_EMERGE_REVERSE:
                return BooleanStyle.FALSE;
            case SWAP_PROGRESS:
            case EMERGE_PROGRESS:
            case ANIMATION_PROGRESS:
            case TRANSITION_PROGRESS:
            case BRANCH_EMERGE_PROGRESS:
                if (getParent() != null)
                    return new StyleInheritWrapper(this::getParent, key).get(0);
                else {
                    WarningStream.putWarning("Came to root diagram with no " + key + " found.", this);
                    return new DiagramAttributeDoubleParticle(0.0);
                }
            case ANIMATION_STEP:
                return new DiagramAttributeDoubleParticle(1.0 / 16);
            case FUNCTION_ANIMATOR_NAME:
                return new DiagramAttributeStringParticle("__@intrinsic__");
            case BACKGROUND_COLOR:
                return DiagramAttributeColorParticle.transparent();
            case SCALE_FACTOR:
                return new DiagramAttributeDoubleParticle(1.0);
            case WIDTH:
            case HEIGHT:
            case PADDING_LEFT:
            case PADDING_RIGHT:
            case PADDING_TOP:
            case PADDING_BOTTOM:
            case MIRROR_DIAGRAM:
                return null;
        }
        WarningStream.putWarning("Invalid or no default attribute key name " + key + ".", Diagram.class);
        return null;
    }

    @Nullable
    public <T> T getStyleOf(String key) {
        DiagramAttributeCluster cluster = getStyle(key);
        if (cluster == null) return null;
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

    @NonNull
    protected CoordinateOffset getPaddingComponent(@NonNull String key) {
        //TODO: fix the null-check here with a well-defined unset mark
        Integer paddingComponent = getStyleOf(key);
        if (paddingComponent != null) return new CoordinateOffset(paddingComponent);
        return new CoordinateOffset(getStyleOf(PADDING));
    }

    protected CoordinateOffset getPaddingLeft() { return getPaddingComponent(PADDING_LEFT); }

    protected CoordinateOffset getPaddingRight() { return getPaddingComponent(PADDING_RIGHT); }

    protected CoordinateOffset getPaddingTop() { return getPaddingComponent(PADDING_TOP); }

    protected CoordinateOffset getPaddingBottom() { return getPaddingComponent(PADDING_BOTTOM); }

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
        return getNotNullDouble(ANIMATION_STEP);
    }

    protected double getAnimationProgress() {
        return ((DiagramAttributeDoubleParticle) getStyle(ANIMATION_PROGRESS).get(0)).get();
    }

    public double getNotNullDouble(String key) {
        Double nullable = getStyleOf(key);
        if (nullable == null) {
            WarningStream.putWarning("Attribute not set: " + key + ".", this);
            return Double.NaN;
        }
        return nullable;
    }

    public double getVarStart() {
        return getNotNullDouble(VAR_START);
    }

    public double getVarEnd() {
        return getNotNullDouble(VAR_END);
    }

    public double getYStart() {
        return getNotNullDouble(Y_START);
    }

    public double getYEnd() {
        return getNotNullDouble(Y_END);
    }

    public String getFunctionVariableName() {
        return getStyleOf(FUNCTION_VARIABLE_NAME);
    }

    @Nullable
    public String getFunctionAnimatorName() { return getStyleOf(FUNCTION_ANIMATOR_NAME); }

    protected Color getBackgroundColor() {
        return getStyleOf(BACKGROUND_COLOR);
    }

    @Override
    protected AbstractDiagram getMirrorDiagram() {
        Diagram thisMirror = getStyleOf(MIRROR_DIAGRAM);
        if (thisMirror != null) return thisMirror;
        return super.getMirrorDiagram();
    }

    @Override
    public double getTransitionProgress() {
        return getNotNullDouble(TRANSITION_PROGRESS);
    }

    public double getScaleFactor() {
        return getNotNullDouble(SCALE_FACTOR);
    }

    protected boolean getBranchEmerge() {
        return ((BooleanStyle) (getStyle(BRANCH_EMERGE).get(0))).toBoolean();
    }

    protected boolean getBranchEmergeReverse() {
        return ((BooleanStyle) (getStyle(BRANCH_EMERGE_REVERSE).get(0))).toBoolean();
    }

    protected double getBranchEmergeProgress() {
        return getNotNullDouble(BRANCH_EMERGE_PROGRESS);
    }

    protected int getBranchEmergeIndex() {
        Integer index = getStyleOf(BRANCH_EMERGE_PARAMETER);
        if (index == null) {
            WarningStream.putWarning(BRANCH_EMERGE_PARAMETER + " had not been set.", this);
            return 0;
        }
        return index;
    }

    @Override
    public IntDimensionComponent getWidth() {
        Integer width = getStyleOf(WIDTH);
        return width == null ? super.getWidth() : new IntDimensionComponent(width);
    }

    @Override
    public IntDimensionComponent getHeight() {
        Integer height = getStyleOf(HEIGHT);
        return height == null ? super.getHeight() : new IntDimensionComponent(height);
    }

    @Override
    public void layout() {
    }
}

