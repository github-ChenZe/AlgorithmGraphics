package van.de.la.sehen.diagram.displayeddiagram;

import van.de.la.sehen.diagram.displayeddiagram.model.PlotDiagramModel;
import van.de.la.sehen.diagram.prototypediagram.Diagram;
import van.de.la.sehen.diagram.readerinterface.DiagramNode;
import van.de.la.sehen.diagram.readerinterface.elementreading.DiagramElementsCollection;
import van.de.la.sehen.diagram.readerinterface.fieldreading.DiagramFieldsCollection;
import van.de.la.sehen.diagramimage.PortableDiagramCanvas;
import van.de.la.sehen.dimensionparticle.positionparticle.AbsoluteCoordinate;
import van.de.la.sehen.dimensionparticle.sizeparticle.IntDimensionComponent;
import van.de.la.sehen.mathematics.MathContext;
import van.de.la.sehen.mathematics.MathTree;
import van.de.la.sehen.mathematics.Parser;
import van.de.la.sehen.warning.WarningStream;

import java.io.IOException;

public class PlotDiagram extends Diagram implements PlotDiagramModel {
    private MathTree mathTree = null;

    public PlotDiagram(Diagram parent, DiagramElementsCollection elements, DiagramFieldsCollection fields) {
        super(parent, fields);
        String formula = elements.getContent();
        try {
            mathTree = new Parser().parse(formula);
        } catch (IOException e) {
            WarningStream.putWarning("Building MathTree failed.", this);
        }
    }

    public PlotDiagram(Diagram parent, DiagramNode node) {
        this(parent, node, node);
    }

    @Override
    public MathTree getMathTree() {
        return mathTree;
    }

    @Override
    public int screenWidth() {
        return getWidth().getValue();
    }

    @Override
    public int screenHeight() {
        return getHeight().getValue();
    }

    @Override
    public void paintDiagram(PortableDiagramCanvas canvas) {
        int[] ys = plotPixels(new MathContext().put(getFunctionAnimatorName(), this::getAnimationProgress));
        for (int i = 0; i < ys.length - 1; i++) {
            if (ys[i] < 0 || ys[i] >= screenHeight()) continue;
            canvas.generateAndPushLine(new AbsoluteCoordinate(i), new AbsoluteCoordinate(ys[i]),
                    new AbsoluteCoordinate(i + 1), new AbsoluteCoordinate(ys[i + 1]),
                    getLineThickness(), getLineColor());
        }
    }

    @Override
    public void calculateSize() {
        // The size should be set by the user, otherwise this graph should not show up
        setWidth(IntDimensionComponent.zero());
        setHeight(IntDimensionComponent.zero());
    }
}
