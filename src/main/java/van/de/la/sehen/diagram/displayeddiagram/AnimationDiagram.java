package van.de.la.sehen.diagram.displayeddiagram;

import van.de.la.sehen.diagram.prototypediagram.Diagram;
import van.de.la.sehen.diagram.readerinterface.DiagramNode;
import van.de.la.sehen.diagram.readerinterface.attributereading.DiagramAttributeDoubleParticle;
import van.de.la.sehen.diagram.readerinterface.attributereading.DiagramAttributeParticle;
import van.de.la.sehen.diagram.readerinterface.diagrambuilder.DiagramBuilder;
import van.de.la.sehen.diagram.readerinterface.elementreading.DiagramElementsCollection;
import van.de.la.sehen.diagram.readerinterface.fieldreading.DiagramFieldsCollection;
import van.de.la.sehen.diagramattributeparticle.StyleInheritWrapper;
import van.de.la.sehen.diagramimage.PixelCanvas;
import van.de.la.sehen.diagramimage.PortableDiagramCanvas;
import van.de.la.sehen.diagramstyle.DiagramStyle;
import van.de.la.sehen.warning.WarningStream;

public class AnimationDiagram extends PaneDiagram {
    public AnimationDiagram(Diagram parent, DiagramStyle style) {
        super(parent, style);
    }

    public <T extends DiagramBuilder<? extends Diagram>> AnimationDiagram(Diagram parent, DiagramElementsCollection<T> elements, DiagramFieldsCollection fields) {
        super(parent, elements, fields);
    }

    public <T extends DiagramNode> AnimationDiagram(Diagram parent, DiagramNode<T, Diagram> node) {
        super(parent, node);
    }

    @Override
    public DiagramAttributeParticle defaultAttribute(String key) {
        if (key.equals(SWAP_PROGRESS)) return new StyleInheritWrapper(()->this, ANIMATION_PROGRESS).get(0);
        if (key.equals(EMERGE_PROGRESS)) return new StyleInheritWrapper(()->this, ANIMATION_PROGRESS).get(0);
        return super.defaultAttribute(key);
    }

    @Override
    public void paintDiagram(PortableDiagramCanvas canvas) {
        super.paintDiagram(canvas);
        while (true) {
            // maskStyle(SWAP_PROGRESS, new DiagramAttributeDoubleParticle(getSwapProgress() + getAnimationStep()).toCluster());
            // maskStyle(EMERGE_PROGRESS, new DiagramAttributeDoubleParticle(getEmergeProgress() + getAnimationStep()).toCluster());
            maskStyle(ANIMATION_PROGRESS, new DiagramAttributeDoubleParticle(getAnimationProgress() + getAnimationStep()).toCluster());
            // if (getSwapProgress() > 1 || getEmergeProgress() > 1) return;
            if (getAnimationProgress() > 1) return;
            if (!(canvas instanceof PixelCanvas)) {
                WarningStream.putWarning("Animation is not supported in this canvas.", this);
                return;
            }
            canvas = ((PixelCanvas) canvas).setNextCanvas(super.generateCanvas());
        }
    }
}
