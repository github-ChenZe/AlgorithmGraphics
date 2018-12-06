package testsrc;

import van.de.la.sehen.diagram.prototypediagram.asciiprototypediagram.ASCIIDiagram;
import van.de.la.sehen.warning.WarningStream;
import van.de.la.sehen.xmldiagramrenderer.ASCIIXMLDiagramRenderer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;

public class ASCIIDiagramTest {
    public static void test() {
        Util.output("asciidiagram", "testasc", (xmlAndCssfile, outpath) -> {
            File result = new File(outpath + xmlAndCssfile.x.getName() + ".output");
            try (PrintStream out = new PrintStream(result)) {
                new ASCIIXMLDiagramRenderer().renderFromFile(
                        xmlAndCssfile.x,
                        xmlAndCssfile.y)
                        .dump(out);
            } catch (FileNotFoundException e) {
                WarningStream.putWarning("File not found processing " + xmlAndCssfile.x.getPath(), ASCIIDiagramTest.class);
            }
        });
    }
}
