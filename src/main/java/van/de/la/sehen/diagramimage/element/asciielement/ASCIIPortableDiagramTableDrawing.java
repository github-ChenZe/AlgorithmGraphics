package van.de.la.sehen.diagramimage.element.asciielement;

import van.de.la.sehen.dimensionparticle.positionparticle.asciipositionparticle.ASCIIAbsoluteCoordinate;

import java.awt.*;

public interface ASCIIPortableDiagramTableDrawing<T extends ASCIIPortableDiagramTableDrawing<T>> extends ASCIIPortableDiagramElement {
    T setX(ASCIIAbsoluteCoordinate x);
    int getX();


    T setY(ASCIIAbsoluteCoordinate y);
    int getY();

    T setElement(TableDrawingElement element);
    TableDrawingElement getElement();

    T setColor(Color color);
    Color getColor();
}
