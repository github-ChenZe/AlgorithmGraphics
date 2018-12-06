package van.de.la.sehen.diagram.displayeddiagram.asciidisplayeddiagram;

import van.de.la.sehen.diagram.prototypediagram.asciiprototypediagram.ASCIIDiagram;
import van.de.la.sehen.diagram.readerinterface.asciireaderinterface.ASCIIDiagramNode;
import van.de.la.sehen.diagram.readerinterface.fieldreading.DiagramFieldsCollection;
import van.de.la.sehen.diagramimage.asciidiagramimage.ASCIICanvas;
import van.de.la.sehen.diagramstyle.DiagramStyle;
import van.de.la.sehen.dimensionparticle.positionparticle.asciipositionparticle.ASCIIAbsoluteCoordinate;
import van.de.la.sehen.warning.WarningStream;

public class ASCIIArrowDiagram extends ASCIIDiagram {
    public static final int DASHED_LENGTH = 10;
    public static final int DASHED_SEPARATION = 16;

    public ASCIIArrowDiagram(ASCIIDiagram parent, DiagramStyle style) {
        super(parent, style);
    }

    public ASCIIArrowDiagram(ASCIIDiagram parent, DiagramFieldsCollection fields) {
        super(parent, fields);
    }

    public ASCIIArrowDiagram(ASCIIDiagram parent, ASCIIDiagramNode node) {
        this(parent, (DiagramFieldsCollection) node);
        if (node.hasChildren()) {
            WarningStream.putWarning("None empty arrow tag.", this);
        }
    }

    private void drawArrow(ASCIICanvas canvas, ASCIIAbsoluteCoordinate FromX, ASCIIAbsoluteCoordinate FromY, ASCIIAbsoluteCoordinate ToX, ASCIIAbsoluteCoordinate ToY) {
        canvas.generateAndPushLine(FromX, FromY, ToX, ToY, getLineColor());
    }

    @Override
    public void paintDiagram(ASCIICanvas canvas) {
        if (!getDraw()) return;
        drawArrow(canvas, getFrom().getAbsoluteX(),
                getFrom().getAbsoluteY(),
                getTo().getAbsoluteX(),
                getTo().getAbsoluteY());
    }

    @Override
    public void calculateSize() {

    }
}
