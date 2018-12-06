package van.de.la.sehen.diagramimage.asciidiagramimage;

import van.de.la.sehen.diagramimage.element.asciielement.TableDrawingElement;
import van.de.la.sehen.warning.WarningStream;

import java.io.OutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;

public class ASCIIImage {
    private int rows;
    private int columns;

    public ASCIIImage(int columns, int rows) {
        this.rows = rows;
        this.columns = columns;
        characters = new ASCIIImageChar[rows][columns];
        for (int i = 0; i < rows; i++)
            for (int j = 0; j < columns; j++)
                characters[i][j] = ASCIILiteralChar.empty();
    }

    private ASCIIImageChar[][] characters;

    public ASCIIImage setChar(int row, int column, ASCIIImageChar imageChar) {
        characters[row][column] = characters[row][column].overrideBy(imageChar);
        return this;
    }

    public ASCIIImage setChar(int row, int column, Character imageChar) {
        return setChar(row, column, new ASCIILiteralChar(imageChar));
    }

    public void dump(PrintStream out) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                out.print(characters[i][j].toChar());
            }
            out.println();
        }
    }

    public void dump() {
        dump(System.out);
    }
}
