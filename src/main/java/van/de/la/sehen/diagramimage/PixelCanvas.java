package van.de.la.sehen.diagramimage;

import van.de.la.sehen.diagramimage.element.PortableDiagramLine;
import van.de.la.sehen.diagramimage.element.PortableDiagramMaskCanvas;
import van.de.la.sehen.diagramimage.element.PortableDiagramPolygon;
import van.de.la.sehen.diagramimage.element.PortableDiagramString;
import van.de.la.sehen.diagramimage.element.pixel.*;
import van.de.la.sehen.dimensionparticle.positionparticle.AbsoluteCoordinate;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class PixelCanvas implements PixelDiagramCanvas<PixelDiagramElement> {
    private List<PixelDiagramElement> elements = new ArrayList<>();
    private PixelCanvas next = null;
    private int width;
    private int height;

    protected List<PixelDiagramElement> getElements() {
        return elements;
    }

    public PixelCanvas(int width, int height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public BufferedImage toImage() {
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D graphics = image.createGraphics();
        for (PixelDiagramElement element: elements) {
            element.paintOn(graphics);
        }
        return image;
    }

    @Override
    public PortableDiagramPixelImage toPortableImage() {
        return next == null ? new PortableDiagramPixelImage(toImage()) : new PortableDiagramPixelImage(toImage(), next.toPortableImage());
    }

    @Override
    public PortableDiagramCanvas<PixelDiagramElement> getNextCanvas() {
        return next;
    }

    @Override
    public PortableDiagramCanvas<PixelDiagramElement> newNextCanvas() {
        return next = new PixelCanvas(width, height);
    }

    public PortableDiagramCanvas<PixelDiagramElement> setNextCanvas(PixelCanvas next) {
        this.next = next;
        return next;
    }

    @Override
    public <S extends PixelDiagramElement> S putElement(S element) {
        this.elements.add(element);
        return element;
    }

    @Override
    public PortableDiagramLine generateAndPushLine() {
        return putElement(new PixelLine());
    }

    @Override
    public PortableDiagramString generateAndPushString() {
        return putElement(new PixelString());
    }

    @Override
    public PortableDiagramPolygon generateAndPushPolygon() {
        return putElement(new PixelPolygon());
    }

    @Override
    public PortableDiagramMaskCanvas<?, PixelDiagramElement> generateAndPushMaskCanvas(AbsoluteCoordinate startX, AbsoluteCoordinate startY, AbsoluteCoordinate endX, AbsoluteCoordinate endY) {
        return putElement(new PixelMaskCanvas(startX, startY, endX, endY));
    }
}
