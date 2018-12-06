package van.de.la.sehen.diagramimage.element.pixel;

import van.de.la.sehen.diagramimage.element.PortableDiagramPolygon;
import van.de.la.sehen.dimensionparticle.positionparticle.AbsoluteCoordinate;
import van.de.la.sehen.util.Util;
import van.de.la.sehen.warning.WarningStream;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class PixelPolygon implements PixelDiagramElement, PortableDiagramPolygon<PixelPolygon> {
    private List<Integer> xs = new ArrayList<>();
    private List<Integer> ys = new ArrayList<>();
    private Color fillColor;

    @Override
    public void paintOn(Graphics2D graphics) {
        graphics.setPaint(fillColor);
        if (xs.size() != ys.size()) WarningStream.putWarning("Unequal polygon coordinates size.", this);
        graphics.fillPolygon(Util.toArray(xs), Util.toArray(ys), Util.min(xs.size(), ys.size()));
    }

    @Override
    public PixelPolygon putX(AbsoluteCoordinate x) {
        xs.add(x.getValue());
        return this;
    }

    @Override
    public List<Integer> getXs() {
        return xs;
    }

    @Override
    public PixelPolygon putY(AbsoluteCoordinate y) {
        ys.add(y.getValue());
        return this;
    }

    @Override
    public List<Integer> getYs() {
        return ys;
    }

    @Override
    public PixelPolygon setFillColor(Color fillColor) {
        this.fillColor = fillColor;
        return this;
    }

    @Override
    public Color getFillColor() {
        return fillColor;
    }
}
