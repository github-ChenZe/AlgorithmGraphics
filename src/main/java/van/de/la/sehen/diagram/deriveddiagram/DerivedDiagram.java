package van.de.la.sehen.diagram.deriveddiagram;

import math.geom2d.Vector2D;
import van.de.la.sehen.diagram.prototypediagram.CompositeDiagram;
import van.de.la.sehen.diagram.prototypediagram.Diagram;
import van.de.la.sehen.diagram.readerinterface.DiagramNode;
import van.de.la.sehen.diagramimage.PortableDiagramCanvas;
import van.de.la.sehen.diagramstyle.DiagramStyle;
import van.de.la.sehen.dimensionparticle.positionparticle.PositionOffset;
import van.de.la.sehen.warning.WarningStream;

public class DerivedDiagram<S extends Diagram> extends CompositeDiagram {
    private S skeleton;

    public S getSkeleton() {
        return skeleton;
    }

    public void setSkeleton(S skeleton) {
        this.skeleton = skeleton;
    }

    public DerivedDiagram(Diagram parent, DiagramStyle style) {
        super(parent, style);
    }

    public DerivedDiagram(Diagram parent, DiagramNode node) {
        super(parent, node);
    }

    @Override
    public Diagram getChildByIndex(int i) {
        if (i != 0) WarningStream.putWarning("Getting index > 0 from derived van.de.la.sehen.diagram.", this);
        return skeleton;
    }

    @Override
    public void layoutChildren() {
        skeleton.layout();
    }

    @Override
    public void setChildrenPosition() {
        PositionOffset z = PositionOffset.zero();
        skeleton.setPosition(PositionOffset.zero());
    }

    @Override
    public void calculateSize() {
        setWidth(skeleton.getWidth());
        setHeight(skeleton.getHeight());
    }

    @Override
    public void paintDiagram(PortableDiagramCanvas canvas) {
        skeleton.paintDiagram(canvas);
    }
}
