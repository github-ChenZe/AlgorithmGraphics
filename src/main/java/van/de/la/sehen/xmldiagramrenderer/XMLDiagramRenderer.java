package van.de.la.sehen.xmldiagramrenderer;

import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import van.de.la.sehen.diagram.displayeddiagram.RootDiagram;
import van.de.la.sehen.diagramimage.PortableDiagramPixelImage;
import van.de.la.sehen.warning.WarningStream;
import van.de.la.sehen.xmldiagramtree.CSSDiagramStyleReader;
import van.de.la.sehen.xmldiagramtree.XMLDiagramTreeBuilder;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;
import java.util.List;

/**
 * This renderer generates image by the given xml.
 */

public class XMLDiagramRenderer {
    public XMLDiagramRenderer() {}

    @NonNull
    public PortableDiagramPixelImage renderFromFile(@NonNull File sourceFile, @Nullable File cssFile) {
        return treeToImage(new XMLDiagramTreeBuilder().toTree(sourceFile).buildDiagram(), cssFile);
    }

    @NonNull
    public PortableDiagramPixelImage renderFromStream(@NonNull InputStream stream, @Nullable File cssFile) {
        return treeToImage(new XMLDiagramTreeBuilder().toTree(stream).buildDiagram(), cssFile);
    }

    @NonNull
    private PortableDiagramPixelImage treeToImage(@NonNull RootDiagram diagram, @Nullable File cssFile) {
        if (cssFile != null) {
            CSSDiagramStyleReader reader = new CSSDiagramStyleReader();
            reader.loadFile(cssFile);
            while (reader.hasMoreElements()) {
                diagram.pushStyle(reader.nextElement());
            }
        }

        PortableDiagramPixelImage image = diagram.generatePortable();
        WarningStream.putSuccess("Successfully generated image.", this);
        return image;
    }

    public static void main(String[] args) {
        WarningStream.putWarning("Calling main from renderer.", XMLDiagramRenderer.class);
    }
}
