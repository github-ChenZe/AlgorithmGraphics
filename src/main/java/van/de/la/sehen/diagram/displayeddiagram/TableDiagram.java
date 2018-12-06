package van.de.la.sehen.diagram.displayeddiagram;

import jdk.jshell.Diag;
import van.de.la.sehen.diagram.prototypediagram.Diagram;
import van.de.la.sehen.diagram.prototypediagram.MatrixDiagram;
import van.de.la.sehen.diagram.pseudodiagram.FixedPseudoDiagram;
import van.de.la.sehen.diagram.pseudodiagram.PseudoDiagram;
import van.de.la.sehen.diagram.readerinterface.DiagramNode;
import van.de.la.sehen.diagram.readerinterface.diagrambuilder.DiagramBuilder;
import van.de.la.sehen.diagram.readerinterface.elementreading.DiagramElementsCollection;
import van.de.la.sehen.diagram.readerinterface.fieldreading.DiagramFieldsCollection;
import van.de.la.sehen.diagram.readerinterface.fieldreading.DiagramFieldsCollectionBuilder;
import van.de.la.sehen.diagramattributeparticle.StyleInheritWrapper;
import van.de.la.sehen.diagramattributeparticle.diagramparticle.PseudoDiagramDirectAccessor;
import van.de.la.sehen.diagramattributeparticle.enumeratedparticle.ArrowStyle;
import van.de.la.sehen.diagramimage.PortableDiagramCanvas;
import van.de.la.sehen.diagramstyle.DiagramStyle;
import van.de.la.sehen.dimensionparticle.positionparticle.CoordinateOffset;
import van.de.la.sehen.dimensionparticle.positionparticle.PositionOffset;
import van.de.la.sehen.dimensionparticle.sizeparticle.IntDimensionComponent;
import van.de.la.sehen.util.Tuple;
import van.de.la.sehen.util.Util;
import van.de.la.sehen.warning.WarningStream;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/* 仅仅边框本身是被绘制的 */
public class TableDiagram extends MatrixDiagram {
    public TableDiagram(Diagram parent, DiagramStyle style) {
        super(parent, style);
    }

    public <T extends DiagramBuilder<? extends Diagram>, S extends DiagramElementsCollection<T>> TableDiagram(Diagram parent, DiagramElementsCollection<S> elements, DiagramFieldsCollection fields) {
        super(parent, fields);
        while (elements.hasChildren()) {
            DiagramElementsCollection<T> row = elements.getChildren().poll();
            List<Diagram> diagramRow = new ArrayList<>();
            while (row.hasChildren()) {
                DiagramBuilder<? extends Diagram> cell = row.getChildren().poll();
                Diagram childDiagram = cell.buildDiagram(this);
                if ((! (childDiagram instanceof EmptyDiagram)) || !getIgnoreEmpty())
                    diagramRow.add(childDiagram);
            }
            array.add(diagramRow);
        }
    }

    public <T extends DiagramBuilder, S extends DiagramElementsCollection<T>> TableDiagram(Diagram parent, DiagramNode<S, Diagram> node) {
        this(parent, node, node);
    }

    private List<List<Diagram>> array = new ArrayList<>();
    private List<ArrowDiagram> arrows = new ArrayList<>();



    public String getPseudoName(int i, int j) {
        return "(" + i + "," + j + ")";
    }

    public List<Double> getPseudoCoordinates(List<Double> sides) {
        List<Double> pseudos = new ArrayList<>();
        double x = sideDelta() / 2;
        pseudos.add(x);
        for (int i = 0; i < sides.size(); i++) {
            double delta = (i == 0 || i + 1 == sides.size()) ? (sideDelta() + innerDelta()) / 2 : innerDelta();
            x += delta + sides.get(i);
            pseudos.add(x);
        }
        return pseudos;
    }

    private List<Tuple<PseudoDiagram, PseudoDiagram>> setPseudos(List<Double> widths, List<Double> heights) {
        List<Tuple<PseudoDiagram, PseudoDiagram>> lineEnds = new ArrayList<>();
        List<Double> pseudoX = getPseudoCoordinates(widths);
        List<Double> pseudoY = getPseudoCoordinates(heights);
        for (int i = 0; i < pseudoX.size(); i++)
            for (int j = 0; j < pseudoY.size(); j++) {
                pushPseudo(getPseudoName(i, j), new FixedPseudoDiagram(this, null, pseudoX.get(i), pseudoY.get(j)));
            }
        for (int i = 0; i < pseudoX.size(); i++) {
            lineEnds.add(new Tuple<>(getPseudo(getPseudoName(i, pseudoY.size() - 1)), getPseudo(getPseudoName(i, 0))));
        }
        for (int j = 0; j < pseudoY.size(); j++) {
            lineEnds.add(new Tuple<>(getPseudo(getPseudoName(pseudoX.size() - 1, j)), getPseudo(getPseudoName(0, j))));
        }
        return lineEnds;
    }

    @Override
    public Diagram getChildByIndex(int i) {
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
        return getChildByIndex(targetRow, targetIndex);
    }

    public Diagram getChildByIndex(int row, int column) {
        return array.get(row).get(column);
    }

    @Override
    public void layoutChildren() {
        for (List<Diagram> list: array)
            for (Diagram diagram: list)
                diagram.layout();
    }

    @Override
    public void setChildrenPosition() {
        List<Double> widths = getGridSizes(array).x;
        List<Double> heights = getGridSizes(array).y;
        List<List<PositionOffset>> expectedPosition = getExpectedChildrenPosition(widths, heights, array);
        List<Double> genuineWidths;
        List<Double> genuineHeights;
        List<List<PositionOffset>> genuinePosition;
        if (!getSwap()) {
            genuineWidths = widths;
            genuineHeights = heights;
            genuinePosition = expectedPosition;
        } else { // Swap on
            List<List<Diagram>> swappedArray = Util.swapped(array, getSwapRowFrom(), getSwapColumnFrom(), getSwapRowTo(), getSwapColumnTo());
            List<Double> swappedWidths = getGridSizes(swappedArray).x;
            List<Double> swappedHeights = getGridSizes(swappedArray).y;
            List<List<PositionOffset>> swappedPosition = Util.swapped(getExpectedChildrenPosition(swappedWidths, swappedHeights, swappedArray), getSwapRowFrom(), getSwapColumnFrom(), getSwapRowTo(), getSwapColumnTo());
            genuineWidths = Util.weightedAverageOfDoubles(swappedWidths, widths, getSwapProgress());
            genuineHeights = Util.weightedAverageOfDoubles(swappedHeights, heights, getSwapProgress());
            genuinePosition = Util.weightedAverageOfOffsetsArray(swappedPosition, expectedPosition, getSwapProgress());
        }
        for (int i = 0; i < expectedPosition.size(); i++)
            for (int j = 0; j < expectedPosition.get(i).size(); j++)
                array.get(i).get(j).setPosition(genuinePosition.get(i).get(j));
        List<Tuple<PseudoDiagram, PseudoDiagram>> lineEnds = setPseudos(genuineWidths, genuineHeights);
        arrows = new ArrayList<>(); // Animation may redraw on the same diagram multiple times
        if (getGrid()) for (Tuple<PseudoDiagram, PseudoDiagram> tuple: lineEnds) {
            arrows.add(new ArrowDiagram(this,
                    new DiagramFieldsCollectionBuilder()
                            .putAttribute(FROM, new PseudoDiagramDirectAccessor(tuple.x).toCluster())
                            .putAttribute(TO, new PseudoDiagramDirectAccessor(tuple.y).toCluster())
                            .putAttribute(ARROW_STYLE, ArrowStyle.NONE.toCluster())
                            .putAttribute(LINE_COLOR, new StyleInheritWrapper(()->this, LINE_COLOR))
                            .putAttribute(LINE_THICKNESS, new StyleInheritWrapper(()->this, LINE_THICKNESS))));
        }
    }

    private List<List<PositionOffset>> getExpectedChildrenPosition(List<Double> widths, List<Double> heights, List<List<Diagram>> array) {
        List<List<PositionOffset>> result = new ArrayList<>();
        int accumulatedY = sideDelta();
        for (int i = 0; i < array.size(); i++) { // Row loop
            List<PositionOffset> rowResult = new ArrayList<>();
            List<Diagram> row = array.get(i);
            int accumulatedX = sideDelta();
            for (int j = 0; j < row.size(); j++) { // Column loop
                Diagram child = row.get(j);
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
                rowResult.add(new PositionOffset((int) childX, (int) childY));
                accumulatedX += widths.get(j) + innerDelta();
            }
            result.add(rowResult);
            accumulatedY += heights.get(i) + innerDelta();
        }
        return result;
    }

    @Override
    public void paintDiagram(PortableDiagramCanvas canvas) {
        for (List<Diagram> row: array) {
            for (Diagram element: row) {
                element.paintDiagram(canvas);
            }
        }
        for (ArrowDiagram arrowDiagram: arrows) {
            arrowDiagram.paintDiagram(canvas);
        }
    }

    // size calculation related

    private Double calculateSizeForRow(List<Diagram> row, List<Double> widths) {
        double height = 0;
        int index = 0;
        for (Diagram child: row) {
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
        return Util.sum(sides) + (sides.size() - 1) * innerDelta() + 2 * sideDelta();
    }

    /**
     * @return The widths and heights of rows and columns to fit cells in this table.
     */
    public Tuple<List<Double>, List<Double>> getGridSizes(List<List<Diagram>> array) {
        List<Double> heights = new ArrayList<>();
        List<Double> widths = new ArrayList<>();
        for (List<Diagram> row: array) {
            double height = calculateSizeForRow(row, widths);
            heights.add(height);
        }
        return new Tuple<>(widths, heights);
    }

    @Override
    public void calculateSize() {
        Tuple<List<Double>, List<Double>> widthsAndHeights = getGridSizes(array);
        if (getSwap()) {
            Tuple<List<Double>, List<Double>> swappedWidthsAndHeights = getGridSizes(Util.swapped(array, getSwapRowFrom(), getSwapColumnFrom(), getSwapRowTo(), getSwapColumnTo()));
            widthsAndHeights = new Tuple<>(Util.weightedAverageOfDoubles(swappedWidthsAndHeights.x, widthsAndHeights.x, getSwapProgress()),
                    Util.weightedAverageOfDoubles(swappedWidthsAndHeights.y, widthsAndHeights.y, getSwapProgress()));
        }
        setHeight(new IntDimensionComponent((int) sumSide(widthsAndHeights.y)));
        setWidth(new IntDimensionComponent((int) sumSide(widthsAndHeights.x)));
    }
}
