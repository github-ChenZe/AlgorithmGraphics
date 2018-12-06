package van.de.la.sehen.diagram.deriveddiagram.asciideriveddiagram;

import van.de.la.sehen.diagram.prototypediagram.asciiprototypediagram.ASCIICompositeDiagram;
import van.de.la.sehen.diagram.prototypediagram.asciiprototypediagram.ASCIIDiagram;
import van.de.la.sehen.diagram.readerinterface.asciireaderinterface.ASCIIDiagramNode;
import van.de.la.sehen.diagramimage.asciidiagramimage.ASCIICanvas;
import van.de.la.sehen.diagramstyle.DiagramStyle;
import van.de.la.sehen.dimensionparticle.positionparticle.asciipositionparticle.ASCIIPositionOffset;
import van.de.la.sehen.warning.WarningStream;

public class ASCIIDerivedDiagram<S extends ASCIIDiagram> extends ASCIICompositeDiagram {
    private S skeleton;

    public S getSkeleton() {
        return skeleton;
    }

    public void setSkeleton(S skeleton) {
        this.skeleton = skeleton;
    }

    public ASCIIDerivedDiagram(ASCIIDiagram parent, DiagramStyle style) {
        super(parent, style);
    }

    public ASCIIDerivedDiagram(ASCIIDiagram parent, ASCIIDiagramNode node) {
        super(parent, node);
    }

    @Override
    public ASCIIDiagram getChildByIndex(int i) {
        if (i != 0) WarningStream.putWarning("Getting index > 0 from derived van.de.la.sehen.diagram.", this);
        return skeleton;
    }

    @Override
    public void layoutChildren() {
        skeleton.layout();
    }

    @Override
    public void setChildrenPosition() {
        skeleton.setPosition(ASCIIPositionOffset.zero());
    }

    @Override
    public void calculateSize() {
        setWidth(skeleton.getWidth());
        setHeight(skeleton.getHeight());
    }

    @Override
    public void paintDiagram(ASCIICanvas canvas) {
        skeleton.paintDiagram(canvas);
    }
}
