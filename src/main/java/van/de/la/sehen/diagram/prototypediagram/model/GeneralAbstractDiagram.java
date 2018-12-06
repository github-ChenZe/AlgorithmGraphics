package van.de.la.sehen.diagram.prototypediagram.model;

import van.de.la.sehen.diagramstyle.DiagramStyle;
import van.de.la.sehen.dimensionparticle.positionparticle.AbsoluteParticle;
import van.de.la.sehen.dimensionparticle.positionparticle.AbsoluteTuple;
import van.de.la.sehen.dimensionparticle.positionparticle.OffsetParticle;
import van.de.la.sehen.dimensionparticle.sizeparticle.DimensionComponent;

public abstract class GeneralAbstractDiagram<
        ThisT extends GeneralAbstractDiagram<ThisT, PositionT, PositionTV, PositionComponentT, OffsetT, SizeComponentT, SizeOffsetT, CanvasT, ImageT>,
        PositionT extends AbsoluteTuple<PositionT, PositionTV, PositionComponentT>,
        PositionTV,
        PositionComponentT extends AbsoluteParticle<PositionComponentT, ?>,
        OffsetT extends OffsetParticle<PositionT, PositionTV, OffsetT>,
        SizeComponentT extends DimensionComponent<?, SizeOffsetT>,
        SizeOffsetT extends OffsetParticle<?, ?, SizeOffsetT>,
        CanvasT,
        ImageT
        > implements AbstractDiagramModel<ThisT, PositionT, PositionTV, PositionComponentT, OffsetT, SizeComponentT, SizeOffsetT, CanvasT, ImageT> {

    public static final double PI = 3.14159265358979;

    public GeneralAbstractDiagram(ThisT parent, DiagramStyle style) {
        this.parent = parent;
        this.style = style;
        this.position = baseOffset();
    }

    public DiagramStyle getStyle() {
        return style;
    }

    private DiagramStyle style;

    public OffsetT getPosition() {
        return position;
    }

    public void setPosition(OffsetT offset) { this.position = offset; }

    private OffsetT position;

    public ThisT getParent() {
        return parent;
    }

    private ThisT parent;

    public SizeComponentT getWidth() {
        return width;
    }

    public void setWidth(SizeComponentT width) {
        this.width = width;
    }

    private SizeComponentT width;

    public SizeComponentT getHeight() {
        return height;
    }

    public void setHeight(SizeComponentT height) {
        this.height = height;
    }

    private SizeComponentT height;
}
