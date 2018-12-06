package van.de.la.sehen.diagramimage;

import van.de.la.sehen.diagramimage.element.pixel.PixelDiagramElement;

import java.awt.*;

public interface PixelDiagramCanvas<T extends PixelDiagramElement> extends PortableDiagramCanvas<T> {
    Image toImage();
    PortableDiagramPixelImage toPortableImage();
}
