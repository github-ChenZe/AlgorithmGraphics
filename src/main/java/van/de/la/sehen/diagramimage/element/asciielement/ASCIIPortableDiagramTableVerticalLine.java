package van.de.la.sehen.diagramimage.element.asciielement;

import van.de.la.sehen.dimensionparticle.positionparticle.asciipositionparticle.ASCIIAbsoluteCoordinate;

import java.awt.*;

public interface ASCIIPortableDiagramTableVerticalLine<T extends ASCIIPortableDiagramTableVerticalLine<T>> extends ASCIIPortableDiagramElement {
    T setStartY(ASCIIAbsoluteCoordinate y);
    int getStartY();

    T setEndY(ASCIIAbsoluteCoordinate y);
    int getEndY();


    T setX(ASCIIAbsoluteCoordinate x);
    int getX();

    T setLineColor(Color color);
    Color getLineColor();
}
