package van.de.la.sehen.xmldiagramtree;

import org.w3c.dom.Element;
import van.de.la.sehen.xmldiagramtree.model.XMLDiagramTreeBuilderModel;

public class XMLDiagramTreeBuilder extends XMLDiagramTreeBuilderModel<XMLDiagramTree> {
    @Override
    public XMLDiagramTree buildTree(Element root) {
        return new XMLDiagramTree(root);
    }
}
