package van.de.la.sehen.diagram.pseudodiagram;

import van.de.la.sehen.diagram.prototypediagram.AbstractDiagram;
import van.de.la.sehen.diagramimage.PortableDiagramCanvas;
import van.de.la.sehen.diagramstyle.DiagramStyle;
import van.de.la.sehen.dimensionparticle.positionparticle.CoordinateOffset;
import van.de.la.sehen.dimensionparticle.positionparticle.PositionOffset;
import van.de.la.sehen.dimensionparticle.sizeparticle.IntDimensionComponent;
import van.de.la.sehen.warning.WarningStream;

import java.lang.reflect.InvocationTargetException;
import java.util.function.Function;

public abstract class PseudoDiagram extends AbstractDiagram implements PseudoDiagramModel<CoordinateOffset, AbstractDiagram> {
    public PseudoDiagram(AbstractDiagram parent, DiagramStyle style) {
        super(parent, style);
    }

    @Override
    public void paintDiagram(PortableDiagramCanvas canvas) {

    }

    @Override
    public void calculateSize() {
        setWidth(IntDimensionComponent.zero());
        setHeight(IntDimensionComponent.zero());
    }

    @Override
    public void layout() {

    }

    @Override
    public final PositionOffset getPosition() {
        return new PositionOffset(getX(), getY());
    }

    public static final Function<AbstractDiagram, CPseudoDiagram> C = (parent) -> newPseudoDiagram(CPseudoDiagram.class, parent);
    public static final Function<AbstractDiagram, EPseudoDiagram> E = (parent) -> newPseudoDiagram(EPseudoDiagram.class, parent);
    public static final Function<AbstractDiagram, NPseudoDiagram> N = (parent) -> newPseudoDiagram(NPseudoDiagram.class, parent);
    public static final Function<AbstractDiagram, WPseudoDiagram> W = (parent) -> newPseudoDiagram(WPseudoDiagram.class, parent);
    public static final Function<AbstractDiagram, SPseudoDiagram> S = (parent) -> newPseudoDiagram(SPseudoDiagram.class, parent);
    public static final Function<AbstractDiagram, NWPseudoDiagram> NW = (parent) -> newPseudoDiagram(NWPseudoDiagram.class, parent);
    public static final Function<AbstractDiagram, SWPseudoDiagram> SW = (parent) -> newPseudoDiagram(SWPseudoDiagram.class, parent);
    public static final Function<AbstractDiagram, SEPseudoDiagram> SE = (parent) -> newPseudoDiagram(SEPseudoDiagram.class, parent);
    public static final Function<AbstractDiagram, NEPseudoDiagram> NE = (parent) -> newPseudoDiagram(NEPseudoDiagram.class, parent);

    public static <T extends PseudoDiagram> T newPseudoDiagram(Class<T> tClass, AbstractDiagram parent) {
        try {
            return tClass.getConstructor(AbstractDiagram.class, DiagramStyle.class).newInstance(parent, null);
        } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            WarningStream.putWarning("Constructor for PseudoDiagram not found or exception during construction.", PseudoDiagram.class);
        }
        return null;
    }
}

class CPseudoDiagram extends PseudoDiagram implements CPseudoDiagramModel<CoordinateOffset, AbstractDiagram> {
    public CPseudoDiagram(AbstractDiagram parent, DiagramStyle style) {
        super(parent, style);
    }
}

class EPseudoDiagram extends PseudoDiagram implements EPseudoDiagramModel<CoordinateOffset, AbstractDiagram> {
    public EPseudoDiagram(AbstractDiagram parent, DiagramStyle style) {
        super(parent, style);
    }
}

class NEPseudoDiagram extends PseudoDiagram implements NEPseudoDiagramModel<CoordinateOffset, AbstractDiagram> {
    public NEPseudoDiagram(AbstractDiagram parent, DiagramStyle style) {
        super(parent, style);
    }
}

class NPseudoDiagram extends PseudoDiagram implements  NPseudoDiagramModel<CoordinateOffset, AbstractDiagram> {
    public NPseudoDiagram(AbstractDiagram parent, DiagramStyle style) {
        super(parent, style);
    }
}

class NWPseudoDiagram extends PseudoDiagram implements NWPseudoDiagramModel<CoordinateOffset, AbstractDiagram> {
    public NWPseudoDiagram(AbstractDiagram parent, DiagramStyle style) {
        super(parent, style);
    }
}

class SEPseudoDiagram extends PseudoDiagram implements SEPseudoDiagramModel<CoordinateOffset, AbstractDiagram> {
    public SEPseudoDiagram(AbstractDiagram parent, DiagramStyle style) {
        super(parent, style);
    }
}

class SPseudoDiagram extends PseudoDiagram implements SPseudoDiagramModel<CoordinateOffset, AbstractDiagram> {
    public SPseudoDiagram(AbstractDiagram parent, DiagramStyle style) {
        super(parent, style);
    }
}

class SWPseudoDiagram extends PseudoDiagram implements SWPseudoDiagramModel<CoordinateOffset, AbstractDiagram> {
    public SWPseudoDiagram(AbstractDiagram parent, DiagramStyle style) {
        super(parent, style);
    }
}

class WPseudoDiagram extends PseudoDiagram implements WPseudoDiagramModel<CoordinateOffset, AbstractDiagram> {
    public WPseudoDiagram(AbstractDiagram parent, DiagramStyle style) {
        super(parent, style);
    }
}