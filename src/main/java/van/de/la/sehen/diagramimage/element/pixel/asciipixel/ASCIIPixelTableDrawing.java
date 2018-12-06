package van.de.la.sehen.diagramimage.element.pixel.asciipixel;

import van.de.la.sehen.diagramimage.asciidiagramimage.ASCIIImage;
import van.de.la.sehen.diagramimage.element.asciielement.ASCIIPortableDiagramTableDrawing;
import van.de.la.sehen.diagramimage.element.asciielement.TableDrawingElement;
import van.de.la.sehen.dimensionparticle.positionparticle.asciipositionparticle.ASCIIAbsoluteCoordinate;

import java.awt.*;

public class ASCIIPixelTableDrawing implements ASCIIPixelDiagramElement, ASCIIPortableDiagramTableDrawing<ASCIIPixelTableDrawing> {
    private int x;
    private int y;
    private TableDrawingElement element;
    private Color color;

    @Override
    public void paintOn(ASCIIImage image) {
        image.setChar(y, x, element);
    }

    @Override
    public ASCIIPixelTableDrawing setX(ASCIIAbsoluteCoordinate x) {
        this.x = x.getValue();
        return this;
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public ASCIIPixelTableDrawing setY(ASCIIAbsoluteCoordinate y) {
        this.y = y.getValue();
        return this;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public ASCIIPixelTableDrawing setElement(TableDrawingElement element) {
        this.element = element;
        return this;
    }

    @Override
    public TableDrawingElement getElement() {
        return element;
    }

    @Override
    public ASCIIPixelTableDrawing setColor(Color color) {
        this.color = color;
        return this;
    }

    @Override
    public Color getColor() {
        return color;
    }
}
