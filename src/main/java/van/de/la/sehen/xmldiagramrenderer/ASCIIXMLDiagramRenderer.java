package van.de.la.sehen.xmldiagramrenderer;

import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import van.de.la.sehen.diagram.displayeddiagram.asciidisplayeddiagram.ASCIIRootDiagram;
import van.de.la.sehen.diagramimage.asciidiagramimage.ASCIIImage;
import van.de.la.sehen.warning.WarningStream;
import van.de.la.sehen.xmldiagramtree.CSSDiagramStyleReader;
import van.de.la.sehen.xmldiagramtree.asciixmldiagramtree.ASCIIXMLDiagramTreeBuilder;

import java.io.File;
import java.io.InputStream;

/**
 * This renderer generates image by the given xml.
 */

public class ASCIIXMLDiagramRenderer {
    public ASCIIXMLDiagramRenderer() {}

    @NonNull
    public ASCIIImage renderFromFile(@NonNull File sourceFile, @Nullable File cssFile) {
        return treeToImage(new ASCIIXMLDiagramTreeBuilder().toTree(sourceFile).buildDiagram(), cssFile);
    }

    @NonNull
    public ASCIIImage renderFromStream(@NonNull InputStream stream, @Nullable File cssFile) {
        return treeToImage(new ASCIIXMLDiagramTreeBuilder().toTree(stream).buildDiagram(), cssFile);
    }

    @NonNull
    private ASCIIImage treeToImage(@NonNull ASCIIRootDiagram diagram, @Nullable File cssFile) {
        if (cssFile != null) {
            CSSDiagramStyleReader reader = new CSSDiagramStyleReader();
            reader.loadFile(cssFile);
            while (reader.hasMoreElements()) {
                diagram.pushStyle(reader.nextElement());
            }
        }

        ASCIIImage image = diagram.generateFinalImage();
        WarningStream.putSuccess("Successfully generated image.", this);
        return image;
    }

    public static void main(String[] args) {
        WarningStream.putWarning("Calling main from renderer.", ASCIIXMLDiagramRenderer.class);
    }
}
