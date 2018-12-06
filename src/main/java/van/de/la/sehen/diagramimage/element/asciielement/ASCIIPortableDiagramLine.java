package van.de.la.sehen.diagramimage.element.asciielement;

import van.de.la.sehen.dimensionparticle.positionparticle.asciipositionparticle.ASCIIAbsoluteCoordinate;

import java.awt.*;

public interface ASCIIPortableDiagramLine<T extends ASCIIPortableDiagramLine<T>> extends ASCIIPortableDiagramElement {
    T setX1(ASCIIAbsoluteCoordinate x1);
    int getX1();

    T setX2(ASCIIAbsoluteCoordinate x2);
    int getX2();

    T setY1(ASCIIAbsoluteCoordinate y1);
    int getY1();

    T setY2(ASCIIAbsoluteCoordinate y2);
    int getY2();

    T setLineColor(Color color);
    Color getLineColor();
}
