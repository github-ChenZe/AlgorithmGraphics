package van.de.la.sehen.diagram.prototypediagram;

import math.geom2d.Vector2D;
import org.springframework.lang.Nullable;
import van.de.la.sehen.diagram.prototypediagram.model.GeneralAbstractDiagram;
import van.de.la.sehen.diagram.pseudodiagram.PseudoDiagram;
import van.de.la.sehen.diagramimage.PixelCanvas;
import van.de.la.sehen.diagramimage.PortableDiagramCanvas;
import van.de.la.sehen.diagramimage.PortableDiagramPixelImage;
import van.de.la.sehen.diagramstyle.DiagramStyle;
import van.de.la.sehen.diagramtopology.ChildIndex;
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
        int width = getWidth().getValue();
        int height = getHeight().getValue();
        if (width <= 0) {
            WarningStream.putWarning("Get width <= 0.", this);
        }

        if (height <= 0) {
            WarningStream.putWarning("Get height <= 0.", this);
        }
        PixelCanvas canvas = new PixelCanvas(width <= 0 ? 1 : width, height <= 0 ? 1 : height);
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

    @Nullable
    protected AbstractDiagram getMirrorDiagram() {
        AbstractDiagram parent = getParent();
        if (parent == null) return null; // come to top without a mirror
        // parent exists
        AbstractDiagram parentMirror = parent.getMirrorDiagram();
        if (parentMirror == null) return null;
        AbstractDiagram mirror = getTopologicalCorrespondence(parentMirror);
        if (mirror == null) {
            WarningStream.putWarning("Corresponding diagram not found. This mirror is bad organized or bad structured.", this);
            return null;
        }
        if (!mirror.getClass().equals(this.getClass())) {
            WarningStream.putWarning("Corresponding diagram is not of the same type.", this);
        }
        return mirror;
    }

    @Override
    public AbsoluteCoordinate getAbsoluteX() {
        AbstractDiagram mirror = getMirrorDiagram();
        AbsoluteCoordinate originalAbsolute = super.getAbsoluteX();

        if (mirror == null) return originalAbsolute;
        return new AbsoluteCoordinate((int) Util.weightedAverageOfDouble (mirror.getAbsoluteX().getValue(), originalAbsolute.getValue(), getTransitionProgress()));
    }

    @Override
    public AbsoluteCoordinate getAbsoluteY() {
        AbstractDiagram mirror = getMirrorDiagram();
        AbsoluteCoordinate originalAbsolute = super.getAbsoluteY();

        if (mirror == null) return originalAbsolute;
        return new AbsoluteCoordinate((int) Util.weightedAverageOfDouble (mirror.getAbsoluteY().getValue(), originalAbsolute.getValue(), getTransitionProgress()));
    }

    public double getTransitionProgress() {
        if (getParent() != null) return getParent().getTransitionProgress();
        return 0;
    }
}
