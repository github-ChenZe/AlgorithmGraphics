package van.de.la.sehen.diagram.prototypediagram.model;

import van.de.la.sehen.diagram.prototypediagram.AbstractDiagram;
import van.de.la.sehen.diagram.prototypediagram.Diagram;
import van.de.la.sehen.dimensionparticle.positionparticle.AbsoluteParticle;
import van.de.la.sehen.dimensionparticle.positionparticle.AbsoluteTuple;
import van.de.la.sehen.dimensionparticle.positionparticle.OffsetParticle;
import van.de.la.sehen.dimensionparticle.sizeparticle.DimensionComponent;

public interface AbstractDiagramModel<
        ThisT extends RecursivePositioningModel<ThisT, PositionT, PositionTV, PositionComponentT, OffsetT> & StyleModel<ThisT>,
        PositionT extends AbsoluteTuple<PositionT, PositionTV, PositionComponentT>,
        PositionTV,
        PositionComponentT extends AbsoluteParticle<PositionComponentT, ?>,
        OffsetT extends OffsetParticle<PositionT, PositionTV, OffsetT>,
        SizeComponentT extends DimensionComponent<?, SizeOffsetT>,
        SizeOffsetT extends OffsetParticle<?, ?, SizeOffsetT>,
        CanvasT,
        ImageT
        > extends RecursivePositioningModel<ThisT, PositionT, PositionTV, PositionComponentT, OffsetT>, SizeModel<SizeComponentT, SizeOffsetT>, PaintingModel<CanvasT, ImageT>, StyleModel<ThisT>  {

    double PI = 3.14159265358979;

    default double centerOffset(double boxSize, double innerSize) {
        return (boxSize - innerSize)/2;
    }

    /**
     * @return The css tag corresponding to the current diagram. For example, a PaneDiagram instance would return "pane".
     */
    String getTagName();
}
