package van.de.la.sehen.diagramimage.element.asciielement;

import van.de.la.sehen.dimensionparticle.positionparticle.asciipositionparticle.ASCIIAbsoluteCoordinate;

import java.awt.*;

public interface ASCIIPortableDiagramString<T extends ASCIIPortableDiagramString<T>> extends ASCIIPortableDiagramElement {
    T setTopLeftX(ASCIIAbsoluteCoordinate x);
    int getTopLeftX();

    T setTopLeftY(ASCIIAbsoluteCoordinate y);
    int getTopLeftY();

    T setFontColor(Color color);
    Color getFontColor();

    T setString(String string);
    String getString();

    int getWidth();
    int getHeight();
}
