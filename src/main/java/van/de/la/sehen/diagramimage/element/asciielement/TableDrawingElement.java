package van.de.la.sehen.diagramimage.element.asciielement;

import van.de.la.sehen.diagramimage.asciidiagramimage.ASCIIImageChar;
import van.de.la.sehen.util.Util;
import van.de.la.sehen.warning.WarningStream;

public enum TableDrawingElement implements ASCIIImageChar {
    TL(0b1001),
    T (0b1011),
    TR(0b0011),
    R (0b0111),
    BR(0b0110),
    B (0b1110),
    BL(0b1100),
    L (0b1101),
    C (0b1111),
    HR(0b1000),
    HL(0b0010),
    HT(0b0100),
    HB(0b0001),
    HORIZONTAL(0b1010),
    VERTICAL(0b0101),
    ERROR(0b0000);

    private final int numValue;

    TableDrawingElement(int numValue) {
        this.numValue = numValue;
    }

    public TableDrawingElement plus(TableDrawingElement element) {
        for (TableDrawingElement choice: TableDrawingElement.values()) {
            if(choice.numValue == (element.numValue | this.numValue)) {
                return choice;
            }
        }
        WarningStream.putWarning("Unknown TableDrawingElement enum, element=" + element + " this=" + this + ".", this);
        return ERROR;
    }

    public Character toChar() {
        switch (this) {
            case TL:
                return Util.BOX_DRAWINGS_LIGHT_DOWN_AND_RIGHT.charAt(0);
            case T:
                return Util.BOX_DRAWINGS_LIGHT_DOWN_AND_HORIZONTAL.charAt(0);
            case TR:
                return Util.BOX_DRAWINGS_LIGHT_DOWN_AND_LEFT.charAt(0);
            case R:
                return Util.BOX_DRAWINGS_LIGHT_VERTICAL_AND_LEFT.charAt(0);
            case BR:
                return Util.BOX_DRAWINGS_LIGHT_UP_AND_LEFT.charAt(0);
            case B:
                return Util.BOX_DRAWINGS_LIGHT_UP_AND_HORIZONTAL.charAt(0);
            case BL:
                return Util.BOX_DRAWINGS_LIGHT_UP_AND_RIGHT.charAt(0);
            case L:
                return Util.BOX_DRAWINGS_LIGHT_VERTICAL_AND_RIGHT.charAt(0);
            case C:
                return Util.BOX_DRAWINGS_LIGHT_VERTICAL_AND_HORIZONTAL.charAt(0);
            case HR:
                return Util.BOX_DRAWINGS_LIGHT_RIGHT.charAt(0);
            case HT:
                return Util.BOX_DRAWINGS_LIGHT_UP.charAt(0);
            case HL:
                return Util.BOX_DRAWINGS_LIGHT_LEFT.charAt(0);
            case HB:
                return Util.BOX_DRAWINGS_LIGHT_DOWN.charAt(0);
            case HORIZONTAL:
                return Util.BOX_DRAWINGS_LIGHT_HORIZONTAL.charAt(0);
            case VERTICAL:
                return Util.BOX_DRAWINGS_LIGHT_VERTICAL.charAt(0);
            case ERROR:
                return '\ufffd';
        }
        WarningStream.putWarning("Unknown table drawing element.", this);
        return null;
    }

    @Override
    public ASCIIImageChar overrideBy(ASCIIImageChar overrideBy) {
        if (overrideBy instanceof TableDrawingElement) {
            return this.plus((TableDrawingElement) overrideBy);
        } else {
            return overrideBy;
        }
    }


    public static Character getHorizontalChar() {
        return HORIZONTAL.toChar();
    }

    public static Character getVerticalChar() {
        return VERTICAL.toChar();
    }


    @Override
    public String toString() {
        return super.toString() + "'" + this.toChar() + "'";
    }
}
