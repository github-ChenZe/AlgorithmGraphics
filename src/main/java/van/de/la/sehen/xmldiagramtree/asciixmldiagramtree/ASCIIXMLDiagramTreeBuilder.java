package van.de.la.sehen.xmldiagramtree.asciixmldiagramtree;

import org.w3c.dom.Element;
import van.de.la.sehen.xmldiagramtree.model.XMLDiagramTreeBuilderModel;

public class ASCIIXMLDiagramTreeBuilder extends XMLDiagramTreeBuilderModel<ASCIIXMLDiagramTree> {
    @Override
    public ASCIIXMLDiagramTree buildTree(Element root) {
        return new ASCIIXMLDiagramTree(root);
    }
}
