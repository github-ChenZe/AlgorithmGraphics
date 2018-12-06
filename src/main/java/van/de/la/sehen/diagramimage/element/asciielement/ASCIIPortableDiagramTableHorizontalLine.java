package van.de.la.sehen.diagramimage.element.asciielement;

import van.de.la.sehen.dimensionparticle.positionparticle.asciipositionparticle.ASCIIAbsoluteCoordinate;

import java.awt.*;

public interface ASCIIPortableDiagramTableHorizontalLine<T extends ASCIIPortableDiagramTableHorizontalLine<T>> extends ASCIIPortableDiagramElement {
    T setStartX(ASCIIAbsoluteCoordinate x);
    int getStartX();

    T setEndX(ASCIIAbsoluteCoordinate x);
    int getEndX();


    T setY(ASCIIAbsoluteCoordinate y);
    int getY();

    T setLineColor(Color color);
    Color getLineColor();
}
