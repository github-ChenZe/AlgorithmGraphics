package van.de.la.sehen.warning;

import van.de.la.sehen.util.StyledConsoleString;
import van.de.la.sehen.util.Util;

import java.awt.*;
import java.util.ArrayList;

public class WarningStream {
    public static void putWarning(String warning, Object source) {
        ArrayList<StyledConsoleString> info = new ArrayList<>();
        info.add(new StyledConsoleString(warning, Color.RED));
        info.add(new StyledConsoleString("source: " +  source.getClass().getName() + "@" + System.identityHashCode(source), Color.CYAN));
        StackTraceElement[] traces = Thread.currentThread().getStackTrace();
        for (int i = 0; i < traces.length; i++) {
            info.add(new StyledConsoleString(traces[i].toString()));
        }
        int dashCount = Util.maxNumber(info, StyledConsoleString::actualLength) + 8;

        System.out.println();
        System.out.println(Util.BOX_DRAWINGS_LIGHT_DOWN_AND_RIGHT + Util.stringMultiple(Util.BOX_DRAWINGS_LIGHT_HORIZONTAL, dashCount - 1) + Util.BOX_DRAWINGS_LIGHT_DOWN_AND_LEFT);
        for (StyledConsoleString string: info) {
            System.out.println(Util.BOX_DRAWINGS_LIGHT_VERTICAL + string.setExtent(dashCount).styledText() + Util.BOX_DRAWINGS_LIGHT_VERTICAL);
        }
        System.out.println(Util.BOX_DRAWINGS_LIGHT_UP_AND_RIGHT + Util.stringMultiple(Util.BOX_DRAWINGS_LIGHT_HORIZONTAL, dashCount - 1) + Util.BOX_DRAWINGS_LIGHT_UP_AND_LEFT);

    }

    public static void putWarning(String warning, Object source, WarningType type) {
        if (type == WarningType.InvalidSize) return;
        if (type == WarningType.Note) {
            putSuccess(warning, source);
            return;
        }
        putWarning(warning, source);
    }

    public static void putSuccess(String message, Object source) {
        ArrayList<StyledConsoleString> info = new ArrayList<>();
        info.add(new StyledConsoleString(message, Color.CYAN));
        int dashCount = Util.maxNumber(info, StyledConsoleString::actualLength) + 8;
        System.out.println();
        System.out.println(Util.BOX_DRAWINGS_LIGHT_DOWN_AND_RIGHT + Util.stringMultiple(Util.BOX_DRAWINGS_LIGHT_HORIZONTAL, dashCount - 1) + Util.BOX_DRAWINGS_LIGHT_DOWN_AND_LEFT);
        for (StyledConsoleString string: info) {
            System.out.println(Util.BOX_DRAWINGS_LIGHT_VERTICAL + string.setExtent(dashCount).styledText() + Util.BOX_DRAWINGS_LIGHT_VERTICAL);
        }
        System.out.println(Util.BOX_DRAWINGS_LIGHT_UP_AND_RIGHT + Util.stringMultiple(Util.BOX_DRAWINGS_LIGHT_HORIZONTAL, dashCount - 1) + Util.BOX_DRAWINGS_LIGHT_UP_AND_LEFT);

    }


}

class TestWarning {
    void warn() {
        WarningStream.putWarning("For test only. I don't know why this is called.", this);
    }

    public static void main(String[] args) {
        new TestWarning().warn();
    }
}
