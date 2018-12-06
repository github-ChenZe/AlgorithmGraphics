package van.de.la.sehen.diagramimage.asciidiagramimage;

public interface ASCIIImageChar {
    Character toChar();
    ASCIIImageChar overrideBy(ASCIIImageChar overrideBy);
}
