package van.de.la.sehen.diagram.displayeddiagram.asciidisplayeddiagram;

import van.de.la.sehen.diagram.displayeddiagram.ArrowDiagram;
import van.de.la.sehen.diagram.prototypediagram.asciiprototypediagram.ASCIICompositeDiagram;
import van.de.la.sehen.diagram.prototypediagram.asciiprototypediagram.ASCIIDiagram;
import van.de.la.sehen.diagram.pseudodiagram.ASCIIFixedPseudoDiagram;
import van.de.la.sehen.diagram.readerinterface.asciireaderinterface.ASCIIDiagramNode;
import van.de.la.sehen.diagram.readerinterface.diagrambuilder.asciidiagrambuilder.ASCIIDiagramBuilder;
import van.de.la.sehen.diagram.readerinterface.elementreading.DiagramElementsCollection;
import van.de.la.sehen.diagram.readerinterface.fieldreading.DiagramFieldsCollection;
import van.de.la.sehen.diagramimage.asciidiagramimage.ASCIICanvas;
import van.de.la.sehen.diagramimage.element.asciielement.TableDrawingElement;
import van.de.la.sehen.diagramstyle.DiagramStyle;
import van.de.la.sehen.dimensionparticle.positionparticle.asciipositionparticle.ASCIIAbsoluteCoordinate;
import van.de.la.sehen.dimensionparticle.positionparticle.asciipositionparticle.ASCIICoordinateOffset;
import van.de.la.sehen.dimensionparticle.positionparticle.asciipositionparticle.ASCIIPositionOffset;
import van.de.la.sehen.dimensionparticle.sizeparticle.asciisizeparticle.ASCIIIntDimensionComponent;
import van.de.la.sehen.util.Util;
import van.de.la.sehen.warning.WarningStream;

import java.util.ArrayList;
import java.util.List;

/* 仅仅边框本身是被绘制的 */
public class ASCIITableDiagram extends ASCIICompositeDiagram {
    public ASCIITableDiagram(ASCIIDiagram parent, DiagramStyle style) {
        super(parent, style);
    }

    public <T extends ASCIIDiagramBuilder<? extends ASCIIDiagram>, S extends DiagramElementsCollection<T>> ASCIITableDiagram(ASCIIDiagram parent, DiagramElementsCollection<S> elements, DiagramFieldsCollection fields) {
        super(parent, fields);
        while (elements.hasChildren()) {
            DiagramElementsCollection<T> row = elements.getChildren().poll();
            while (row.hasChildren()) {
                ASCIIDiagramBuilder<? extends ASCIIDiagram> cell = row.getChildren().poll();
                ASCIIDiagram childDiagram = cell.buildDiagram(this);
                if ((! (childDiagram instanceof ASCIIEmptyDiagram)) || !getIgnoreEmpty())
                    push(childDiagram);
            }
            pushRow();
        }
    }

    public <T extends ASCIIDiagramBuilder, S extends DiagramElementsCollection<T>> ASCIITableDiagram(ASCIIDiagram parent, ASCIIDiagramNode<S, ASCIIDiagram> node) {
        this(parent, node, node);
    }

    private List<ASCIIDiagram> currentRow = null;
    private List<List<ASCIIDiagram>> array = new ArrayList<>();
    private List<Double> heights;
    private List<Double> widths;
    private List<ArrowDiagram> arrows = new ArrayList<>();

    private List<ASCIICoordinateOffset> horizontals = new ArrayList<>();
    private List<ASCIICoordinateOffset> verticals = new ArrayList<>();

    private void push(ASCIIDiagram diagram) {
        checkMembership(diagram);
        if (currentRow == null) {
            currentRow = new ArrayList<>();
        }
        currentRow.add(diagram);
    }

    private void pushRow() {
        if (currentRow != null) {
            array.add(currentRow);
            currentRow = null;
        }
    }

    /**
     The size are all inner size.
     */
    private Double calculateSizeForRow(List<ASCIIDiagram> row) {
        double height = 0;
        int index = 0;
        for (ASCIIDiagram child: row) {
            if (child.getHeight().getValue() > height)
                height = child.getHeight().getValue();
            Util.updateIfGreater(widths, index, child.getWidth().getValue());
            index++;
        }
        return height;
    }

    private int innerDelta() {
        return getGrid() ? getLineThickness() : getSeparation();
    }

    private int sideDelta() {
        return getGrid() ? getLineThickness() : 0;
    }

    private double sumSide(List<Double> sides) {
        // TODO: the case where table has no element was not handled well
        return Util.sum(sides) + (sides.size() > 0 ? (sides.size() - 1) : 0) * innerDelta() + 2 * sideDelta();
    }

    public String getPseudoName(int i, int j) {
        return "(" + i + "," + j + ")";
    }

    public List<Integer> getPseudoCoordinates(List<Double> sides) {
        List<Integer> pseudos = new ArrayList<>();
        int x = sideDelta() / 2;
        pseudos.add(x);
        for (int i = 0; i < sides.size(); i++) {
            int delta = (i == 0 || i + 1 == sides.size()) ? (sideDelta() + innerDelta()) / 2 : innerDelta();
            x += delta + sides.get(i);
            pseudos.add(x);
        }
        return pseudos;
    }

    public void setPseudos() {
        List<Integer> pseudoX = getPseudoCoordinates(widths);
        List<Integer> pseudoY = getPseudoCoordinates(heights);
        for (int i = 0; i < pseudoX.size(); i++)
            for (int j = 0; j < pseudoY.size(); j++)
                pushPseudo(getPseudoName(i, j), new ASCIIFixedPseudoDiagram(this, null, pseudoX.get(i), pseudoY.get(j)));
        for (Integer x: pseudoX) {
            verticals.add(new ASCIICoordinateOffset(x));
        }
        for (Integer y: pseudoY) {
            horizontals.add(new ASCIICoordinateOffset(y));
        }
    }

    @Override
    public ASCIIDiagram getChildByIndex(int i) {
        int lastAccumulatedIndex = 0;
        int accumulatedIndex = 0;
        int targetRow = 0;
        for (int j = 0; j < array.size(); j++) {
            accumulatedIndex += array.get(j).size();
            if (accumulatedIndex > i) {
                targetRow = j;
                break;
            }
            lastAccumulatedIndex += accumulatedIndex;
        }
        int targetIndex = i - lastAccumulatedIndex;
        return array.get(targetRow).get(targetIndex);
    }

    @Override
    public void layoutChildren() {
        if (currentRow != null) {
            WarningStream.putWarning("Non empty currentRow in table, miss calling to 'pushRow'?", this);
        }
        for (List<ASCIIDiagram> list: array)
            for (ASCIIDiagram diagram: list)
                diagram.layout();
    }

    @Override
    public void setChildrenPosition() {
        int accumulatedY = sideDelta();
        for (int i = 0; i < array.size(); i++) { // Row loop
            List<ASCIIDiagram> row = array.get(i);
            int accumulatedX = sideDelta();
            for (int j = 0; j < row.size(); j++) { // Column loop
                ASCIIDiagram child = row.get(j);
                double childX;
                double childY;
                switch (getHorizontalAlign()) {
                    case LEFT:
                        childX = accumulatedX;
                        break;
                    case CENTER:
                        childX = accumulatedX + centerOffset(widths.get(j), child.getWidth().getValue());
                        break;
                    case RIGHT:
                        childX = accumulatedX + widths.get(j) - child.getWidth().getValue();
                        break;
                    default:
                        WarningStream.putWarning("Unknown HorizontalAlign option '" + getHorizontalAlign() + "'.", this);
                        childX = accumulatedX + centerOffset(widths.get(j), child.getWidth().getValue());
                }
                switch (getVerticalAlign()) {
                    case TOP:
                        childY = accumulatedY;
                        break;
                    case CENTER:
                        childY = accumulatedY + centerOffset(heights.get(i), child.getHeight().getValue());
                        break;
                    case BOTTOM:
                        childY = accumulatedY + heights.get(i) - child.getHeight().getValue();
                        break;
                    default:
                        WarningStream.putWarning("Unknown VerticalAlign option '" + getVerticalAlign() + "'.", this);
                        childY = accumulatedX + centerOffset(widths.get(j), child.getWidth().getValue());
                }
                child.setPosition(new ASCIIPositionOffset((int) childX, (int) childY));
                accumulatedX += widths.get(j) + innerDelta();
            }
            accumulatedY += heights.get(i) + innerDelta();
        }
        setPseudos();
    }

    @Override
    public void paintDiagram(ASCIICanvas canvas) {
        for (List<ASCIIDiagram> row: array) {
            for (ASCIIDiagram element: row) {
                element.paintDiagram(canvas);
            }
        }
        if (!getGrid()) return;
        // Calculation must be delayed till here since table's position may not have been calculated
        List<ASCIIAbsoluteCoordinate> absoluteXs = new ArrayList<>();
        List<ASCIIAbsoluteCoordinate> absoluteYs = new ArrayList<>();
        for (ASCIICoordinateOffset offset: verticals) {
            absoluteXs.add(getAbsoluteX().addByOffset(offset));
        }
        for (ASCIICoordinateOffset offset: horizontals) {
            absoluteYs.add(getAbsoluteY().addByOffset(offset));
        }
        ASCIIAbsoluteCoordinate beginX = absoluteXs.get(0);
        ASCIIAbsoluteCoordinate beginY = absoluteYs.get(0);
        ASCIIAbsoluteCoordinate endX = absoluteXs.get(absoluteXs.size() - 1);
        ASCIIAbsoluteCoordinate endY = absoluteYs.get(absoluteYs.size() - 1);
        for (ASCIIAbsoluteCoordinate y: absoluteYs) // VERTICLE LINES
            canvas.generateAndPushTableHorizontalLine(y, beginX.addByOffset(new ASCIICoordinateOffset(1)), endX, getLineColor());
        for (ASCIIAbsoluteCoordinate x: absoluteXs)  // HORIZONTAL LINES
            canvas.generateAnsPushTableVerticalLine(x, beginY.addByOffset(new ASCIICoordinateOffset(1)), endY, getLineColor());
        for (ASCIIAbsoluteCoordinate x: absoluteXs.subList(1, absoluteXs.size() - 1))
            for (ASCIIAbsoluteCoordinate y: absoluteYs.subList(1, absoluteYs.size() - 1))  // INTERSECTIONS
                canvas.generateAndPushTableDrawing(x, y, TableDrawingElement.C,getLineColor());
        for (ASCIIAbsoluteCoordinate y: absoluteYs.subList(1, absoluteYs.size() - 1))
            canvas.generateAndPushTableDrawing(beginX, y, TableDrawingElement.L,getLineColor());
        for (ASCIIAbsoluteCoordinate y: absoluteYs.subList(1, absoluteYs.size() - 1))
            canvas.generateAndPushTableDrawing(endX, y, TableDrawingElement.R,getLineColor());
        for (ASCIIAbsoluteCoordinate x: absoluteXs.subList(1, absoluteXs.size() - 1))
            canvas.generateAndPushTableDrawing(x, beginY, TableDrawingElement.T,getLineColor());
        for (ASCIIAbsoluteCoordinate x: absoluteXs.subList(1, absoluteXs.size() - 1))
            canvas.generateAndPushTableDrawing(x, endY, TableDrawingElement.B,getLineColor());
        canvas.generateAndPushTableDrawing(beginX, beginY, TableDrawingElement.TL,getLineColor());
        canvas.generateAndPushTableDrawing(endX, beginY, TableDrawingElement.TR,getLineColor());
        canvas.generateAndPushTableDrawing(beginX, endY, TableDrawingElement.BL,getLineColor());
        canvas.generateAndPushTableDrawing(endX, endY, TableDrawingElement.BR,getLineColor());
    }

    @Override
    public void calculateSize() {
        heights = new ArrayList<>();
        widths = new ArrayList<>();
        for (List<ASCIIDiagram> row: array) {
            double height = calculateSizeForRow(row);
            heights.add(height);
        }
        setHeight(new ASCIIIntDimensionComponent((int) sumSide(heights)));
        setWidth(new ASCIIIntDimensionComponent((int) sumSide(widths)));
    }
}
