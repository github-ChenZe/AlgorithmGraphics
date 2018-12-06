package van.de.la.sehen.diagramimage.element.pixel;

import van.de.la.sehen.diagramimage.element.PortableDiagramString;
import van.de.la.sehen.dimensionparticle.positionparticle.AbsoluteCoordinate;

import java.awt.*;
import java.awt.image.BufferedImage;

public class PixelString implements PixelDiagramElement, PortableDiagramString<PixelString> {
    private int topLeftX;
    private int topLeftY;
    private String fontName;
    private int fontSize;
    private Color fontColor;
    private String string;

    private Graphics2D initializeGraphics(Graphics2D graphics) {
        Font font = new Font(getFontName(), Font.PLAIN, getFontSize());
        graphics.setFont(font);
        graphics.setPaint(getFontColor());
        return graphics;
    }

    @Override
    public void paintOn(Graphics2D graphics) {
        graphics = initializeGraphics(graphics);
        FontMetrics fontMetrics = graphics.getFontMetrics();
        int ascent = fontMetrics.getAscent();
        graphics.drawString(getString(), getTopLeftX(), ascent + getTopLeftY());
    }

    @Override
    public PixelString setTopLeftX(AbsoluteCoordinate x) {
        this.topLeftX = x.getValue();
        return this;
    }

    @Override
    public int getTopLeftX() {
        return topLeftX;
    }

    @Override
    public PixelString setTopLeftY(AbsoluteCoordinate y) {
        this.topLeftY = y.getValue();
        return this;
    }

    @Override
    public int getTopLeftY() {
        return topLeftY;
    }

    @Override
    public PixelString setFontName(String fontName) {
        this.fontName = fontName;
        return this;
    }

    @Override
    public String getFontName() {
        return fontName;
    }

    @Override
    public PixelString setFontSize(int fontSize) {
        this.fontSize = fontSize;
        return this;
    }

    @Override
    public int getFontSize() {
        return fontSize;
    }

    @Override
    public PixelString setFontColor(Color color) {
        this.fontColor = color;
        return this;
    }

    @Override
    public Color getFontColor() {
        return fontColor;
    }

    @Override
    public PixelString setString(String string) {
        this.string = string;
        return this;
    }

    @Override
    public String getString() {
        return string;
    }

    @Override
    public int getWidth() {
        BufferedImage b = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = initializeGraphics(b.createGraphics());
        FontMetrics fontMetrics = g.getFontMetrics();
        return fontMetrics.stringWidth(getString());
    }

    @Override
    public int getHeight() {
        BufferedImage b = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = initializeGraphics(b.createGraphics());
        FontMetrics fontMetrics = g.getFontMetrics();
        return fontMetrics.getHeight();
    }
}
