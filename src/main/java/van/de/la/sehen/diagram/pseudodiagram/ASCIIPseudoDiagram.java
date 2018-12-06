package van.de.la.sehen.diagram.pseudodiagram;

import van.de.la.sehen.diagram.prototypediagram.asciiprototypediagram.ASCIIAbstractDiagram;
import van.de.la.sehen.diagramimage.asciidiagramimage.ASCIICanvas;
import van.de.la.sehen.diagramstyle.DiagramStyle;
import van.de.la.sehen.dimensionparticle.positionparticle.asciipositionparticle.ASCIICoordinateOffset;
import van.de.la.sehen.dimensionparticle.positionparticle.asciipositionparticle.ASCIIPositionOffset;
import van.de.la.sehen.dimensionparticle.sizeparticle.asciisizeparticle.ASCIIIntDimensionComponent;
import van.de.la.sehen.warning.WarningStream;

import java.lang.reflect.InvocationTargetException;
import java.util.function.Function;

public abstract class ASCIIPseudoDiagram extends ASCIIAbstractDiagram implements PseudoDiagramModel<ASCIICoordinateOffset, ASCIIAbstractDiagram> {
    public ASCIIPseudoDiagram(ASCIIAbstractDiagram parent, DiagramStyle style) {
        super(parent, style);
    }

    @Override
    public void paintDiagram(ASCIICanvas canvas) {

    }

    @Override
    public void calculateSize() {
        setWidth(ASCIIIntDimensionComponent.zero());
        setHeight(ASCIIIntDimensionComponent.zero());
    }

    @Override
    public void layout() {

    }

    @Override
    public final ASCIIPositionOffset getPosition() {
        return new ASCIIPositionOffset(getX(), getY());
    }

    public static final Function<ASCIIAbstractDiagram, ASCIICPseudoDiagram> C = (parent) -> newPseudoDiagram(ASCIICPseudoDiagram.class, parent);
    public static final Function<ASCIIAbstractDiagram, ASCIIEPseudoDiagram> E = (parent) -> newPseudoDiagram(ASCIIEPseudoDiagram.class, parent);
    public static final Function<ASCIIAbstractDiagram, ASCIINPseudoDiagram> N = (parent) -> newPseudoDiagram(ASCIINPseudoDiagram.class, parent);
    public static final Function<ASCIIAbstractDiagram, ASCIIWPseudoDiagram> W = (parent) -> newPseudoDiagram(ASCIIWPseudoDiagram.class, parent);
    public static final Function<ASCIIAbstractDiagram, ASCIISPseudoDiagram> S = (parent) -> newPseudoDiagram(ASCIISPseudoDiagram.class, parent);
    public static final Function<ASCIIAbstractDiagram, ASCIINWPseudoDiagram> NW = (parent) -> newPseudoDiagram(ASCIINWPseudoDiagram.class, parent);
    public static final Function<ASCIIAbstractDiagram, ASCIISWPseudoDiagram> SW = (parent) -> newPseudoDiagram(ASCIISWPseudoDiagram.class, parent);
    public static final Function<ASCIIAbstractDiagram, ASCIISEPseudoDiagram> SE = (parent) -> newPseudoDiagram(ASCIISEPseudoDiagram.class, parent);
    public static final Function<ASCIIAbstractDiagram, ASCIINEPseudoDiagram> NE = (parent) -> newPseudoDiagram(ASCIINEPseudoDiagram.class, parent);

    public static <T extends ASCIIPseudoDiagram> T newPseudoDiagram(Class<T> tClass, ASCIIAbstractDiagram parent) {
        try {
            return tClass.getConstructor(ASCIIAbstractDiagram.class, DiagramStyle.class).newInstance(parent, null);
        } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            WarningStream.putWarning("Constructor for PseudoDiagram not found or exception during construction.", PseudoDiagram.class);
        }
        return null;
    }
}

class ASCIICPseudoDiagram extends ASCIIPseudoDiagram implements CPseudoDiagramModel<ASCIICoordinateOffset, ASCIIAbstractDiagram> {
    public ASCIICPseudoDiagram(ASCIIAbstractDiagram parent, DiagramStyle style) {
        super(parent, style);
    }
}

class ASCIIEPseudoDiagram extends ASCIIPseudoDiagram implements EPseudoDiagramModel<ASCIICoordinateOffset, ASCIIAbstractDiagram> {
    public ASCIIEPseudoDiagram(ASCIIAbstractDiagram parent, DiagramStyle style) {
        super(parent, style);
    }
}

class ASCIINEPseudoDiagram extends ASCIIPseudoDiagram implements NEPseudoDiagramModel<ASCIICoordinateOffset, ASCIIAbstractDiagram> {
    public ASCIINEPseudoDiagram(ASCIIAbstractDiagram parent, DiagramStyle style) {
        super(parent, style);
    }
}

class ASCIINPseudoDiagram extends ASCIIPseudoDiagram implements NPseudoDiagramModel<ASCIICoordinateOffset, ASCIIAbstractDiagram> {
    public ASCIINPseudoDiagram(ASCIIAbstractDiagram parent, DiagramStyle style) {
        super(parent, style);
    }
}

class ASCIINWPseudoDiagram extends ASCIIPseudoDiagram implements NWPseudoDiagramModel<ASCIICoordinateOffset, ASCIIAbstractDiagram> {
    public ASCIINWPseudoDiagram(ASCIIAbstractDiagram parent, DiagramStyle style) {
        super(parent, style);
    }
}

class ASCIISEPseudoDiagram extends ASCIIPseudoDiagram implements EPseudoDiagramModel<ASCIICoordinateOffset, ASCIIAbstractDiagram> {
    public ASCIISEPseudoDiagram(ASCIIAbstractDiagram parent, DiagramStyle style) {
        super(parent, style);
    }
}

class ASCIISPseudoDiagram extends ASCIIPseudoDiagram implements SPseudoDiagramModel<ASCIICoordinateOffset, ASCIIAbstractDiagram> {
    public ASCIISPseudoDiagram(ASCIIAbstractDiagram parent, DiagramStyle style) {
        super(parent, style);
    }
}

class ASCIISWPseudoDiagram extends ASCIIPseudoDiagram implements SPseudoDiagramModel<ASCIICoordinateOffset, ASCIIAbstractDiagram> {
    public ASCIISWPseudoDiagram(ASCIIAbstractDiagram parent, DiagramStyle style) {
        super(parent, style);
    }
}

class ASCIIWPseudoDiagram extends ASCIIPseudoDiagram implements WPseudoDiagramModel<ASCIICoordinateOffset, ASCIIAbstractDiagram> {
    public ASCIIWPseudoDiagram(ASCIIAbstractDiagram parent, DiagramStyle style) {
        super(parent, style);
    }
}