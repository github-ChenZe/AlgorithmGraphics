package van.de.la.sehen.xmldiagramtree.asciixmldiagramtree;

import org.w3c.dom.Element;
import van.de.la.sehen.diagram.displayeddiagram.asciidisplayeddiagram.ASCIIRootDiagram;
import van.de.la.sehen.xmldiagramtree.model.XMLDiagramTreeModel;

public class ASCIIXMLDiagramTree extends XMLDiagramTreeModel<ASCIIXMLDiagramTreeNode, ASCIIRootDiagram> {
    @Override
    public ASCIIXMLDiagramTreeNode rootFromElement(Element root) {
        return new ASCIIXMLDiagramTreeNode(root, null, this);
    }

    public ASCIIXMLDiagramTree(Element root) {
        super(root);
    }

    public ASCIIRootDiagram buildDiagram() {
        return new ASCIIRootDiagram(getRoot());
    }
}
