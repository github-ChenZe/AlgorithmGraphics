package van.de.la.sehen.diagramimage.element.pixel;

import van.de.la.sehen.diagramimage.PixelCanvas;
import van.de.la.sehen.diagramimage.element.*;
import van.de.la.sehen.dimensionparticle.positionparticle.AbsoluteCoordinate;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class PixelMaskCanvas extends PixelCanvas implements PortableDiagramMaskCanvas<PixelMaskCanvas, PixelDiagramElement>, PixelDiagramElement {
    private int startX;
    private int startY;
    private int endX;
    private int endY;

    public PixelMaskCanvas(AbsoluteCoordinate startX, AbsoluteCoordinate startY, AbsoluteCoordinate endX, AbsoluteCoordinate endY) {
        super(endX.getValue(), endY.getValue());
        setStartX(startX);
        setStartY(startY);
        setEndX(endX);
        setEndY(endY);
    }

    @Override
    public PixelMaskCanvas setStartX(AbsoluteCoordinate x) {
        startX = x.getValue();
        return this;
    }

    @Override
    public int getStartX() {
        return startX;
    }

    @Override
    public PixelMaskCanvas setStartY(AbsoluteCoordinate y) {
        startY = y.getValue();
        return this;
    }

    @Override
    public int getStartY() {
        return startY;
    }

    @Override
    public PixelMaskCanvas setEndX(AbsoluteCoordinate x) {
        endX = x.getValue();
        return this;
    }

    @Override
    public int getEndX() {
        return endX;
    }

    @Override
    public PixelMaskCanvas setEndY(AbsoluteCoordinate y) {
        endY = y.getValue();
        return this;
    }

    @Override
    public int getEndY() {
        return endY;
    }

    @Override
    public void paintOn(Graphics2D graphics) {
        if (startX == endX || startY == endY) return;
        Image subImage = toImage().getSubimage(getStartX(), getStartY(), getEndX() - getStartX(), getEndY() - getStartY());
        graphics.drawImage(subImage, startX, startY, null);
    }
}
