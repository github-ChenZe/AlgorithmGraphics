package van.de.la.sehen.xmldiagramtree;

import org.w3c.dom.Element;
import van.de.la.sehen.diagram.displayeddiagram.RootDiagram;
import van.de.la.sehen.xmldiagramtree.model.XMLDiagramTreeModel;

public class XMLDiagramTree extends XMLDiagramTreeModel<XMLDiagramTreeNode, RootDiagram> {
    @Override
    public XMLDiagramTreeNode rootFromElement(Element root) {
        return new XMLDiagramTreeNode(root, null, this);
    }

    public XMLDiagramTree(Element root) {
        super(root);
    }

    public RootDiagram buildDiagram() {
        return new RootDiagram(getRoot());
    }
}
