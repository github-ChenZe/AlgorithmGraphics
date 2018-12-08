package van.de.la.sehen.diagram.prototypediagram.model;

import org.springframework.lang.Nullable;
import van.de.la.sehen.diagram.displayeddiagram.model.RootDiagramModel;
import van.de.la.sehen.diagram.prototypediagram.Diagram;
import van.de.la.sehen.diagram.readerinterface.attributereading.*;
import van.de.la.sehen.diagramattributeparticle.enumeratedparticle.*;
import van.de.la.sehen.diagramattributeparticle.model.GeneralStyleInheritWrapper;
import van.de.la.sehen.diagramstyle.DiagramStyle;
import van.de.la.sehen.warning.WarningStream;

public interface StyleModel<ThisT extends TreeModel<ThisT> & StyleModel<ThisT>> extends DiagramCSSModel, TreeModel<ThisT> {
    public static final SpecialStyleParticle INHERIT = SpecialStyleParticle.INHERIT;
    public static final SpecialStyleParticle DEFAULT = SpecialStyleParticle.DEFAULT;
    public static final SpecialStyleParticle UNSET   = SpecialStyleParticle.UNSET;

    public static final String LINE_THICKNESS          = "LineThickness";
    public static final String LINE_COLOR              = "LineColor";
    public static final String FONT_SIZE               = "FontSize";
    public static final String FONT_NAME               = "FontName";
    public static final String FONT_COLOR              = "FontColor";
    public static final String GRID                    = "Grid";
    public static final String SEPARATION              = "Separation";
    public static final String FROM                    = "From";
    public static final String TO                      = "To";
    public static final String ARROW_STYLE             = "ArrowStyle";
    public static final String LINE_STYLE              = "LineStyle";
    public static final String PADDING                 = "Padding";
    public static final String PADDING_LEFT            = "PaddingLeft";
    public static final String PADDING_RIGHT           = "PaddingRight";
    public static final String PADDING_TOP             = "PaddingTop";
    public static final String PADDING_BOTTOM          = "PaddingBottom";
    public static final String ROOT_SEPARATION         = "RootSeparation";
    public static final String BRANCH_SEPARATION       = "BranchSeparation";
    public static final String VERTICAL_ALIGN          = "VerticalAlign";
    public static final String HORIZONTAL_ALIGN        = "HorizontalAlign";
    public static final String VERTICAL_ALIGN_ANCHOR   = "VerticalAlignAnchor";
    public static final String TREE_ROOT_ANCHOR        = "TreeRootAnchor";
    public static final String TREE_BRANCH_ANCHOR      = "TreeBranchAnchor";
    public static final String TREE_ROOT_ARROW_PERCH   = "TreeRootArrowPerch";
    public static final String IGNORE_EMPTY            = "IgnoreEmpty";
    public static final String DRAW                    = "Draw";
    public static final String SWAP_PARAMETER          = "SwapParameter";
    public static final String SWAP                    = "Swap";
    public static final String SWAP_PROGRESS           = "SwapProgress";
    public static final String EMERGE                  = "Emerge";
    public static final String EMERGE_PROGRESS         = "EmergeProgress";
    public static final String EMERGE_REVERSE          = "EmergeReverse";
    public static final String ANIMATION_STEP          = "AnimationStep";
    public static final String ANIMATION_PROGRESS      = "AnimationProgress";
    public static final String WIDTH                   = "Width";
    public static final String HEIGHT                  = "Height";
    public static final String VAR_START               = "VarStart";
    public static final String VAR_END                 = "VarEnd";
    public static final String Y_START                 = "YStart";
    public static final String Y_END                   = "YEnd";
    public static final String FUNCTION_VARIABLE_NAME  = "FunctionVariableName";
    public static final String FUNCTION_ANIMATOR_NAME  = "FunctionAnimatorName";
    public static final String BACKGROUND_COLOR        = "BackgroundColor";
    public static final String MIRROR_DIAGRAM          = "MirrorDiagram";
    public static final String TRANSITION_PROGRESS     = "TransitionProgress";
    public static final String SCALE_FACTOR            = "ScaleFactor";
    public static final String BRANCH_EMERGE           = "BranchEmerge";
    public static final String BRANCH_EMERGE_PARAMETER = "BranchEmergeParameter";
    public static final String BRANCH_EMERGE_PROGRESS  = "BranchEmergeProgress";
    public static final String BRANCH_EMERGE_REVERSE   = "BranchEmergeReverse";

    public static boolean isAttributeKey(String key) {
        return ( key.equals("LineThickness")
                || key.equals("LineColor")
                || key.equals("FontSize")
                || key.equals("FontName")
                || key.equals("FontColor")
                || key.equals("Grid")
                || key.equals("Separation")
                || key.equals("From")
                || key.equals("To")
                || key.equals("from")
                || key.equals("to")
                || key.equals("ArrowStyle")
                || key.equals("LineStyle")
                || key.equals("Padding")
                || key.equals(PADDING_LEFT)
                || key.equals(PADDING_RIGHT)
                || key.equals(PADDING_TOP)
                || key.equals(PADDING_BOTTOM)
                || key.equals("RootSeparation")
                || key.equals("BranchSeparation")
                || key.equals("VerticalAlign")
                || key.equals("HorizontalAlign")
                || key.equals("VerticalAlignAnchor")
                || key.equals("TreeRootAnchor")
                || key.equals("TreeBranchAnchor")
                || key.equals("TreeRootArrowPerch")
                || key.equals("IgnoreEmpty")
                || key.equals("Draw")
                || key.equals(SWAP_PARAMETER)
                || key.equals(SWAP)
                || key.equals(SWAP_PROGRESS)
                || key.equals(EMERGE)
                || key.equals(EMERGE_PROGRESS)
                || key.equals(EMERGE_REVERSE)
                || key.equals(ANIMATION_STEP)
                || key.equals(ANIMATION_PROGRESS)
                || key.equals(WIDTH)
                || key.equals(HEIGHT)
                || key.equals(VAR_START)
                || key.equals(VAR_END)
                || key.equals(Y_START)
                || key.equals(Y_END)
                || key.equals(FUNCTION_VARIABLE_NAME)
                || key.equals(FUNCTION_ANIMATOR_NAME)
                || key.equals(BACKGROUND_COLOR)
                || key.equals(MIRROR_DIAGRAM)
                || key.equals(TRANSITION_PROGRESS)
                || key.equals(SCALE_FACTOR)
                || key.equals(BRANCH_EMERGE)
                || key.equals(BRANCH_EMERGE_PARAMETER)
                || key.equals(BRANCH_EMERGE_PROGRESS)
                || key.equals(BRANCH_EMERGE_REVERSE)
        );
    }

    static DiagramAttributeParticle attributeToObject(String key, Object value) {
        if (key.equals("To") || key.equals("From") || key.equals("to") || key.equals("from") ||
                key.equals("VerticalAlignAnchor") || key.equals("TreeRootAnchor") || key.equals("TreeBranchAnchor") || key.equals("TreeRootArrowPerch") ||
                key.equals(MIRROR_DIAGRAM)) {
            WarningStream.putWarning("Key of '" + key + "' detected, no longer supported now.", Diagram.class);
        }
        if (value.equals("Inherit")) return SpecialStyleParticle.INHERIT;
        if (value.equals("Default")) return SpecialStyleParticle.DEFAULT;
        if (value.equals("Unset")) return SpecialStyleParticle.UNSET;
        switch (key) {
            case "LineThickness":
            case "FontSize":
            case "Separation":
            case "Padding":
            case PADDING_LEFT:
            case PADDING_RIGHT:
            case PADDING_TOP:
            case PADDING_BOTTOM:
            case "RootSeparation":
            case "BranchSeparation":
            case WIDTH:
            case HEIGHT:
            case BRANCH_EMERGE_PARAMETER:
                return DiagramAttributeIntegerParticle.fromObject(value);
            case "FontName":
            case FUNCTION_VARIABLE_NAME:
            case FUNCTION_ANIMATOR_NAME:
                return DiagramAttributeStringParticle.fromObject(value);
            case "LineColor":
            case "FontColor":
            case BACKGROUND_COLOR:
                return DiagramAttributeColorParticle.fromObject(value);
            case "ArrowStyle":
                return ArrowStyle.fromObject(value);
            case "LineStyle":
                return LineStyle.fromObject(value);
            case "VerticalAlign":
                return VerticalAlign.fromObject(value);
            case "HorizontalAlign":
                return HorizontalAlign.fromObject(value);
            case "Grid":
            case "IgnoreEmpty":
            case "Draw":
            case SWAP:
            case EMERGE:
            case EMERGE_REVERSE:
            case BRANCH_EMERGE:
            case BRANCH_EMERGE_REVERSE:
                return BooleanStyle.fromObject(value);
            case SWAP_PARAMETER:
                return DiagramAttributeSwapAnimationParticle.fromObject(value);
            case SWAP_PROGRESS:
            case EMERGE_PROGRESS:
            case ANIMATION_PROGRESS:
            case BRANCH_EMERGE_PROGRESS:
            case VAR_START:
            case VAR_END:
            case Y_START:
            case Y_END:
            case TRANSITION_PROGRESS:
            case SCALE_FACTOR:
                return DiagramAttributeDoubleParticle.fromObject(value);
            case ANIMATION_STEP:
                return DiagramAttributeDoubleParticle.fromObject(value);
        }
        WarningStream.putWarning("Invalid attribute key name " + key + ".", Diagram.class);
        return null;
    }

    default RootDiagramModel<?> getRoot() {
        WarningStream.putWarning("Calling default getRoot.", this);
        return null;
    }

    default boolean hasIdInStyle(String id) {
        return getRoot().hasIdInStyle(id);
    }

    default DiagramStyle getStyleById(String id) {
        return getRoot().getStyleById(id);
    }

    default boolean hasTagInStyle(String tag) {
        return getRoot().hasTagInStyle(tag);
    }

    default DiagramStyle getStyleByTag(String tag) {
        return getRoot().getStyleByTag(tag);
    }

    default boolean hasClassInStyle(String className) {
        return getRoot().hasClassInStyle(className);
    }

    default DiagramStyle getStyleByClass(String className) {
        return getRoot().getStyleByClass(className);
    }

    default String getId() {
        WarningStream.putWarning("Calling default getId.", this);
        return null;
    }

    default String getTagName() {
        WarningStream.putWarning("Calling default getId.", this);
        return null;
    }

    default Iterable<String> getClasses() {
        WarningStream.putWarning("Calling default getClasses.", this);
        return null;
    }

    default void maskStyle(String key, DiagramAttributeCluster value) {
        getStyle().putStyle(key, value);
    }

    default DiagramStyle getStyle() {
        WarningStream.putWarning("Calling default getStyle.", this);
        return null;
    }

    @Nullable
    default DiagramAttributeParticle defaultAttribute(String key) {
        WarningStream.putWarning("Calling default defaultAttribute.", this);
        return null;
    }

    @Nullable
    default DiagramAttributeCluster getStyle(String key) {
        DiagramAttributeCluster value = this.getStyle().getStyle(key);
        if (value == null && hasIdInStyle(this.getId())) value = getStyleById(this.getId()).getStyle(key);
        if (value == null) {
            for (String className: this.getClasses()) {
                if (value == null && hasClassInStyle(className))
                    value = getStyleByClass(className).getStyle(key);
            }
        }
        if (value == null && hasTagInStyle(getTagName())) {
            value = getStyleByTag(this.getTagName()).getStyle(key);
        }
        try { // the order is important: StyleInheritWrapper case should occur before DEFAULT case as
            // ancestor may assign DEFAULT to the attribute to let child diagram decide which value to take
            if (value instanceof GeneralStyleInheritWrapper<?, ?>) {
                GeneralStyleInheritWrapper<?, ?> wrapper = (GeneralStyleInheritWrapper<?, ?>) value;
                wrapper.isRecursionSafe(this);
                value = wrapper.get();
            } else if (value != null && !(value instanceof DiagramAttributeSingleCluster)) {
                WarningStream.putWarning("Encountered not Single Cluster, which is not supported yet.", this);
                return value;
            }
            DiagramAttributeSingleCluster singleValue = (DiagramAttributeSingleCluster) value;
            if (singleValue != null && singleValue.getUnique() == INHERIT) {
                if (getParent() != null) {
                    return getParent().getStyle(key);
                }
                value = null;
                WarningStream.putWarning("Inherit style without parent.", this);
            }
            if (singleValue != null && singleValue.getUnique() == DEFAULT) {
                DiagramAttributeParticle particle = defaultAttribute(key);
                if (particle == DEFAULT) {
                    WarningStream.putWarning("Recursion of DEFAULT attribute '" + key + "',", this);
                    singleValue = null;
                } else if (particle == null) {
                    return null;
                } else return particle.toCluster();
            }
            if (singleValue == null) {
                DiagramAttributeParticle<?> particle = defaultAttribute(key);
                return particle == null ? null : particle.toCluster();
            }
            return value;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
