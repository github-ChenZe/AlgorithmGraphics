package van.de.la.sehen.diagramimage.element;

import van.de.la.sehen.dimensionparticle.positionparticle.AbsoluteCoordinate;

import java.awt.*;
import java.util.List;

public interface PortableDiagramPolygon<T extends PortableDiagramPolygon<T>> extends PortableDiagramElement {
    T putX(AbsoluteCoordinate x);
    List<Integer> getXs();

    T putY(AbsoluteCoordinate y);
    List<Integer> getYs();

    default T putXY(AbsoluteCoordinate x, AbsoluteCoordinate y) {
        putX(x);
        return putY(y);
    }

    T setFillColor(Color fillColor);
    Color getFillColor();
}
