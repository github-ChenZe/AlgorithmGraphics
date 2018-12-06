package van.de.la.sehen.xmldiagramtree;

import org.w3c.dom.Element;
import van.de.la.sehen.diagram.deriveddiagram.FramedTextDiagram;
import van.de.la.sehen.diagram.deriveddiagram.TreeDiagram;
import van.de.la.sehen.diagram.displayeddiagram.*;
import van.de.la.sehen.diagram.prototypediagram.Diagram;
import van.de.la.sehen.diagram.readerinterface.DiagramNode;
import van.de.la.sehen.diagramattributeparticle.diagramparticle.DiagramLambdaPointer;
import van.de.la.sehen.diagramattributeparticle.diagramparticle.PseudoDiagramByLambdaAccessor;
import van.de.la.sehen.xmldiagramtree.model.XMLDiagramTreeNodeModel;

import java.util.function.Function;
import java.util.function.Supplier;

public class XMLDiagramTreeNode
        extends XMLDiagramTreeNodeModel<XMLDiagramTreeNode, Diagram, Diagram, PseudoDiagramByLambdaAccessor, DiagramLambdaPointer<Diagram>, XMLDiagramTree>
        implements DiagramNode<XMLDiagramTreeNode, Diagram> {

    public XMLDiagramTreeNode(Element element, XMLDiagramTreeNode parentNode, XMLDiagramTree parentTree) {
        super(element, parentNode, parentTree);
    }

    @Override
    public XMLDiagramTreeNode newChildNodeOf(Element element, XMLDiagramTreeNode parentNode, XMLDiagramTree parentTree) {
        return new XMLDiagramTreeNode(element, parentNode, parentTree);
    }

    @Override
    public boolean isAttributeKey(String key) {
        return Diagram.isAttributeKey(key);
    }

    @Override
    public DiagramLambdaPointer<Diagram> newDiagramLambdaPointer(Supplier<Diagram> supplier) {
        return new DiagramLambdaPointer<>(supplier);
    }

    @Override
    public PseudoDiagramByLambdaAccessor newPseudoByLambdaAccessor(Supplier<Diagram> supplier, String pseudoname) {
        return new PseudoDiagramByLambdaAccessor(supplier, pseudoname);
    }

    @Override
    public Function<Diagram, Diagram> tagToConstructor(String tag) {
        switch (tag) {
            case "table":
                return parent -> new TableDiagram(parent, this);
            case "pane":
                return parent -> new PaneDiagram(parent, this);
            case "cell":
                return parent -> new CellDiagram(parent, this);
            case "arrow":
                return parent -> new ArrowDiagram(parent, this);
            case "tree":
                return parent -> new TreeDiagram(parent, this);
            case "framedText":
                return parent -> new FramedTextDiagram(parent, this);
            case "verticalAlign":
                return parent -> new VerticalAlignDiagram(parent, this);
            case "empty":
                return parent -> new EmptyDiagram(parent, this);
            case "mask":
                return parent -> new MaskDiagram(parent, this);
            case "animation":
                return parent -> new AnimationDiagram(parent, this);
        }
        return null;
    }
}
