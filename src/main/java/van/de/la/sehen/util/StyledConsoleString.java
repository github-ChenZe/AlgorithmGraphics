package van.de.la.sehen.util;

import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import van.de.la.sehen.warning.WarningStream;

import java.awt.*;

public class StyledConsoleString {
    public static final StyledConsoleString EMPTY = new StyledConsoleString("");

    @Nullable
    private Color textColor;
    @Nullable
    private Color backgroundColor;
    private String text;
    private int extent = -1;

    public StyledConsoleString setExtent(int extent) {
        this.extent = extent;
        return this;
    }

    public StyledConsoleString setTextColor(@Nullable Color textColor) {
        this.textColor = textColor;
        return this;
    }

    public StyledConsoleString setBackgroundColor(@Nullable Color backgroundColor) {
        this.backgroundColor = backgroundColor;
        return this;
    }

    public StyledConsoleString setText(String text) {
        this.text = text;
        return this;
    }

    public StyledConsoleString(@NonNull String text) {
        this(text, null, null);
    }

    public StyledConsoleString(@NonNull String text, @Nullable Color textColor) {
        this(text, textColor, null);
    }

    public StyledConsoleString(@NonNull String text, @Nullable Color textColor, @Nullable Color backgroundColor) {
        this.textColor = textColor;
        this.backgroundColor = backgroundColor;
        this.text = text;
    }

    public int actualLength() {
        return text.length();
    }

    public String centeringToExtent() {
        int length = actualLength();
        if (extent < length) {
            if (extent != -1) System.err.println("Error in text formatting. Extent is set but is less than length.");
            return text;
        }
        int leftPadding = (extent - length) / 2;
        int rightPadding = extent - leftPadding - length;
        return Util.spaceMultiple(leftPadding) + text + Util.spaceMultiple(rightPadding);
    }

    public String styledText() {
        return ((backgroundColor == null) ? "" : colorToANSIBackgroundEscape(backgroundColor))
                + ((textColor == null) ? "" : colorToANSITextEscape(textColor))
                + centeringToExtent()
                + Util.ANSI_RESET;
    }

    @NonNull
    public static String colorToANSITextEscape(@NonNull Color color) {
        if (color.equals(Color.BLACK)) {
            return Util.ANSI_BLACK;
        } else if (color.equals(Color.RED)) {
            return Util.ANSI_RED;
        } else if (color.equals(Color.GREEN)) {
            return Util.ANSI_GREEN;
        } else if (color.equals(Color.BLUE)) {
            return Util.ANSI_BLUE;
        } else if (color.equals(Color.CYAN)) {
            return Util.ANSI_CYAN;
        } else if (color.equals(Color.WHITE)) {
            return Util.ANSI_WHITE;
        }
        WarningStream.putWarning("ANSI escape not found for color.", StyledConsoleString.class);
        return Util.ANSI_WHITE;
    }

    @NonNull
    public static String colorToANSIBackgroundEscape(@NonNull Color color) {
        if (color.equals(Color.BLACK)) {
            return Util.ANSI_BLACK_BACKGROUND;
        } else if (color.equals(Color.RED)) {
            return Util.ANSI_RED_BACKGROUND;
        } else if (color.equals(Color.GREEN)) {
            return Util.ANSI_GREEN_BACKGROUND;
        } else if (color.equals(Color.BLUE)) {
            return Util.ANSI_BLUE_BACKGROUND;
        } else if (color.equals(Color.CYAN)) {
            return Util.ANSI_CYAN_BACKGROUND;
        } else if (color.equals(Color.WHITE)) {
            return Util.ANSI_WHITE_BACKGROUND;
        }
        WarningStream.putWarning("ANSI escape not found for color.", StyledConsoleString.class);
        return Util.ANSI_BLACK_BACKGROUND;
    }
}
