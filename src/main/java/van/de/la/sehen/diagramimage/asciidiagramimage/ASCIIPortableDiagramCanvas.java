package van.de.la.sehen.diagramimage.asciidiagramimage;

import van.de.la.sehen.diagramimage.element.asciielement.*;
import van.de.la.sehen.dimensionparticle.positionparticle.asciipositionparticle.ASCIIAbsoluteCoordinate;

import java.awt.*;

/**
 * A Canvas is a container of DiagramElements.
 * @param <T> The type of elements that lie in this Canvas
 */

public interface ASCIIPortableDiagramCanvas<T extends ASCIIPortableDiagramElement> {
    <S extends T> S putElement(S element);
    default ASCIIPortableDiagramLine generateAndPushLine(ASCIIAbsoluteCoordinate x1, ASCIIAbsoluteCoordinate y1, ASCIIAbsoluteCoordinate x2, ASCIIAbsoluteCoordinate y2, Color color) {
        return generateAndPushLine().setX1(x1).setX2(x2).setY1(y1).setY2(y2).setLineColor(color);
    }
    default ASCIIPortableDiagramString generateAndPushString(String string, ASCIIAbsoluteCoordinate topLeftX, ASCIIAbsoluteCoordinate topLeftY, Color fontColor) {
        return generateAndPushString().setString(string).setTopLeftX(topLeftX).setTopLeftY(topLeftY).setFontColor(fontColor);
    }
    default ASCIIPortableDiagramTableDrawing generateAndPushTableDrawing(ASCIIAbsoluteCoordinate x, ASCIIAbsoluteCoordinate y, TableDrawingElement element, Color color) {
        return generateAndPushTableDrawing().setX(x).setY(y).setElement(element).setColor(color);
    }
    default ASCIIPortableDiagramTableHorizontalLine generateAndPushTableHorizontalLine(ASCIIAbsoluteCoordinate y, ASCIIAbsoluteCoordinate startX, ASCIIAbsoluteCoordinate endX, Color color) {
        return generateAndPushTableHorizontalLine().setY(y).setStartX(startX).setEndX(endX).setLineColor(color);
    }
    default ASCIIPortableDiagramTableVerticalLine generateAnsPushTableVerticalLine(ASCIIAbsoluteCoordinate x, ASCIIAbsoluteCoordinate startY, ASCIIAbsoluteCoordinate endY, Color color) {
        return generateAnsPushTableVerticalLine().setX(x).setStartY(startY).setEndY(endY).setLineColor(color);
    }

    ASCIIPortableDiagramLine generateAndPushLine();
    ASCIIPortableDiagramString generateAndPushString();
    ASCIIPortableDiagramTableDrawing generateAndPushTableDrawing();
    ASCIIPortableDiagramTableHorizontalLine generateAndPushTableHorizontalLine();
    ASCIIPortableDiagramTableVerticalLine generateAnsPushTableVerticalLine();
}
