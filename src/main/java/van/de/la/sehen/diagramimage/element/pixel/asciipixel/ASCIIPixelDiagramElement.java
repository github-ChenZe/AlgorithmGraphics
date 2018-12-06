package van.de.la.sehen.diagramimage.element.pixel.asciipixel;

import van.de.la.sehen.diagramimage.asciidiagramimage.ASCIIImage;
import van.de.la.sehen.diagramimage.element.asciielement.ASCIIPortableDiagramElement;

/**
 * PixelDiagramElements are those who can be painted on a awt image.
 */

public interface ASCIIPixelDiagramElement extends ASCIIPortableDiagramElement {
    void paintOn(ASCIIImage image);
}
