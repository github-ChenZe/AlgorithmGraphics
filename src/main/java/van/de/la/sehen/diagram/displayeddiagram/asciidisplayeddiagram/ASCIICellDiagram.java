package van.de.la.sehen.diagram.displayeddiagram.asciidisplayeddiagram;

import van.de.la.sehen.diagram.prototypediagram.asciiprototypediagram.ASCIIDiagram;
import van.de.la.sehen.diagram.readerinterface.asciireaderinterface.ASCIIDiagramNode;
import van.de.la.sehen.diagram.readerinterface.elementreading.DiagramElementsCollection;
import van.de.la.sehen.diagram.readerinterface.fieldreading.DiagramFieldsCollection;
import van.de.la.sehen.diagramimage.asciidiagramimage.ASCIICanvas;
import van.de.la.sehen.diagramstyle.DiagramStyle;
import van.de.la.sehen.dimensionparticle.sizeparticle.asciisizeparticle.ASCIIIntDimensionComponent;
import van.de.la.sehen.warning.WarningStream;


/* 仅仅文字本身是被绘制的，边框等等都是由容器绘制的 */
public class ASCIICellDiagram extends ASCIIDiagram {
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    private String text;

    public ASCIICellDiagram(String text, ASCIIDiagram parent, DiagramStyle style) {
        super(parent, style);
        this.text = text;
    }

    public ASCIICellDiagram(ASCIIDiagram parent, DiagramElementsCollection elements, DiagramFieldsCollection fields) {
        super(parent, fields);
        this.text = elements.getContent();
        if (elements.hasChildren()) {
            WarningStream.putWarning("None empty cell tag.", this);
        }
    }

    public ASCIICellDiagram(ASCIIDiagram parent, ASCIIDiagramNode node) {
        this(parent, node, node);
    }

    @Override
    public void paintDiagram(ASCIICanvas canvas) {
        canvas.generateAndPushString(getText(), getAbsoluteX().addByOffset(getPadding()), getAbsoluteY().addByOffset(getPadding()), getFontColor());
    }

    //TODO: why is here a calculateSize in layout?
    @Override
    public void layout() {
        super.layout();
        calculateSize();
    }

    @Override
    public void calculateSize() {
        setWidth(new ASCIIIntDimensionComponent(text.length() + 2 * getPadding().getValue()));
        setHeight(new ASCIIIntDimensionComponent(1 + 2 * getPadding().getValue()));
    }
}
