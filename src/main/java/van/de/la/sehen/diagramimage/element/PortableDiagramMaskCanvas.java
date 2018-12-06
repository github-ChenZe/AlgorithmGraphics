package van.de.la.sehen.diagramimage.element;

import van.de.la.sehen.diagramimage.PortableDiagramCanvas;
import van.de.la.sehen.dimensionparticle.positionparticle.AbsoluteCoordinate;

public interface PortableDiagramMaskCanvas<T extends PortableDiagramMaskCanvas<T, ChildT>, ChildT extends PortableDiagramElement> extends PortableDiagramCanvas<ChildT>, PortableDiagramElement {
    T setStartX(AbsoluteCoordinate x);
    int getStartX();
    T setStartY(AbsoluteCoordinate y);
    int getStartY();
    T setEndX(AbsoluteCoordinate x);
    int getEndX();
    T setEndY(AbsoluteCoordinate y);
    int getEndY();
}
