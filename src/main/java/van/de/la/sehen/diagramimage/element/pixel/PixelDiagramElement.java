package van.de.la.sehen.diagramimage.element.pixel;

import van.de.la.sehen.diagramimage.element.PortableDiagramElement;

import java.awt.*;

/**
 * PixelDiagramElements are those who can be painted on a awt image.
 */

public interface PixelDiagramElement extends PortableDiagramElement {
    void paintOn(Graphics2D graphics);
}
