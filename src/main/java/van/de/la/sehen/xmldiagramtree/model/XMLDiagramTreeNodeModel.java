package van.de.la.sehen.xmldiagramtree.model;

import org.w3c.dom.*;
import van.de.la.sehen.diagram.readerinterface.attributereading.DiagramAttributeCluster;
import van.de.la.sehen.diagram.readerinterface.attributereading.DiagramAttributeParticle;
import van.de.la.sehen.diagram.readerinterface.diagrambuilder.model.DiagramBuilderModel;
import van.de.la.sehen.diagram.readerinterface.elementreading.DiagramElementsCollection;
import van.de.la.sehen.diagram.readerinterface.fieldreading.DiagramFieldsCollection;
import van.de.la.sehen.diagramattributeparticle.diagramparticle.model.DiagramPointerModel;
import van.de.la.sehen.util.Util;
import van.de.la.sehen.warning.WarningStream;

import java.util.*;
import java.util.function.Function;
import java.util.function.Supplier;

public abstract class XMLDiagramTreeNodeModel<
        ThisT extends XMLDiagramTreeNodeModel<ThisT, GenerateT, ParentT, PseudoByLambdaAccessorT, DiagramLambdaPointerT, ParentTreeT>,
        GenerateT extends ParentT ,
        ParentT,
        PseudoByLambdaAccessorT extends DiagramAttributeParticle<?>,
        DiagramLambdaPointerT extends DiagramPointerModel<GenerateT, ParentT>,
        ParentTreeT extends XMLDiagramTreeModel<ThisT, ?>>
        implements DiagramElementsCollection<ThisT>, DiagramFieldsCollection, DiagramBuilderModel<GenerateT, ParentT> {
    private String nodeTag;
    private Map<String, DiagramAttributeCluster> attributes = new HashMap<>();
    private LinkedList<ThisT> children = new LinkedList<>();
    private String id;
    private List<String> classes = new ArrayList<>();
    private StringBuilder content = new StringBuilder();

    private GenerateT diagramBuilt = null;

    public boolean hasChildren() {
        return children.size() > 0;
    }

    @Override
    public List<String> getClasses() {
        return classes;
    }

    public ThisT getParentNode() {
        return parentNode;
    }

    private ThisT parentNode;

    public String getNodeTag() {
        return nodeTag;
    }

    @Override
    public Map<String, DiagramAttributeCluster> getAttributes() {
        return attributes;
    }

    @Override
    public LinkedList<ThisT> getChildren() {
        return children;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getContent() {
        return content.toString();
    }

    public abstract ThisT newChildNodeOf(Element element, ThisT parentNode, ParentTreeT parentTree);

    public XMLDiagramTreeNodeModel(Element element, ThisT parentNode, ParentTreeT parentTree) {
        this.parentNode = parentNode;
        nodeTag = element.getTagName();
        NodeList nodes = element.getChildNodes();
        for (int i = 0; i < nodes.getLength(); i++) {
            Node node = nodes.item(i);
            if (node instanceof Element) {
                children.add(newChildNodeOf((Element) node, (ThisT) this, parentTree));
            }
            else if (node instanceof Text) {
                content.append(((Text) node).getData().trim());
            }
            else {
                WarningStream.putWarning("Not a valid node type.", this);
            }
        }

        NamedNodeMap map = element.getAttributes();
        for (int i = 0; i < map.getLength(); i++) {
            Node node = map.item(i);
            // TODO: There is an dirty fix here, there can be better way to implement UNSET
            if (node.getNodeValue().trim().equals("UNSET")) {
                continue;
            }
            String key = node.getNodeName();
            if (node.getNodeValue().trim().isEmpty()) {
                WarningStream.putWarning("Setting XML field '" + key + "' to empty.", this);
            }
            if (key.equals("id")) {
                this.id = node.getNodeValue();
                parentTree.associateTo(this.id, (ThisT) this);
            }
            else if (key.equals("class")) {
                this.classes.addAll(Arrays.asList(node.getNodeValue().split(" ")));
            }
            else if (key.equals("from") || key.equals("From")) {
                key = "From";
                this.attributes.put(key, parsePseudo(node.getNodeValue(), parentTree).toCluster());
            }
            else if (key.equals("to") || key.equals("To")) {
                key = "To";
                this.attributes.put(key, parsePseudo(node.getNodeValue(), parentTree).toCluster());
            }
            else if (key.equals("VerticalAlignAnchor") || key.equals("TreeRootAnchor") || key.equals("TreeBranchAnchor")) {
                this.attributes.put(key, parsePseudo(node.getNodeValue(), parentTree).toCluster());
            }
            else if (key.equals("TreeRootArrowPerch")) {
                if (node.getNodeValue().equals("")) {
                    this.attributes.put(key, newDiagramLambdaPointer(() -> this.children.get(0).dereference()).toCluster());
                } else {
                    this.attributes.put(key, parseId(node.getNodeValue(), parentTree).toCluster());
                }
            }
            else if (isAttributeKey(key)) {
                this.attributes.put(key, clusterFromString(key, node.getNodeValue()));
            }
            else {
                WarningStream.putWarning("Not a valid node attribute: " + key + "." , this);
            }
        }
    }

    public abstract boolean isAttributeKey(String key);

    public abstract DiagramLambdaPointerT newDiagramLambdaPointer(Supplier<GenerateT> supplier);

    private DiagramLambdaPointerT parseId(String id, XMLDiagramTreeModel<ThisT, ?> parentTree) {
        return newDiagramLambdaPointer(() -> parentTree.getElementById(id).dereference());
    }

    public abstract PseudoByLambdaAccessorT newPseudoByLambdaAccessor(Supplier<GenerateT> supplier, String pseudoname);

    private PseudoByLambdaAccessorT parsePseudo(String fullname, XMLDiagramTreeModel<ThisT, ?> parentTree) {
        String[] idAndPseudo = fullname.split("::");
        if (idAndPseudo.length > 1) {
            String diagramId =idAndPseudo[0];
            String pseudoName = idAndPseudo[1];
            return newPseudoByLambdaAccessor(() -> parentTree.getElementById(diagramId).dereference(), pseudoName);
        }
        else {
            return newPseudoByLambdaAccessor(this::dereference, idAndPseudo[0]);
        }
    }

    public abstract Function<ParentT, GenerateT> tagToConstructor(String tag);

    @Override
    public GenerateT buildDiagram(ParentT parent) {
        Function<ParentT, GenerateT> constructor = tagToConstructor(getNodeTag());
        if (constructor != null) diagramBuilt = constructor.apply(parent);
        else WarningStream.putWarning("Unknown node tag " + getNodeTag() + ".", this);
        return diagramBuilt;
    }

    public String allChildren() {
        StringBuilder sb = new StringBuilder();
        for (XMLDiagramTreeNodeModel node: children) {
            sb.append(node.toString());
        }
        return sb.toString();
    }

    public String allAttribute() {
        StringBuilder sb = new StringBuilder();
        for (String key: attributes.keySet()) {
            sb.append(key);
            sb.append(": ");
            sb.append(attributes.get(key));
            sb.append(". ");
        }
        return sb.toString();
    }

    public static final String INDENT = "-   ";
    public static final String TAG_INDENT = "----";

    public String getIndent() {
        if (getParentNode() == null) {
            return INDENT;
        }
        return INDENT + getParentNode().getIndent();
    }

    public String getTagIndent() {
        if (getParentNode() == null) {
            return TAG_INDENT;
        }
        return TAG_INDENT + getParentNode().getTagIndent();
    }

    @Override
    public String toString() {
        return  getTagIndent() + Util.ANSI_CYAN + "<" + nodeTag + ">" + Util.ANSI_RESET + "\n" +
                getIndent() + "attributes=" + attributes + "\n" +
                getIndent() + "id='" + id + "'\n" +
                getIndent() + "content='" + content + "'\n" +
                allChildren();
    }

    @Override
    public GenerateT dereference() {
        if (diagramBuilt == null) WarningStream.putWarning("Dereferencing from unbuilt diagram.", this);
        return diagramBuilt;
    }
}
