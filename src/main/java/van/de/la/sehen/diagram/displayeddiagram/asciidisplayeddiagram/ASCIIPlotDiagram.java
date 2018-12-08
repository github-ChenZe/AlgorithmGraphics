package van.de.la.sehen.diagram.displayeddiagram.asciidisplayeddiagram;

import van.de.la.sehen.diagram.displayeddiagram.model.PlotDiagramModel;
import van.de.la.sehen.diagram.prototypediagram.asciiprototypediagram.ASCIIDiagram;
import van.de.la.sehen.diagram.readerinterface.DiagramNode;
import van.de.la.sehen.diagram.readerinterface.asciireaderinterface.ASCIIDiagramNode;
import van.de.la.sehen.diagram.readerinterface.elementreading.DiagramElementsCollection;
import van.de.la.sehen.diagram.readerinterface.fieldreading.DiagramFieldsCollection;
import van.de.la.sehen.diagramimage.PortableDiagramCanvas;
import van.de.la.sehen.diagramimage.asciidiagramimage.ASCIICanvas;
import van.de.la.sehen.diagramimage.asciidiagramimage.ASCIIPortableDiagramCanvas;
import van.de.la.sehen.dimensionparticle.positionparticle.AbsoluteCoordinate;
import van.de.la.sehen.dimensionparticle.positionparticle.asciipositionparticle.ASCIIAbsoluteCoordinate;
import van.de.la.sehen.dimensionparticle.sizeparticle.IntDimensionComponent;
import van.de.la.sehen.dimensionparticle.sizeparticle.asciisizeparticle.ASCIIIntDimensionComponent;
import van.de.la.sehen.mathematics.MathTree;
import van.de.la.sehen.mathematics.Parser;
import van.de.la.sehen.warning.WarningStream;

import java.io.IOException;

public class ASCIIPlotDiagram extends ASCIIDiagram implements PlotDiagramModel {
    private MathTree mathTree = null;

    public ASCIIPlotDiagram(ASCIIDiagram parent, DiagramElementsCollection elements, DiagramFieldsCollection fields) {
        super(parent, fields);
        String formula = elements.getContent();
        try {
            mathTree = new Parser().parse(formula);
        } catch (IOException e) {
            WarningStream.putWarning("Building MathTree failed.", this);
        }
    }

    public ASCIIPlotDiagram(ASCIIDiagram parent, ASCIIDiagramNode node) {
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
    public void paintDiagram(ASCIICanvas canvas) {
        int[] ys = plotPixels();
        for (int i = 0; i < ys.length - 1; i++) {
            if (ys[i] < 0 || ys[i] >= screenHeight()) continue;
            canvas.generateAndPushLine(new ASCIIAbsoluteCoordinate(i), new ASCIIAbsoluteCoordinate(ys[i]),
                    new ASCIIAbsoluteCoordinate(i + 1), new ASCIIAbsoluteCoordinate(ys[i + 1]),
                    getLineColor());
        }
    }

    @Override
    public void calculateSize() {
        // The size should be set by the user, otherwise this graph should not show up
        setWidth(ASCIIIntDimensionComponent.zero());
        setHeight(ASCIIIntDimensionComponent.zero());
    }
}
