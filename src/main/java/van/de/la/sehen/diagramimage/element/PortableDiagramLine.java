package van.de.la.sehen.diagramimage.element;

import van.de.la.sehen.dimensionparticle.positionparticle.AbsoluteCoordinate;

import java.awt.*;

public interface PortableDiagramLine<T extends PortableDiagramLine<T>> extends PortableDiagramElement {
    T setX1(AbsoluteCoordinate x1);
    int getX1();

    T setX2(AbsoluteCoordinate x2);
    int getX2();

    T setY1(AbsoluteCoordinate y1);
    int getY1();

    T setY2(AbsoluteCoordinate y2);
    int getY2();

    T setLineWidth(int width);
    int getLineWidth();

    T setLineColor(Color color);
    Color getLineColor();
}
