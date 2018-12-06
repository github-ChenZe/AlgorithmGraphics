package van.de.la.sehen.diagram.displayeddiagram;

import van.de.la.sehen.diagram.prototypediagram.Diagram;
import van.de.la.sehen.diagram.readerinterface.DiagramNode;
import van.de.la.sehen.diagram.readerinterface.attributereading.DiagramAttributeDoubleParticle;
import van.de.la.sehen.diagram.readerinterface.diagrambuilder.DiagramBuilder;
import van.de.la.sehen.diagram.readerinterface.elementreading.DiagramElementsCollection;
import van.de.la.sehen.diagram.readerinterface.fieldreading.DiagramFieldsCollection;
import van.de.la.sehen.diagramimage.PixelCanvas;
import van.de.la.sehen.diagramimage.PortableDiagramCanvas;
import van.de.la.sehen.diagramstyle.DiagramStyle;
import van.de.la.sehen.dimensionparticle.sizeparticle.IntDimensionComponent;
import van.de.la.sehen.warning.WarningStream;

public class MaskDiagram extends PaneDiagram {
    public MaskDiagram(Diagram parent, DiagramStyle style) {
        super(parent, style);
    }

    public <T extends DiagramBuilder<? extends Diagram>> MaskDiagram(Diagram parent, DiagramElementsCollection<T> elements, DiagramFieldsCollection fields) {
        super(parent, elements, fields);
    }

    public <T extends DiagramNode> MaskDiagram(Diagram parent, DiagramNode<T, Diagram> node) {
        super(parent, node);
    }

    @Override
    public void calculateSize() {
        super.calculateSize();
        double progress = getEmergeProgress();
        if (getEmergeReverse()) progress = 1 - progress;
        setWidth(new IntDimensionComponent((int) (getWidth().getValue() * progress)));
    }

    @Override
    public void paintDiagram(PortableDiagramCanvas canvas) {
        super.paintDiagram(canvas.generateAndPushMaskCanvas(
                getAbsoluteX(), getAbsoluteY(), getAbsoluteX().addByOffset(getWidth().toOffset()), getAbsoluteY().addByOffset(getHeight().toOffset())));
    }
}
