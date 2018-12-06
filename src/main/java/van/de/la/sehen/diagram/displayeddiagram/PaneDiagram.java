package van.de.la.sehen.diagram.displayeddiagram;

import van.de.la.sehen.diagram.prototypediagram.CompositeDiagram;
import van.de.la.sehen.diagram.prototypediagram.Diagram;
import van.de.la.sehen.diagram.readerinterface.DiagramNode;
import van.de.la.sehen.diagram.readerinterface.diagrambuilder.DiagramBuilder;
import van.de.la.sehen.diagram.readerinterface.elementreading.DiagramElementsCollection;
import van.de.la.sehen.diagram.readerinterface.fieldreading.DiagramFieldsCollection;
import van.de.la.sehen.diagramimage.PortableDiagramCanvas;
import van.de.la.sehen.diagramstyle.DiagramStyle;
import van.de.la.sehen.dimensionparticle.positionparticle.PositionOffset;
import van.de.la.sehen.dimensionparticle.sizeparticle.IntDimensionComponent;
import van.de.la.sehen.warning.WarningStream;

import java.util.ArrayList;

public class PaneDiagram extends CompositeDiagram {
    private Diagram base;

    public void setBase(Diagram base) {
        checkMembership(base);
        this.base = base;
    }

    private ArrayList<Diagram> children = new ArrayList<>();

    public PaneDiagram(Diagram parent, DiagramStyle style) {
        super(parent, style);

    }

    public <T extends DiagramBuilder<? extends Diagram>> PaneDiagram(Diagram parent, DiagramElementsCollection<T> elements, DiagramFieldsCollection fields) {
        super(parent, fields);
        boolean baseSet = false;

        while (elements.hasChildren()) {
            DiagramBuilder<? extends Diagram> child = elements.getChildren().poll();
            if (!baseSet) setBase(child.buildDiagram(this));
            else pushChild(child.buildDiagram(this));
            baseSet = true;
        }
    }

    public <T extends DiagramNode> PaneDiagram(Diagram parent, DiagramNode<T, Diagram> node) {
        this(parent, node, node);
    }

    public void pushChild(Diagram child) {
        checkMembership(child);
        children.add(child);
    }

    @Override
    public Diagram getChildByIndex(int i) {
        if (i == 0) return this.base;
        else return this.children.get(i - 1);
    }

    @Override
    public void layoutChildren() {
        if (base == null) {
            WarningStream.putWarning("Did not set base.", this);
        }
        base.layout();
    }

    @Override
    public void setChildrenPosition() {
        base.setPosition(new PositionOffset(getPadding(), getPadding()));
    }

    @Override
    public void paintDiagram(PortableDiagramCanvas canvas) {
        base.paintDiagram(canvas);
        for (Diagram child: children) {
            child.paintDiagram(canvas);
        }
    }

    @Override
    public void calculateSize() {
        setWidth(new IntDimensionComponent(base.getWidth().toOffset().add(getPadding()).add(getPadding()).getValue()));
        setHeight(new IntDimensionComponent(base.getHeight().toOffset().add(getPadding()).add(getPadding()).getValue()));
    }
}
