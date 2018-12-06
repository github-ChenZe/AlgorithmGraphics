package van.de.la.sehen.diagramimage;

import van.de.la.sehen.diagramimage.element.*;
import van.de.la.sehen.dimensionparticle.positionparticle.AbsoluteCoordinate;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * A Canvas is a container of DiagramElements.
 * @param <T> The type of elements that lie in this Canvas
 */

public interface PortableDiagramCanvas<T extends PortableDiagramElement> {
    PortableDiagramCanvas<T> getNextCanvas();
    PortableDiagramCanvas<T> newNextCanvas();
    <S extends T> S putElement(S element);
    default PortableDiagramLine generateAndPushLine(AbsoluteCoordinate x1, AbsoluteCoordinate y1, AbsoluteCoordinate x2, AbsoluteCoordinate y2, int width, Color color) {
        return generateAndPushLine().setX1(x1).setX2(x2).setY1(y1).setY2(y2).setLineWidth(width).setLineColor(color);
    }
    default PortableDiagramString generateAndPushString(String string, AbsoluteCoordinate topLeftX, AbsoluteCoordinate topLeftY, String fontName, int fontSize, Color fontColor) {
        return generateAndPushString().setString(string).setTopLeftX(topLeftX).setTopLeftY(topLeftY).setFontName(fontName).setFontSize(fontSize).setFontColor(fontColor);
    }
    default PortableDiagramPolygon generateAndPushPolygon(Color fillColor) {
        return generateAndPushPolygon().setFillColor(fillColor);
    }

    /**
     * @return The MaskCanvas generated, this is different from other generateAndPush methods.
     */
    PortableDiagramMaskCanvas<?, T> generateAndPushMaskCanvas(AbsoluteCoordinate startX, AbsoluteCoordinate startY, AbsoluteCoordinate endX, AbsoluteCoordinate endY);
    PortableDiagramLine generateAndPushLine();
    PortableDiagramString generateAndPushString();
    PortableDiagramPolygon generateAndPushPolygon();

    static int getStringWidth(String string, String fontName, int fontSize) {
        BufferedImage b = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = b.createGraphics();
        g.setFont(new Font(fontName, Font.PLAIN, fontSize));
        FontMetrics fontMetrics = g.getFontMetrics();
        return fontMetrics.stringWidth(string);
    }

    //TODO: remove the dirty calculation here
    static int getStringHeight(String string, String fontName, int fontSize) {
        BufferedImage b = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = b.createGraphics();
        g.setFont(new Font(fontName, Font.PLAIN, fontSize));
        FontMetrics fontMetrics = g.getFontMetrics();
        return fontMetrics.getHeight();
    }
}
