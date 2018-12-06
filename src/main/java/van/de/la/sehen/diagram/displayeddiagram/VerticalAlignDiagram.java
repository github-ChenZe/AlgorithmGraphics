package van.de.la.sehen.diagram.displayeddiagram;

import van.de.la.sehen.diagram.prototypediagram.Diagram;
import van.de.la.sehen.diagram.prototypediagram.MatrixDiagram;
import van.de.la.sehen.diagram.readerinterface.DiagramNode;
import van.de.la.sehen.diagram.readerinterface.diagrambuilder.DiagramBuilder;
import van.de.la.sehen.diagram.readerinterface.elementreading.DiagramElementsCollection;
import van.de.la.sehen.diagram.readerinterface.fieldreading.DiagramFieldsCollection;
import van.de.la.sehen.diagramimage.PortableDiagramCanvas;
import van.de.la.sehen.diagramstyle.DiagramStyle;
import van.de.la.sehen.dimensionparticle.positionparticle.CoordinateOffset;
import van.de.la.sehen.dimensionparticle.sizeparticle.IntDimensionComponent;
import van.de.la.sehen.util.Util;

import java.util.ArrayList;
import java.util.List;

/* 仅仅边框本身是被绘制的 */
public class VerticalAlignDiagram extends MatrixDiagram {
    public VerticalAlignDiagram(Diagram parent, DiagramStyle style) {
        super(parent, style);
    }

    public <T extends DiagramBuilder<? extends Diagram>> VerticalAlignDiagram(Diagram parent, DiagramElementsCollection<T> elements, DiagramFieldsCollection fields) {
        super(parent, fields);
        for (DiagramBuilder<? extends Diagram> child: elements.getChildren()) {
            push(child.buildDiagram(this));
        }
    }

    public <T extends DiagramBuilder> VerticalAlignDiagram(Diagram parent, DiagramNode<T, Diagram> node) {
        this(parent, node, node);
    }

    private List<Diagram> array = new ArrayList<>();
    private double maxLeft = 0;
    private double maxRight = 0;

    private void push(Diagram diagram) {
        checkMembership(diagram);
        array.add(diagram);
    }

    private void calculateSizeForSides() {
        for (Diagram child: array) {
            maxLeft = Util.max(child.getVerticalAlignAnchor().relativePosition(child).getX(), maxLeft);
            maxRight = Util.max(child.getWidth().getValue() - child.getVerticalAlignAnchor().relativePosition(child).getX(), maxRight);
        }
    }

    @Override
    public Diagram getChildByIndex(int i) {
        return array.get(i);
    }

    @Override
    public void layoutChildren() {
        for (Diagram child: array)
            child.layout();
    }

    @Override
    public void setChildrenPosition() {
        CoordinateOffset accumulatedY = CoordinateOffset.zero();
        for (Diagram diagram: array) {
            diagram.setPosition(
                    new CoordinateOffset((int) (maxLeft - diagram.getVerticalAlignAnchor().relativePosition(diagram).getX())),
                    accumulatedY);
            accumulatedY = accumulatedY.add(diagram.getHeight().toOffset());
            accumulatedY = accumulatedY.add(new CoordinateOffset(getSeparation()));
        }
    }

    @Override
    public void paintDiagram(PortableDiagramCanvas canvas) {
        for (Diagram diagram: array) {
            diagram.paintDiagram(canvas);
        }
    }

    @Override
    public void calculateSize() {
        CoordinateOffset height = CoordinateOffset.zero();
        for (Diagram child: array) {
            height = height.add(child.getHeight().toOffset());
            height = height.add(new CoordinateOffset(getSeparation()));
        }
        if (height.getValue() > 0) {
            height = height.add(new CoordinateOffset(-getSeparation()));
        }
        calculateSizeForSides();
        setWidth(new IntDimensionComponent((int)(maxLeft + maxRight)));
        setHeight(new IntDimensionComponent(height.getValue()));
    }
}
