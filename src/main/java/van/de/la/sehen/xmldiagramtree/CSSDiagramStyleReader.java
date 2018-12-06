package van.de.la.sehen.xmldiagramtree;

import van.de.la.sehen.warning.WarningStream;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Enumeration;

public class CSSDiagramStyleReader implements Enumeration<CSSDiagramStyleList> {
    private BufferedReader reader;
    private String lineCached;

    public CSSDiagramStyleReader() {}

    @Deprecated
    public void loadFile(String filename) {
        try {
            reader = new BufferedReader(new FileReader(filename));
        } catch (IOException e) {
            WarningStream.putWarning("File not found.", this);
            e.printStackTrace();
        }
    }

    public void loadFile(File cssFile) {
        try {
            reader = new BufferedReader(new FileReader(cssFile));
        } catch (IOException e) {
            WarningStream.putWarning("File not found.", this);
            e.printStackTrace();
        }
    }

    @Override
    public boolean hasMoreElements() {
        try {
            do {
                if (!reader.ready()) return false;
                String line = reader.readLine();
                if (!line.isEmpty()) {
                    lineCached = line;
                    return true;
                }
            } while (reader.ready());
            return false;
        } catch (IOException e) {
            WarningStream.putWarning("Premature end of CSS file.", this);
            e.printStackTrace();
        }
        return false;
    }

    private String firstWord(String line) {
        return line.replace("#", "").replace(".", "").split(" ")[0];
    }

    @Override
    public CSSDiagramStyleList nextElement() {
        String key = firstWord(lineCached);
        CSSDiagramStyleList styleList;
        if (lineCached.charAt(0) == '.') {
            styleList = new CSSClassDiagramStyleList(key);
        }
        else if (lineCached.charAt(0) == '#') {
            styleList = new CSSIdDiagramStyleList(key);
        } else {
            styleList = new CSSTagDiagramStyleList(key);
        }
        while (true) {
            try {
                if ((lineCached = reader.readLine()).trim().equals("}")) break;
                String[] keyValue = lineCached.split("[:;]");
                String attributeName = keyValue[0].trim();
                String value = keyValue[1].trim();
                styleList.put(attributeName, value);
            } catch (IOException e) {
                WarningStream.putWarning("Premature end of CSS block.", this);
                e.printStackTrace();
            }
        }
        return styleList;
    }
}
