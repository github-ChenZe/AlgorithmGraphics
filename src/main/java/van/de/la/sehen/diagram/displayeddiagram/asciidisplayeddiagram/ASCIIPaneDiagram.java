package van.de.la.sehen.diagram.displayeddiagram.asciidisplayeddiagram;

import van.de.la.sehen.diagram.prototypediagram.asciiprototypediagram.ASCIICompositeDiagram;
import van.de.la.sehen.diagram.prototypediagram.asciiprototypediagram.ASCIIDiagram;
import van.de.la.sehen.diagram.readerinterface.asciireaderinterface.ASCIIDiagramNode;
import van.de.la.sehen.diagram.readerinterface.diagrambuilder.asciidiagrambuilder.ASCIIDiagramBuilder;
import van.de.la.sehen.diagram.readerinterface.elementreading.DiagramElementsCollection;
import van.de.la.sehen.diagram.readerinterface.fieldreading.DiagramFieldsCollection;
import van.de.la.sehen.diagramimage.asciidiagramimage.ASCIICanvas;
import van.de.la.sehen.diagramstyle.DiagramStyle;
import van.de.la.sehen.dimensionparticle.positionparticle.asciipositionparticle.ASCIIPositionOffset;
import van.de.la.sehen.dimensionparticle.sizeparticle.asciisizeparticle.ASCIIIntDimensionComponent;
import van.de.la.sehen.warning.WarningStream;

import java.util.ArrayList;

public class ASCIIPaneDiagram extends ASCIICompositeDiagram {
    private ASCIIDiagram base;

    public void setBase(ASCIIDiagram base) {
        checkMembership(base);
        this.base = base;
    }

    private ArrayList<ASCIIDiagram> children = new ArrayList<>();

    public ASCIIPaneDiagram(ASCIIDiagram parent, DiagramStyle style) {
        super(parent, style);

    }

    public <T extends ASCIIDiagramBuilder<? extends ASCIIDiagram>> ASCIIPaneDiagram(ASCIIDiagram parent, DiagramElementsCollection<T> elements, DiagramFieldsCollection fields) {
        super(parent, fields);
        boolean baseSet = false;

        while (elements.hasChildren()) {
            ASCIIDiagramBuilder<? extends ASCIIDiagram> child = elements.getChildren().poll();
            if (!baseSet) setBase(child.buildDiagram(this));
            else pushChild(child.buildDiagram(this));
            baseSet = true;
        }
    }

    public <T extends ASCIIDiagramNode> ASCIIPaneDiagram(ASCIIDiagram parent, ASCIIDiagramNode<T, ASCIIDiagram> node) {
        this(parent, node, node);
    }

    public void pushChild(ASCIIDiagram child) {
        checkMembership(child);
        children.add(child);
    }

    @Override
    public ASCIIDiagram getChildByIndex(int i) {
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
        base.setPosition(new ASCIIPositionOffset(getPadding(), getPadding()));
    }

    @Override
    public void paintDiagram(ASCIICanvas canvas) {
        base.paintDiagram(canvas);
        for (ASCIIDiagram child: children) {
            child.paintDiagram(canvas);
        }
    }

    @Override
    public void calculateSize() {
        setWidth(new ASCIIIntDimensionComponent(base.getWidth().toOffset().add(getPadding()).add(getPadding()).getValue()));
        setHeight(new ASCIIIntDimensionComponent(base.getHeight().toOffset().add(getPadding()).add(getPadding()).getValue()));
    }
}
