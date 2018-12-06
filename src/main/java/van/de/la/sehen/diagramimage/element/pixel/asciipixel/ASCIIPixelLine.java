package van.de.la.sehen.diagramimage.element.pixel.asciipixel;

import van.de.la.sehen.diagramimage.asciidiagramimage.ASCIIImage;
import van.de.la.sehen.diagramimage.element.asciielement.ASCIIPortableDiagramLine;
import van.de.la.sehen.dimensionparticle.positionparticle.asciipositionparticle.ASCIIAbsoluteCoordinate;
import van.de.la.sehen.util.LinePrinting;

import java.awt.*;

public class ASCIIPixelLine implements ASCIIPixelDiagramElement, ASCIIPortableDiagramLine<ASCIIPixelLine> {
    private int x1;
    private int y1;
    private int x2;
    private int y2;
    private int width;
    private Color color;

    @Override
    public void paintOn(ASCIIImage image) {
        LinePrinting.draw(x1, y1, x2, y2, image);
    }

    @Override
    public ASCIIPixelLine setX1(ASCIIAbsoluteCoordinate x1) {
        this.x1 = x1.getValue();
        return this;
    }

    @Override
    public int getX1() {
        return x1;
    }

    @Override
    public ASCIIPixelLine setX2(ASCIIAbsoluteCoordinate x2) {
        this.x2 = x2.getValue();
        return this;
    }

    @Override
    public int getX2() {
        return x2;
    }

    @Override
    public ASCIIPixelLine setY1(ASCIIAbsoluteCoordinate y1) {
        this.y1 = y1.getValue();
        return this;
    }

    @Override
    public int getY1() {
        return y1;
    }

    @Override
    public ASCIIPixelLine setY2(ASCIIAbsoluteCoordinate y2) {
        this.y2 = y2.getValue();
        return this;
    }

    @Override
    public int getY2() {
        return y2;
    }

    @Override
    public ASCIIPixelLine setLineColor(Color color) {
        this.color = color;
        return this;
    }

    @Override
    public Color getLineColor() {
        return color;
    }
}
