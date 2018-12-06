package van.de.la.sehen.diagramimage.asciidiagramimage;

import van.de.la.sehen.diagramimage.element.asciielement.*;
import van.de.la.sehen.diagramimage.element.pixel.asciipixel.*;

import java.util.ArrayList;
import java.util.List;

public class ASCIICanvas implements ASCIIPortableDiagramCanvas<ASCIIPixelDiagramElement> {
    private List<ASCIIPixelDiagramElement> elements = new ArrayList<>();
    private int width;
    private int height;

    public ASCIICanvas(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public ASCIIImage toImage() {
        ASCIIImage image = new ASCIIImage(width, height);
        for (ASCIIPixelDiagramElement element: elements) {
            element.paintOn(image);
        }
        return image;
    }

    @Override
    public <S extends ASCIIPixelDiagramElement> S putElement(S element) {
        elements.add(element);
        return element;
    }

    @Override
    public ASCIIPortableDiagramLine generateAndPushLine() {
        return putElement(new ASCIIPixelLine());
    }

    @Override
    public ASCIIPortableDiagramString generateAndPushString() {
        return putElement(new ASCIIPixelString());
    }

    @Override
    public ASCIIPortableDiagramTableDrawing generateAndPushTableDrawing() {
        return putElement(new ASCIIPixelTableDrawing());
    }

    @Override
    public ASCIIPortableDiagramTableHorizontalLine generateAndPushTableHorizontalLine() {
        return putElement(new ASCIIPixelTableHorizontalLine());
    }

    @Override
    public ASCIIPortableDiagramTableVerticalLine generateAnsPushTableVerticalLine() {
        return putElement(new ASCIIPixelTableVerticalLine());
    }
}
