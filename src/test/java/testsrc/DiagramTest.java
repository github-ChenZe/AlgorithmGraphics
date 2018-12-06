package testsrc;

import van.de.la.sehen.diagramimage.PortableDiagramPixelImage;
import van.de.la.sehen.warning.WarningStream;
import van.de.la.sehen.xmldiagramrenderer.ASCIIXMLDiagramRenderer;
import van.de.la.sehen.xmldiagramrenderer.XMLDiagramRenderer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;

public class DiagramTest {
    public static void test() {
        Util.output("diagram", "test", (xmlAndCssfile, outpath) -> {
            PortableDiagramPixelImage image = new XMLDiagramRenderer().renderFromFile(
                    xmlAndCssfile.x,
                    xmlAndCssfile.y);
            image.exportToFile(outpath + "/" + xmlAndCssfile.x.getName() +".png", 0);
        });

    }
}
