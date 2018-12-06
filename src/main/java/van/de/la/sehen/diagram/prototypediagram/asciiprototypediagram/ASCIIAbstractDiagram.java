package van.de.la.sehen.diagram.prototypediagram.asciiprototypediagram;

import van.de.la.sehen.diagram.prototypediagram.model.GeneralAbstractDiagram;
import van.de.la.sehen.diagramimage.asciidiagramimage.ASCIICanvas;
import van.de.la.sehen.diagramimage.asciidiagramimage.ASCIIImage;
import van.de.la.sehen.diagramstyle.DiagramStyle;
import van.de.la.sehen.dimensionparticle.positionparticle.IntVector2D;
import van.de.la.sehen.dimensionparticle.positionparticle.asciipositionparticle.ASCIIAbsoluteCoordinate;
import van.de.la.sehen.dimensionparticle.positionparticle.asciipositionparticle.ASCIIAbsolutePosition;
import van.de.la.sehen.dimensionparticle.positionparticle.asciipositionparticle.ASCIICoordinateOffset;
import van.de.la.sehen.dimensionparticle.positionparticle.asciipositionparticle.ASCIIPositionOffset;
import van.de.la.sehen.dimensionparticle.sizeparticle.asciisizeparticle.ASCIIIntDimensionComponent;
import van.de.la.sehen.util.Util;

public abstract class ASCIIAbstractDiagram extends GeneralAbstractDiagram<
        ASCIIAbstractDiagram,
        ASCIIAbsolutePosition,
        IntVector2D,
        ASCIIAbsoluteCoordinate,
        ASCIIPositionOffset,
        ASCIIIntDimensionComponent,
        ASCIICoordinateOffset,
        ASCIICanvas,
        ASCIIImage> {
    public ASCIIAbstractDiagram(ASCIIAbstractDiagram parent, DiagramStyle style) {
        super(parent, style);
    }

    public void setPosition(ASCIICoordinateOffset x, ASCIICoordinateOffset y) { setPosition(new ASCIIPositionOffset(x, y)); }

    public ASCIIImage generateImage() {
        layout();
        // +1: remember that for ASCDiagrams, getSize returns the size fit the diagram exactly
        ASCIICanvas canvas = new ASCIICanvas(getWidth().getValue() + 1, getHeight().getValue() + 1);
        paintDiagram(canvas);
        return canvas.toImage();
    }

    @Override
    public ASCIIImage generateFinalImage() {
        return generateImage();
    }

    @Override
    public ASCIIAbsolutePosition basePosition() {
        return ASCIIAbsolutePosition.zero();
    }

    @Override
    public ASCIIPositionOffset baseOffset() {
        return ASCIIPositionOffset.zero();
    }

    @Override
    public String getTagName() {
        String classname = Util.firstLetterToLower(this.getClass().getSimpleName());
        return classname.substring("ASCII".length(), classname.length() - "Diagram".length());
    }
}
