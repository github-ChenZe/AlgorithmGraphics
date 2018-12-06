package van.de.la.sehen.diagram.prototypediagram.model;

public interface PaintingModel<CanvasT, ImageT> extends LayoutModel {
    void paintDiagram(CanvasT canvas);
    ImageT generateFinalImage();
}
