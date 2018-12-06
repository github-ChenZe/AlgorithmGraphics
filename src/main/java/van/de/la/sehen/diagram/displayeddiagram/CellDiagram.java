package van.de.la.sehen.diagram.displayeddiagram;

import van.de.la.sehen.diagram.prototypediagram.Diagram;
import van.de.la.sehen.diagram.readerinterface.DiagramNode;
import van.de.la.sehen.diagram.readerinterface.elementreading.DiagramElementsCollection;
import van.de.la.sehen.diagram.readerinterface.fieldreading.DiagramFieldsCollection;
import van.de.la.sehen.diagramimage.PortableDiagramCanvas;
import van.de.la.sehen.diagramstyle.DiagramStyle;
import van.de.la.sehen.dimensionparticle.sizeparticle.IntDimensionComponent;
import van.de.la.sehen.warning.WarningStream;

import java.awt.*;


/* 仅仅文字本身是被绘制的，边框等等都是由容器绘制的 */
public class CellDiagram extends Diagram {
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    private String text;

    public Font getFont() {
        return font;
    }

    private Font font;

    public CellDiagram(String text, Diagram parent, DiagramStyle style) {
        super(parent, style);
        this.text = text;
        font = new Font(getFontName(), Font.PLAIN, getFontSize());
    }

    public CellDiagram(Diagram parent, DiagramElementsCollection elements, DiagramFieldsCollection fields) {
        super(parent, fields);
        this.text = elements.getContent();
        this.font = new Font(getFontName(), Font.PLAIN, getFontSize());
        if (elements.hasChildren()) {
            WarningStream.putWarning("None empty cell tag.", this);
        }
    }

    public CellDiagram(Diagram parent, DiagramNode node) {
        this(parent, node, node);
    }

    @Override
    public void paintDiagram(PortableDiagramCanvas canvas) {
        canvas.generateAndPushString(getText(), getAbsoluteX().addByOffset(getPadding()), getAbsoluteY().addByOffset(getPadding()), getFontName(), getFontSize(), getFontColor());
    }

    //TODO: why is here a calculateSize in layout?
    @Override
    public void layout() {
        super.layout();
        calculateSize();
    }

    @Override
    public void calculateSize() {
        int stringWidth = PortableDiagramCanvas.getStringWidth(getText(), getFontName(), getFontSize());
        int stringHeight = PortableDiagramCanvas.getStringHeight(getText(), getFontName(), getFontSize());
        setWidth(new IntDimensionComponent(stringWidth + 2 * getPadding().getValue()));
        setHeight(new IntDimensionComponent(stringHeight + 2 * getPadding().getValue()));
    }
}
