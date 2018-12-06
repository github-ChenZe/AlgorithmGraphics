package van.de.la.sehen.diagramimage.element.pixel.asciipixel;

import van.de.la.sehen.diagramimage.asciidiagramimage.ASCIIImage;
import van.de.la.sehen.diagramimage.element.asciielement.ASCIIPortableDiagramTableVerticalLine;
import van.de.la.sehen.diagramimage.element.asciielement.TableDrawingElement;
import van.de.la.sehen.dimensionparticle.positionparticle.asciipositionparticle.ASCIIAbsoluteCoordinate;

import java.awt.*;

public class ASCIIPixelTableVerticalLine implements ASCIIPixelDiagramElement, ASCIIPortableDiagramTableVerticalLine<ASCIIPixelTableVerticalLine> {
    private int startY;
    private int endY;
    private int x;
    private Color color;

    @Override
    public void paintOn(ASCIIImage image) {
        if (startY > endY) {
            int tmp = endY;
            endY = startY;
            startY = tmp;
        }
        for (int y = startY; y < endY; y++) {
            // DON'T USE getVerticalChar here, or override behaviour would be weird.
            image.setChar(y, x, TableDrawingElement.VERTICAL);
        }
    }

    @Override
    public ASCIIPixelTableVerticalLine setStartY(ASCIIAbsoluteCoordinate y) {
        this.startY = y.getValue();
        return this;
    }

    @Override
    public int getStartY() {
        return startY;
    }

    @Override
    public ASCIIPixelTableVerticalLine setEndY(ASCIIAbsoluteCoordinate y) {
        this.endY = y.getValue();
        return this;
    }

    @Override
    public int getEndY() {
        return endY;
    }

    @Override
    public ASCIIPixelTableVerticalLine setX(ASCIIAbsoluteCoordinate x) {
        this.x = x.getValue();
        return this;
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public ASCIIPixelTableVerticalLine setLineColor(Color color) {
        this.color = color;
        return this;
    }

    @Override
    public Color getLineColor() {
        return color;
    }
}
