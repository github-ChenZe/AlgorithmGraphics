package van.de.la.sehen.diagram.displayeddiagram.asciidisplayeddiagram;

import van.de.la.sehen.diagram.prototypediagram.asciiprototypediagram.ASCIICompositeDiagram;
import van.de.la.sehen.diagram.prototypediagram.asciiprototypediagram.ASCIIDiagram;
import van.de.la.sehen.diagram.readerinterface.asciireaderinterface.ASCIIDiagramNode;
import van.de.la.sehen.diagram.readerinterface.diagrambuilder.asciidiagrambuilder.ASCIIDiagramBuilder;
import van.de.la.sehen.diagram.readerinterface.elementreading.DiagramElementsCollection;
import van.de.la.sehen.diagram.readerinterface.fieldreading.DiagramFieldsCollection;
import van.de.la.sehen.diagramimage.asciidiagramimage.ASCIICanvas;
import van.de.la.sehen.diagramstyle.DiagramStyle;
import van.de.la.sehen.dimensionparticle.positionparticle.asciipositionparticle.ASCIICoordinateOffset;
import van.de.la.sehen.dimensionparticle.sizeparticle.asciisizeparticle.ASCIIIntDimensionComponent;
import van.de.la.sehen.util.Util;

import java.util.ArrayList;
import java.util.List;

/* 仅仅边框本身是被绘制的 */
public class ASCIIVerticalAlignDiagram extends ASCIICompositeDiagram {
    public ASCIIVerticalAlignDiagram(ASCIIDiagram parent, DiagramStyle style) {
        super(parent, style);
    }

    public <T extends ASCIIDiagramBuilder<? extends ASCIIDiagram>> ASCIIVerticalAlignDiagram(ASCIIDiagram parent, DiagramElementsCollection<T> elements, DiagramFieldsCollection fields) {
        super(parent, fields);
        for (ASCIIDiagramBuilder<? extends ASCIIDiagram> child: elements.getChildren()) {
            push(child.buildDiagram(this));
        }
    }

    public <T extends ASCIIDiagramBuilder> ASCIIVerticalAlignDiagram(ASCIIDiagram parent, ASCIIDiagramNode<T, ASCIIDiagram> node) {
        this(parent, node, node);
    }

    private List<ASCIIDiagram> array = new ArrayList<>();
    private double maxLeft = 0;
    private double maxRight = 0;

    private void push(ASCIIDiagram diagram) {
        checkMembership(diagram);
        array.add(diagram);
    }

    private void calculateSizeForSides() {
        for (ASCIIDiagram child: array) {
            maxLeft = Util.max(child.getVerticalAlignAnchor().relativePosition(child).getX(), maxLeft);
            maxRight = Util.max(child.getWidth().getValue() - child.getVerticalAlignAnchor().relativePosition(child).getX(), maxRight);
        }
    }

    @Override
    public ASCIIDiagram getChildByIndex(int i) {
        return array.get(i);
    }

    @Override
    public void layoutChildren() {
        for (ASCIIDiagram child: array)
            child.layout();
    }

    @Override
    public void setChildrenPosition() {
        ASCIICoordinateOffset accumulatedY = ASCIICoordinateOffset.zero();
        for (ASCIIDiagram diagram: array) {
            diagram.setPosition(
                    new ASCIICoordinateOffset((int) (maxLeft - diagram.getVerticalAlignAnchor().relativePosition(diagram).getX())),
                    accumulatedY);
            accumulatedY = accumulatedY.add(diagram.getHeight().toOffset());
            accumulatedY = accumulatedY.add(new ASCIICoordinateOffset(getSeparation()));
        }
    }

    @Override
    public void paintDiagram(ASCIICanvas canvas) {
        for (ASCIIDiagram diagram: array) {
            diagram.paintDiagram(canvas);
        }
    }

    @Override
    public void calculateSize() {
        ASCIICoordinateOffset height = ASCIICoordinateOffset.zero();
        for (ASCIIDiagram child: array) {
            height = height.add(child.getHeight().toOffset());
            height = height.add(new ASCIICoordinateOffset(getSeparation()));
        }
        if (height.getValue() > 0) {
            height = height.add(new ASCIICoordinateOffset(-getSeparation()));
        }
        calculateSizeForSides();
        setWidth(new ASCIIIntDimensionComponent((int)(maxLeft + maxRight)));
        setHeight(new ASCIIIntDimensionComponent(height.getValue()));
    }
}
