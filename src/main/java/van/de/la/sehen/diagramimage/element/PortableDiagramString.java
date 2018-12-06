package van.de.la.sehen.diagramimage.element;

import van.de.la.sehen.dimensionparticle.positionparticle.AbsoluteCoordinate;

import java.awt.*;

public interface PortableDiagramString<T extends PortableDiagramString<T>> extends PortableDiagramElement {
    T setTopLeftX(AbsoluteCoordinate x);
    int getTopLeftX();

    T setTopLeftY(AbsoluteCoordinate y);
    int getTopLeftY();

    T setFontName(String fontName);
    String getFontName();

    T setFontSize(int fontSize);
    int getFontSize();

    T setFontColor(Color color);
    Color getFontColor();

    T setString(String string);
    String getString();

    int getWidth();
    int getHeight();
}
