package van.de.la.sehen.xmldiagramtree.model;

import org.w3c.dom.Element;

import java.util.HashMap;
import java.util.Map;

public abstract class XMLDiagramTreeModel<NodeT extends XMLDiagramTreeNodeModel<?, ?, ?, ?, ?, ?>, RootType> {
    private Map<String, NodeT> idToNode = new HashMap<>();
    private NodeT root;

    public NodeT getRoot() {
        return root;
    }

    public void associateTo(String id, NodeT node) {
        idToNode.put(id, node);
    }

    public NodeT getElementById(String id) {
        return idToNode.get(id);
    }

    // rootFromElement(root, null, this);
    public abstract NodeT rootFromElement(Element root);

    public XMLDiagramTreeModel(Element root) {
        this.root = rootFromElement(root);
    }

    public abstract RootType buildDiagram();

    @Override
    public String toString() {
        return "van.de.la.sehen.xmldiagramtree{\n" +
                "root=\n" + root +
                "\n}";
    }
}
