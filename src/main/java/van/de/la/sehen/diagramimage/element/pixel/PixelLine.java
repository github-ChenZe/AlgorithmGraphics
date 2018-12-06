package van.de.la.sehen.diagramimage.element.pixel;

import van.de.la.sehen.diagramimage.element.PortableDiagramLine;
import van.de.la.sehen.dimensionparticle.positionparticle.AbsoluteCoordinate;
import van.de.la.sehen.warning.WarningStream;

import java.awt.*;

public class PixelLine implements PixelDiagramElement, PortableDiagramLine<PixelLine> {
    private int x1;
    private int y1;
    private int x2;
    private int y2;
    private int width;
    private Color color;

    @Override
    public void paintOn(Graphics2D graphics) {
        graphics.setStroke(new BasicStroke(getLineWidth()));
        graphics.setPaint(getLineColor());
        graphics.drawLine(getX1(), getY1(), getX2(), getY2());
    }

    @Override
    public PixelLine setX1(AbsoluteCoordinate x1) {
        this.x1 = x1.getValue();
        return this;
    }

    @Override
    public int getX1() {
        return x1;
    }

    @Override
    public PixelLine setX2(AbsoluteCoordinate x2) {
        this.x2 = x2.getValue();
        return this;
    }

    @Override
    public int getX2() {
        return x2;
    }

    @Override
    public PixelLine setY1(AbsoluteCoordinate y1) {
        this.y1 = y1.getValue();
        return this;
    }

    @Override
    public int getY1() {
        return y1;
    }

    @Override
    public PixelLine setY2(AbsoluteCoordinate y2) {
        this.y2 = y2.getValue();
        return this;
    }

    @Override
    public int getY2() {
        return y2;
    }

    @Override
    public PixelLine setLineWidth(int width) {
        this.width = width;
        return this;
    }

    @Override
    public int getLineWidth() {
        return width;
    }

    @Override
    public PixelLine setLineColor(Color color) {
        this.color = color;
        return this;
    }

    @Override
    public Color getLineColor() {
        return color;
    }
}
