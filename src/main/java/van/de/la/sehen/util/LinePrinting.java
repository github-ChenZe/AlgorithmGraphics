package van.de.la.sehen.util;

import org.springframework.lang.Nullable;
import van.de.la.sehen.diagramimage.asciidiagramimage.ASCIIImage;
import van.de.la.sehen.diagramimage.asciidiagramimage.ASCIIImageChar;
import van.de.la.sehen.diagramimage.element.asciielement.TableDrawingElement;
import van.de.la.sehen.warning.WarningStream;

import java.util.ArrayList;
import java.util.List;

public class LinePrinting {
    private static int strictFloor(double value) {
        return (int) Math.ceil(value) - 1;
    }

    private static int floor(double value) {
        return (int) Math.floor(value);
    }

    private static int ceiling(double value) {
        return (int) Math.ceil(value);
    }

    private static int strictCeiling(double value) {
        return (int) Math.floor(value) + 1;
    }

    private static boolean isDiffLegal(Tuple<Integer, Integer> diff) {
        return ( diff.x == 0 || diff.x == -1 || diff.x == 1 ) && ( diff.y == 0 || diff.y == -1 || diff.y == 1 );
    }

    private static Tuple<Integer, Integer> diff(Tuple<Integer, Integer> lhs, Tuple<Integer, Integer> rhs) {
        return new Tuple<>(rhs.x - lhs.x, rhs.y - lhs.y);
    }

    @Nullable
    private static Tuple<Integer, Integer> intermediate(Tuple<Integer, Integer> lhs, Tuple<Integer, Integer> rhs) {
        Tuple<Integer, Integer> diff = diff(lhs, rhs);
        if (!isDiffLegal(diff)) {
            System.out.println("Illegal " + lhs.x + " " + lhs.y + " " + rhs.x + " " + rhs.y);
            WarningStream.putWarning("Illegal diff.", LinePrinting.class);
            return null;
        }
        if (diff.x == 0 || diff.y == 0) return null; // do not need any intermediate
        return new Tuple<>(lhs.x + diff.x, lhs.y);
    }

    public static final int X_INC_1 = 0;
    public static final int X_DEC_1 = 1;
    public static final int Y_INC_1 = 2;
    public static final int Y_DEC_1 = 3;

    private static int direction(Tuple<Integer, Integer> tupleNext, Tuple<Integer, Integer> tupleThis) {
        if (tupleNext.x - tupleThis.x == 1) return X_INC_1;
        if (tupleNext.x - tupleThis.x == -1) return X_DEC_1;
        if (tupleNext.y - tupleThis.y == 1) return Y_INC_1;
        if (tupleNext.y - tupleThis.y == -1) return Y_DEC_1;
        return -100;
    }

    private static int towards(int value, int towards) {
        if (value == towards) return value;
        return value < towards ? value + 1 : value - 1;
    }

    public static List<Tuple<Integer, Integer>> drawHorizontal(int beginX, int endX, double commonY) {
        List<Tuple<Integer, Integer>> list = new ArrayList<>();
        int x = beginX;
        while (x != endX) {
            list.add(new Tuple<>(x, floor(commonY)));
            x = towards(x, endX);
        }
        list.add(new Tuple<>(endX, floor(commonY)));
        return list;
    }

    public static List<Tuple<Integer, Integer>> drawHorizontal(double beginX, double endX, double commonY) {
        return drawHorizontal(floor(beginX), floor(endX), commonY);
    }

    public static List<Tuple<Integer, Integer>> drawVertical(int beginY, int endY, double commonX) {
        List<Tuple<Integer, Integer>> list = new ArrayList<>();
        int y = beginY;
        while (y != endY) {
            list.add(new Tuple<>(floor(commonX), y));
            y = towards(y, endY);
        }
        list.add(new Tuple<>(floor(commonX), y));
        return list;
    }

    public static List<Tuple<Integer, Integer>> drawVertical(double beginY, double endY, double commonX) {
        return drawVertical(floor(beginY), floor(endY), commonX);
    }

    public static List<Tuple<Integer, Integer>> drawDirect(double beginX, double beginY, double endX, double endY) {
        List<Tuple<Integer, Integer>> list = drawHorizontal(beginX, endX, beginY);
        list.addAll(drawVertical(beginY, endY, endX));
        return list;
    }

    public static void draw(double beginX, double beginY, double endX, double endY, ASCIIImage image) {
        if (beginX > endX) {
            draw(endX, endY, beginX, beginY, image);
            return;
        }
        TableDrawingElement[] ending = new TableDrawingElement[4];
        ending[X_INC_1] = TableDrawingElement.HR;
        ending[X_DEC_1] = TableDrawingElement.HL;
        ending[Y_INC_1] = TableDrawingElement.HB;
        ending[Y_DEC_1] = TableDrawingElement.HT;
        TableDrawingElement[][] traverse = new TableDrawingElement[4][4];
        for (int i = 0; i < 4; i++)
            for (int j = 0; j < 4; j++) traverse[i][j] = TableDrawingElement.ERROR;
        traverse[X_INC_1][X_INC_1] = TableDrawingElement.HORIZONTAL;
        traverse[X_INC_1][Y_INC_1] = TableDrawingElement.TR;
        traverse[X_INC_1][X_DEC_1] = traverse[X_INC_1][X_INC_1];
        traverse[X_INC_1][Y_DEC_1] = TableDrawingElement.BR;

        traverse[Y_INC_1][X_INC_1] = TableDrawingElement.BL;
        traverse[Y_INC_1][Y_INC_1] = TableDrawingElement.VERTICAL;
        traverse[Y_INC_1][X_DEC_1] = traverse[X_INC_1][X_DEC_1];
        traverse[Y_INC_1][Y_DEC_1] = traverse[Y_INC_1][Y_INC_1];

        traverse[X_DEC_1][X_INC_1] = traverse[X_INC_1][X_DEC_1];
        traverse[X_DEC_1][Y_INC_1] = TableDrawingElement.TL;
        traverse[X_DEC_1][X_DEC_1] = traverse[X_INC_1][X_INC_1];
        traverse[X_DEC_1][Y_DEC_1] = traverse[Y_INC_1][X_INC_1];

        traverse[Y_DEC_1][X_INC_1] = traverse[X_DEC_1][Y_INC_1];
        traverse[Y_DEC_1][Y_INC_1] = traverse[Y_INC_1][Y_INC_1];
        traverse[Y_DEC_1][X_DEC_1] = traverse[X_INC_1][Y_INC_1];
        traverse[Y_DEC_1][Y_DEC_1] = traverse[Y_INC_1][Y_INC_1];

        double x = beginX;
        double y = beginY;
        double slope = (endY - beginY) / (endX - beginX);
        List<Tuple<Integer, Integer>> cellThrough = new ArrayList<>();
        while (Math.abs(x - endX) > 1 || Math.abs(y - endY) > 1) {
            if (slope > 0) {
                if ((strictCeiling(y) - y) > slope * (strictCeiling(x) - x)) { // reaches right side first
                    y += (strictCeiling(x) - x) * slope;
                    x = strictCeiling(x);
                    // y += slope;
                } else if ((strictCeiling(y) - y) < slope * (strictCeiling(x) - x)) { // reaches top side first
                    x += (strictCeiling(y) - y) / slope;
                    y = strictCeiling(y);
                } else { // reaches intersection of vertical an horizontal
                    cellThrough.add(new Tuple<>(floor(x) + 1, floor(y)));
                    x += (strictCeiling(y) - y) / slope;
                    y = strictCeiling(y);
                }
                cellThrough.add(new Tuple<>(floor(x), floor(y)));
            } else if (slope < 0) {
                if ((strictFloor(y) - y) / slope > (strictCeiling(x) - x)) { // reaches right side first
                    y += (strictCeiling(x) - x) * slope;
                    x = strictCeiling(x);
                } else if ((strictFloor(y) - y) / slope < (strictCeiling(x) - x)) { // reaches bottom side first
                    x += (strictFloor(y) - y) / slope;
                    y = strictFloor(y);
                } else { // reaches intersection of vertical an horizontal
                    cellThrough.add(new Tuple<>(floor(x) + 1, strictFloor(y)));
                    x += (strictFloor(y) - y) / slope;
                    y = strictFloor(y);
                }
                cellThrough.add(new Tuple<>(floor(x), strictFloor(y)));
            } else if (slope == 0) {
                x += 1;
                cellThrough.add(new Tuple<>(floor(x), floor(y)));
            } else if (Double.isFinite(slope)) {
                y += 1;
                cellThrough.add(new Tuple<>(floor(x), floor(y)));
            }
        }
        Tuple<Integer, Integer> firstTuple = cellThrough.get(0);
        Tuple<Integer, Integer> lastTuple = cellThrough.get(cellThrough.size() - 1);
        cellThrough.addAll(0, drawDirect(beginX, beginY, firstTuple.x, firstTuple.y));
        cellThrough.addAll(cellThrough.size(), drawDirect(lastTuple.x, lastTuple.y, endX, endY));
        for (int i = 0; i < cellThrough.size() - 1; i++) { // Fill in the gap caused by float-operation diff
            Tuple<Integer, Integer> tupleCurrent = cellThrough.get(i);
            Tuple<Integer, Integer> tupleNext = cellThrough.get(i + 1);
            if (tupleCurrent.x.equals(tupleNext.x) && tupleCurrent.y.equals(tupleNext.y)) {
                cellThrough.remove(i + 1);
                i--;
                continue;
            }
            Tuple<Integer, Integer> intermediate = intermediate(tupleCurrent, tupleNext);
            if (intermediate != null) cellThrough.add(i + 1, intermediate);
        }
        for (int i = 0; i < cellThrough.size(); i++) {
            Tuple<Integer, Integer> tupleCurrent = cellThrough.get(i);
            if (i == 0) {
                Tuple<Integer, Integer> tupleNext = cellThrough.get(i + 1);
                image.setChar(tupleCurrent.y, tupleCurrent.x, ending[direction(tupleNext, tupleCurrent)]);
            } else if (i == cellThrough.size() - 1) {
                Tuple<Integer, Integer> tuplePrevious = cellThrough.get(i - 1);
                // The order of tuples is placed so, as ending point behaviour reverse the starting point behaviour
                image.setChar(tupleCurrent.y, tupleCurrent.x, ending[direction(tuplePrevious, tupleCurrent)]);
            } else {
                Tuple<Integer, Integer> tuplePrevious = cellThrough.get(i - 1);
                Tuple<Integer, Integer> tupleNext = cellThrough.get(i + 1);
                image.setChar(tupleCurrent.y, tupleCurrent.x, traverse[direction(tupleCurrent, tuplePrevious)][direction(tupleNext, tupleCurrent)]);
            }
        }

    }

    public static void main(String[] args) throws Exception {
        int W_SIZE = 200;
        int H_SIZE = 62;
        while (true) {
            for (int d = -20; d < 21; d++) {
                ASCIIImage image = new ASCIIImage(H_SIZE, W_SIZE);
                draw(50, 21, 50 + 2 * d, 21 + (20 - Math.abs(d)), image);
                image.dump();
                Thread.sleep(25);
            }
            for (int d = -19; d < 20; d++) {
                ASCIIImage image = new ASCIIImage(H_SIZE, W_SIZE);
                draw(50, 21, 50 - 2 * d, 21 - (20 - Math.abs(d)), image);
                image.dump();
                Thread.sleep(25);
            }
        }

    }
}
