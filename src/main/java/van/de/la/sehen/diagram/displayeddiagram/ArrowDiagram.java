package van.de.la.sehen.diagram.displayeddiagram;

import math.geom2d.Vector2D;
import van.de.la.sehen.diagram.prototypediagram.Diagram;
import van.de.la.sehen.diagram.readerinterface.DiagramNode;
import van.de.la.sehen.diagram.readerinterface.fieldreading.DiagramFieldsCollection;
import van.de.la.sehen.diagramattributeparticle.enumeratedparticle.ArrowStyle;
import van.de.la.sehen.diagramattributeparticle.enumeratedparticle.LineStyle;
import van.de.la.sehen.diagramimage.PortableDiagramCanvas;
import van.de.la.sehen.diagramstyle.DiagramStyle;
import van.de.la.sehen.dimensionparticle.positionparticle.AbsoluteCoordinate;
import van.de.la.sehen.dimensionparticle.positionparticle.CoordinateOffset;
import van.de.la.sehen.warning.WarningStream;

import java.awt.*;

public class ArrowDiagram extends Diagram {
    public static final int DASHED_LENGTH = 10;
    public static final int DASHED_SEPARATION = 16;

    public ArrowDiagram(Diagram parent, DiagramStyle style) {
        super(parent, style);
    }

    public ArrowDiagram(Diagram parent, DiagramFieldsCollection fields) {
        super(parent, fields);
    }

    public ArrowDiagram(Diagram parent, DiagramNode node) {
        this(parent, (DiagramFieldsCollection) node);
        if (node.hasChildren()) {
            WarningStream.putWarning("None empty arrow tag.", this);
        }
    }

    // TODO: The separation is interfered by the thickness of the line and thus need a correction
    private void drawLine(Graphics2D g, int FromX, int FromY, int ToX, int ToY) {
        g.setPaint(getLineColor());
        if (getLineStyle() == LineStyle.SOLID) {
            g.drawLine(FromX, FromY, ToX, ToY);
            return;
        }
        Vector2D line = new Vector2D(ToX - FromX, ToY - FromY);
        Vector2D arrowDirection = line.normalize().times(DASHED_LENGTH);
        for (Vector2D current = new Vector2D(); current.norm() < line.norm(); current = current.plus(arrowDirection)) {
            g.drawLine(FromX + (int)current.getX(), FromY + (int)current.getY(),
                    FromX + (int)(current.getX() + arrowDirection.getX()), FromY + (int) (current.getY() + arrowDirection.getY()));
            current = current.plus(arrowDirection.times((double)DASHED_SEPARATION / DASHED_LENGTH));
        }
    }

    private void drawLine(PortableDiagramCanvas canvas, AbsoluteCoordinate FromX, AbsoluteCoordinate FromY, AbsoluteCoordinate ToX, AbsoluteCoordinate ToY) {
        if (getLineStyle() == LineStyle.SOLID) {
            canvas.generateAndPushLine(FromX, FromY, ToX, ToY, getLineThickness(), getLineColor());
            return;
        }
        Vector2D line = new Vector2D(ToX.getValue() - FromX.getValue(), ToY.getValue() - FromY.getValue());
        Vector2D arrowDirection = line.normalize().times(DASHED_LENGTH);
        for (Vector2D current = new Vector2D(); current.norm() < line.norm(); current = current.plus(arrowDirection)) {
            canvas.generateAndPushLine(FromX.addByOffset(new CoordinateOffset((int)current.getX())),
                    FromY.addByOffset(new CoordinateOffset((int)current.getY())),
                    FromX.addByOffset(new CoordinateOffset((int)(current.getX() + arrowDirection.getX()))),
                    FromY.addByOffset(new CoordinateOffset ((int) (current.getY() + arrowDirection.getY()))),
                    getLineThickness(), getLineColor());
            current = current.plus(arrowDirection.times((double)DASHED_SEPARATION / DASHED_LENGTH));
        }
    }

    private void drawArrow(PortableDiagramCanvas canvas, AbsoluteCoordinate FromX, AbsoluteCoordinate FromY, AbsoluteCoordinate ToX, AbsoluteCoordinate ToY) {
        if (getArrowStyle() == ArrowStyle.NONE) {
            drawLine(canvas, FromX, FromY, ToX, ToY);
            return;
        }
        Vector2D tip = new Vector2D(ToX.getValue(), ToY.getValue());
        double height = 9 * (double)getLineThickness() / 2;
        double halfWidth = 3 * (double)getLineThickness() / 2;
        Vector2D arrowDirection = new Vector2D(ToX.getValue() - FromX.getValue(), ToY.getValue() - FromY.getValue()).normalize().times(-1);
        Vector2D perpendicularDirection = arrowDirection.rotate(PI / 2);
        Vector2D heightVector = arrowDirection.times(height);
        Vector2D halfBottomVector = perpendicularDirection.times(halfWidth);
        Vector2D vertex1 = heightVector.plus(halfBottomVector);
        Vector2D vertex2 = heightVector.minus(halfBottomVector);
        Vector2D globalVertex1 = vertex1.plus(tip);
        Vector2D globalVertex2 = vertex2.plus(tip);
        canvas.generateAndPushPolygon(getLineColor())
                .putXY(ToX, ToY)
                .putXY(new AbsoluteCoordinate((int)globalVertex1.getX()), new AbsoluteCoordinate((int)globalVertex1.getY()))
                .putXY(new AbsoluteCoordinate((int)globalVertex2.getX()), new AbsoluteCoordinate((int)globalVertex2.getY()));
        Vector2D newTip = tip.plus(heightVector);
        drawLine(canvas, FromX, FromY, new AbsoluteCoordinate((int)newTip.getX()), new AbsoluteCoordinate ((int)newTip.getY()));
    }

    @Override
    public void paintDiagram(PortableDiagramCanvas canvas) {
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
