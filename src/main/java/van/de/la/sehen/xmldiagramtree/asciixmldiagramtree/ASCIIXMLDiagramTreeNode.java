package van.de.la.sehen.xmldiagramtree.asciixmldiagramtree;

import org.w3c.dom.Element;
import van.de.la.sehen.diagram.deriveddiagram.asciideriveddiagram.ASCIIFramedTextDiagram;
import van.de.la.sehen.diagram.deriveddiagram.asciideriveddiagram.ASCIITreeDiagram;
import van.de.la.sehen.diagram.displayeddiagram.asciidisplayeddiagram.*;
import van.de.la.sehen.diagram.prototypediagram.Diagram;
import van.de.la.sehen.diagram.prototypediagram.asciiprototypediagram.ASCIIDiagram;
import van.de.la.sehen.diagram.readerinterface.asciireaderinterface.ASCIIDiagramNode;
import van.de.la.sehen.diagramattributeparticle.diagramparticle.asciidiagramparticle.ASCIIDiagramLambdaPointer;
import van.de.la.sehen.diagramattributeparticle.diagramparticle.asciidiagramparticle.ASCIIPseudoDiagramByLambdaAccessor;
import van.de.la.sehen.xmldiagramtree.model.XMLDiagramTreeNodeModel;

import java.util.function.Function;
import java.util.function.Supplier;

public class ASCIIXMLDiagramTreeNode
        extends XMLDiagramTreeNodeModel<ASCIIXMLDiagramTreeNode, ASCIIDiagram, ASCIIDiagram, ASCIIPseudoDiagramByLambdaAccessor, ASCIIDiagramLambdaPointer<ASCIIDiagram>, ASCIIXMLDiagramTree>
        implements ASCIIDiagramNode<ASCIIXMLDiagramTreeNode, ASCIIDiagram> {

    public ASCIIXMLDiagramTreeNode(Element element, ASCIIXMLDiagramTreeNode parentNode, ASCIIXMLDiagramTree parentTree) {
        super(element, parentNode, parentTree);
    }

    @Override
    public ASCIIXMLDiagramTreeNode newChildNodeOf(Element element, ASCIIXMLDiagramTreeNode parentNode, ASCIIXMLDiagramTree parentTree) {
        return new ASCIIXMLDiagramTreeNode(element, parentNode, parentTree);
    }

    @Override
    public boolean isAttributeKey(String key) {
        return Diagram.isAttributeKey(key);
    }

    @Override
    public ASCIIDiagramLambdaPointer<ASCIIDiagram> newDiagramLambdaPointer(Supplier<ASCIIDiagram> supplier) {
        return new ASCIIDiagramLambdaPointer<>(supplier);
    }

    @Override
    public ASCIIPseudoDiagramByLambdaAccessor newPseudoByLambdaAccessor(Supplier<ASCIIDiagram> supplier, String pseudoname) {
        return new ASCIIPseudoDiagramByLambdaAccessor(supplier, pseudoname);
    }

    @Override
    public Function<ASCIIDiagram, ASCIIDiagram> tagToConstructor(String tag) {
        switch (tag) {
            case "table":
                return parent -> new ASCIITableDiagram(parent, this);
            case "pane":
                return parent -> new ASCIIPaneDiagram(parent, this);
            case "cell":
                return parent -> new ASCIICellDiagram(parent, this);
            case "arrow":
                return parent -> new ASCIIArrowDiagram(parent, this);
            case "tree":
                return parent -> new ASCIITreeDiagram(parent, this);
            case "framedText":
                return parent -> new ASCIIFramedTextDiagram(parent, this);
            case "verticalAlign":
                return parent -> new ASCIIVerticalAlignDiagram(parent, this);
            case "empty":
                return parent -> new ASCIIEmptyDiagram(parent, this);
        }
        return null;
    }
}
