package van.de.la.sehen.xmldiagramtree.model;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;
import van.de.la.sehen.warning.WarningStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public abstract class XMLDiagramTreeBuilderModel<TreeT extends XMLDiagramTreeModel<?, ?>> {
    public DocumentBuilder newDocumentBuilder() {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            return  factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
            WarningStream.putWarning("Cannot create DocumentBuilder.", this);
        }
        return null;
    }

    @Deprecated
    public TreeT toTree(String filename) {
        try {
            DocumentBuilder builder = newDocumentBuilder();
            File file = new File(filename);
            Document document = builder.parse(file);
            return toTree(document);
        } catch (SAXException | IOException e) {
            e.printStackTrace();
            WarningStream.putWarning("XML processing error during reading File.", this);
        }
        return null;
    }

    public TreeT toTree(File sourceFile) {
        try {
            DocumentBuilder builder = newDocumentBuilder();
            Document document = builder.parse(sourceFile);
            return toTree(document);
        } catch (SAXException | IOException e) {
            e.printStackTrace();
            WarningStream.putWarning("XML processing error during reading File.", this);
        }
        return null;
    }

    public TreeT toTree(Document document) {
        Element root = document.getDocumentElement();
        return buildTree(root);
    }

    public TreeT toTree(InputStream in) {
        try {
            DocumentBuilder builder = newDocumentBuilder();
            Document document = builder.parse(in);
            return toTree(document);
        } catch (SAXException | IOException e) {
            e.printStackTrace();
            WarningStream.putWarning("XML processing error during reading InputStream.", this);
        }
        return null;
    }

    public abstract TreeT buildTree(Element root);
}
