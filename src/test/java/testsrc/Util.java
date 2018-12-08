package testsrc;

import van.de.la.sehen.util.Tuple;
import van.de.la.sehen.warning.WarningStream;
import van.de.la.sehen.warning.WarningType;
import van.de.la.sehen.xmldiagramrenderer.ASCIIXMLDiagramRenderer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.function.BiConsumer;

public class Util {
    /**
     *
     * @param dirname name like "asciidiagram" without any backslash
     * @param cssname name like "testasc" without css suffix
     */
    public static void output(String dirname, String cssname, BiConsumer<Tuple<File, File>, String> outputCreator) {
        boolean newdir = new File(Util.class.getResource("").getPath() + "../output/" + dirname).mkdirs();
        String outpath = Util.class.getResource("../output/" + dirname + "/").getPath();

        File asciiDiagramTestSources = new File(Util.class.getResource("../input/" + dirname + "/").getPath());
        File cssSource = new File(Util.class.getResource("../input/" + dirname + "/" + cssname + ".CSS").getPath());
        File[] resources = asciiDiagramTestSources.listFiles();
        if (resources == null) {
            WarningStream.putWarning("Unable to get test resources.", Util.class);
            return;
        }
        for (File xmlSource: resources)
        {
            if (!xmlSource.getName().endsWith(".xml")) continue;
            WarningStream.putWarning("Start testing " + xmlSource.getName() + ".", Util.class, WarningType.Note);
            try {
                outputCreator.accept(new Tuple<>(xmlSource, cssSource), outpath);
            } catch (Exception e) {
                WarningStream.putWarning("Unhandled exception occurred when testing " + xmlSource.getName() + ".",
                        Util.class);
                throw e;
            }
        }
    }
}
