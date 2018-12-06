package van.de.la.sehen.diagramimage.element.pixel.asciipixel;

import van.de.la.sehen.diagramimage.asciidiagramimage.ASCIIImage;
import van.de.la.sehen.diagramimage.element.asciielement.ASCIIPortableDiagramString;
import van.de.la.sehen.dimensionparticle.positionparticle.asciipositionparticle.ASCIIAbsoluteCoordinate;

import java.awt.*;

public class ASCIIPixelString implements ASCIIPixelDiagramElement, ASCIIPortableDiagramString<ASCIIPixelString> {
    private int topLeftX;
    private int topLeftY;
    private String fontName;
    private int fontSize;
    private Color fontColor;
    private String string;


    @Override
    public void paintOn(ASCIIImage image) {
        for (int x = topLeftX; x < topLeftX + string.length(); x++) {
            image.setChar(topLeftY, x, string.charAt(x - topLeftX));
        }
    }

    @Override
    public ASCIIPixelString setTopLeftX(ASCIIAbsoluteCoordinate x) {
        this.topLeftX = x.getValue();
        return this;
    }

    @Override
    public int getTopLeftX() {
        return topLeftX;
    }

    @Override
    public ASCIIPixelString setTopLeftY(ASCIIAbsoluteCoordinate y) {
        this.topLeftY = y.getValue();
        return this;
    }

    @Override
    public int getTopLeftY() {
        return topLeftY;
    }

    @Override
    public ASCIIPixelString setFontColor(Color color) {
        this.fontColor = color;
        return this;
    }

    @Override
    public Color getFontColor() {
        return fontColor;
    }

    @Override
    public ASCIIPixelString setString(String string) {
        this.string = string;
        return this;
    }

    @Override
    public String getString() {
        return string;
    }

    @Override
    public int getWidth() {
        return string.length();
    }

    @Override
    public int getHeight() {
        return 1;
    }
}
