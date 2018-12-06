package van.de.la.sehen.diagramimage.asciidiagramimage;

public class ASCIILiteralChar implements ASCIIImageChar {
    private Character thisChar;

    public ASCIILiteralChar(Character thisChar) {
        this.thisChar = thisChar;
    }

    @Override
    public Character toChar() {
        return thisChar;
    }

    @Override
    public ASCIIImageChar overrideBy(ASCIIImageChar overrideBy) {
        return overrideBy;
    }

    public static ASCIILiteralChar empty() {
        return new ASCIILiteralChar(' ');
    }
}
