package van.de.la.sehen.diagramimage.element.pixel.asciipixel;

import van.de.la.sehen.diagramimage.asciidiagramimage.ASCIIImage;
import van.de.la.sehen.diagramimage.element.asciielement.ASCIIPortableDiagramTableHorizontalLine;
import van.de.la.sehen.diagramimage.element.asciielement.TableDrawingElement;
import van.de.la.sehen.dimensionparticle.positionparticle.asciipositionparticle.ASCIIAbsoluteCoordinate;

import java.awt.*;

public class ASCIIPixelTableHorizontalLine implements ASCIIPixelDiagramElement, ASCIIPortableDiagramTableHorizontalLine<ASCIIPixelTableHorizontalLine> {
    private int startX;
    private int endX;
    private int y;
    private Color color;

    @Override
    public void paintOn(ASCIIImage image) {
        if (startX > endX) {
            int tmp = endX;
            endX = startX;
            startX = tmp;
        }
        for (int x = startX; x < endX; x++) {
            // DON'T USE getHorizontalChar here, or override behaviour would be weird.
            image.setChar(y, x, TableDrawingElement.HORIZONTAL);
        }
    }

    @Override
    public ASCIIPixelTableHorizontalLine setStartX(ASCIIAbsoluteCoordinate x) {
        this.startX = x.getValue();
        return this;
    }

    @Override
    public int getStartX() {
        return startX;
    }

    @Override
    public ASCIIPixelTableHorizontalLine setEndX(ASCIIAbsoluteCoordinate x) {
        this.endX = x.getValue();
        return this;
    }

    @Override
    public int getEndX() {
        return endX;
    }

    @Override
    public ASCIIPixelTableHorizontalLine setY(ASCIIAbsoluteCoordinate y) {
        this.y = y.getValue();
        return this;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public ASCIIPixelTableHorizontalLine setLineColor(Color color) {
        this.color = color;
        return this;
    }

    @Override
    public Color getLineColor() {
        return color;
    }
}
