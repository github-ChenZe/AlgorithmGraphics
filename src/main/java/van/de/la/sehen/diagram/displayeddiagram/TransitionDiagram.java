package van.de.la.sehen.diagram.displayeddiagram;

import van.de.la.sehen.diagram.prototypediagram.CompositeDiagram;
import van.de.la.sehen.diagram.prototypediagram.Diagram;
import van.de.la.sehen.diagram.readerinterface.DiagramNode;
import van.de.la.sehen.diagram.readerinterface.diagrambuilder.DiagramBuilder;
import van.de.la.sehen.diagram.readerinterface.elementreading.DiagramElementsCollection;
import van.de.la.sehen.diagram.readerinterface.fieldreading.DiagramFieldsCollection;
import van.de.la.sehen.diagramstyle.DiagramStyle;
import van.de.la.sehen.dimensionparticle.positionparticle.PositionOffset;
import van.de.la.sehen.dimensionparticle.sizeparticle.IntDimensionComponent;
import van.de.la.sehen.warning.WarningStream;

import java.util.ArrayList;

public class TransitionDiagram extends CompositeDiagram {
    private Diagram base;
    private Diagram reference;

    public void setBase(Diagram base) {
        checkMembership(base);
        this.base = putChildToIndex(base);
    }

    public void setReference(Diagram reference) {
        checkMembership(reference);
        this.reference = reference;
    }

    public TransitionDiagram(Diagram parent, DiagramStyle style) {
        super(parent, style);

    }

    public <T extends DiagramBuilder<? extends Diagram>> TransitionDiagram(Diagram parent, DiagramElementsCollection<T> elements, DiagramFieldsCollection fields) {
        super(parent, fields);
        boolean baseSet = false;

        DiagramBuilder<? extends Diagram> baseBuilder = elements.getChildren().poll();
        if (baseBuilder == null) {
            WarningStream.putWarning("Transition without base.", this);
            return;
        }
        setBase(baseBuilder.buildDiagram(this));

        DiagramBuilder<? extends Diagram> referenceBuilder = elements.getChildren().poll();
        if (referenceBuilder == null) {
            WarningStream.putWarning("Transition without reference.", this);
            return;
        }
        setReference(referenceBuilder.buildDiagram(this));
    }

    public <T extends DiagramNode> TransitionDiagram(Diagram parent, DiagramNode<T, Diagram> node) {
        this(parent, node, node);
    }

    @Override
    public void layoutChildren() {
        if (base == null || reference == null) {
            WarningStream.putWarning("Base or reference not assigned.", this);
        }
        reference.layout();
        base.layout();
    }

    @Override
    public void setChildrenPosition() {
        reference.setPosition(PositionOffset.zero());
        base.setPosition(PositionOffset.zero());
    }

    @Override
    public void calculateSize() {
        setWidth(new IntDimensionComponent(Math.max(base.getWidth().getValue(), reference.getWidth().getValue())));
        setHeight(new IntDimensionComponent(Math.max(base.getHeight().getValue(), reference.getHeight().getValue())));
    }
}
