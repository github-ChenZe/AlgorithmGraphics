package van.de.la.sehen.diagram.prototypediagram.model;

import van.de.la.sehen.dimensionparticle.positionparticle.AbsoluteParticle;
import van.de.la.sehen.dimensionparticle.positionparticle.AbsoluteTuple;
import van.de.la.sehen.dimensionparticle.positionparticle.OffsetParticle;
import van.de.la.sehen.warning.WarningStream;

public interface RecursivePositioningModel<
        ThisT extends RecursivePositioningModel<ThisT, PositionT, PositionTV, PositionComponentT, OffsetT>,
        PositionT extends AbsoluteTuple<PositionT, PositionTV, PositionComponentT>,
        PositionTV,
        PositionComponentT extends AbsoluteParticle<PositionComponentT, ?>,
        OffsetT extends OffsetParticle<PositionT, PositionTV, OffsetT>
        >
        extends TreeModel<ThisT> {

    default PositionT globalPosition() {
        if (getParent() == null) { // This is the top diagram
            return basePosition();
        }
        PositionT parentPosition = getParent().globalPosition();
        return parentPosition.addByOffset(getPosition());
    }

    default OffsetT relativePosition(ThisT ancestor) {
        if (this == ancestor) { // This is the top diagram
            return baseOffset();
        }
        if (getParent() == null || getParent() == this) {
            WarningStream.putWarning("Relative position failed: " + ancestor + " is not an ancestor of me.", this);
            return baseOffset();
        }
        return getParent().relativePosition(ancestor).add(this.getPosition());
    }

    default PositionComponentT getAbsoluteX() {
        return globalPosition().getXComponent();
    }

    default PositionComponentT getAbsoluteY() {
        return globalPosition().getYComponent();
    }

    OffsetT getPosition();
    void setPosition(OffsetT position);

    PositionT basePosition();
    OffsetT baseOffset();
}
