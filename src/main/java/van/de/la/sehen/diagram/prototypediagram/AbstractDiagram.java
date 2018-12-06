package van.de.la.sehen.diagram.prototypediagram;

import math.geom2d.Vector2D;
import van.de.la.sehen.diagram.prototypediagram.model.GeneralAbstractDiagram;
import van.de.la.sehen.diagramimage.PixelCanvas;
import van.de.la.sehen.diagramimage.PortableDiagramCanvas;
import van.de.la.sehen.diagramimage.PortableDiagramPixelImage;
import van.de.la.sehen.diagramstyle.DiagramStyle;
import van.de.la.sehen.dimensionparticle.positionparticle.AbsoluteCoordinate;
import van.de.la.sehen.dimensionparticle.positionparticle.AbsolutePosition;
import van.de.la.sehen.dimensionparticle.positionparticle.CoordinateOffset;
import van.de.la.sehen.dimensionparticle.positionparticle.PositionOffset;
import van.de.la.sehen.dimensionparticle.sizeparticle.IntDimensionComponent;
import van.de.la.sehen.util.Util;
import van.de.la.sehen.warning.WarningStream;

import java.awt.image.BufferedImage;

public abstract class AbstractDiagram extends GeneralAbstractDiagram<
        AbstractDiagram, AbsolutePosition, Vector2D, AbsoluteCoordinate, PositionOffset, IntDimensionComponent, CoordinateOffset, PortableDiagramCanvas, PortableDiagramPixelImage> {
    public AbstractDiagram(AbstractDiagram parent, DiagramStyle style) {
        super(parent, style);
    }

    public void setPosition(CoordinateOffset x, CoordinateOffset y) { setPosition(new PositionOffset(x, y)); }

    public PixelCanvas generateCanvas() {
        layout();
        if (getWidth().getValue() <= 0) {
            WarningStream.putWarning("Get width <= 0.", this);
        }

        if (getHeight().getValue() <= 0) {
            WarningStream.putWarning("Get height <= 0.", this);
        }
        PixelCanvas canvas = new PixelCanvas(getWidth().getValue(), getHeight().getValue());
        paintDiagram(canvas);
        return canvas;
    }

    public PortableDiagramPixelImage generatePortable() {
        return generateCanvas().toPortableImage();
    }

    @Override
    public PortableDiagramPixelImage generateFinalImage() {
        return generatePortable();
    }

    @Override
    public AbsolutePosition basePosition() {
        return AbsolutePosition.zero();
    }

    @Override
    public PositionOffset baseOffset() {
        return PositionOffset.zero();
    }

    @Override
    public String getTagName() {
        String classname = Util.firstLetterToLower(this.getClass().getSimpleName());
        return classname.substring(0, classname.length() - "Diagram".length());
    }
}
