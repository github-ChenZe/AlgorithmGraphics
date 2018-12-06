package testsrc;

import van.de.la.sehen.warning.WarningStream;
import van.de.la.sehen.warning.WarningType;
import org.junit.Test;

public class TestDriver {
    @Test
    public void test() {
        WarningStream.putWarning("Start testing ascii diagrams.", TestDriver.class, WarningType.Note);
        ASCIIDiagramTest.test();
        WarningStream.putWarning("Start testing diagrams.", TestDriver.class, WarningType.Note);
        DiagramTest.test();
    }

    public static void main(String[] args) {
        new TestDriver().test();
    }
}
