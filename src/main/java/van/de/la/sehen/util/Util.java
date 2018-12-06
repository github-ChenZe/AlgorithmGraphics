package van.de.la.sehen.util;

import math.geom2d.Point2D;
import math.geom2d.Vector2D;
import van.de.la.sehen.dimensionparticle.positionparticle.PositionOffset;
import van.de.la.sehen.warning.WarningStream;

import javax.annotation.Nullable;
import javax.annotation.RegEx;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Util {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
    public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
    public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
    public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
    public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
    public static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
    public static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
    public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";

    public static final String BOX_DRAWINGS_LIGHT_HORIZONTAL = "\u2500";
    public static final String BOX_DRAWINGS_HEAVY_HORIZONTAL = "\u2501";
    public static final String BOX_DRAWINGS_LIGHT_VERTICAL = "\u2502";
    public static final String BOX_DRAWINGS_HEAVY_VERTICAL = "\u2503";
    public static final String BOX_DRAWINGS_LIGHT_TRIPLE_DASH_HORIZONTAL = "\u2504";
    public static final String BOX_DRAWINGS_HEAVY_TRIPLE_DASH_HORIZONTAL = "\u2505";
    public static final String BOX_DRAWINGS_LIGHT_TRIPLE_DASH_VERTICAL = "\u2506";
    public static final String BOX_DRAWINGS_HEAVY_TRIPLE_DASH_VERTICAL = "\u2507";
    public static final String BOX_DRAWINGS_LIGHT_QUADRUPLE_DASH_HORIZONTAL = "\u2508";
    public static final String BOX_DRAWINGS_HEAVY_QUADRUPLE_DASH_HORIZONTAL = "\u2509";
    public static final String BOX_DRAWINGS_LIGHT_QUADRUPLE_DASH_VERTICAL = "\u250A";
    public static final String BOX_DRAWINGS_HEAVY_QUADRUPLE_DASH_VERTICAL = "\u250B";
    public static final String BOX_DRAWINGS_LIGHT_DOWN_AND_RIGHT = "\u250C";
    public static final String BOX_DRAWINGS_DOWN_LIGHT_AND_RIGHT_HEAVY = "\u250D";
    public static final String BOX_DRAWINGS_DOWN_HEAVY_AND_RIGHT_LIGHT = "\u250E";
    public static final String BOX_DRAWINGS_HEAVY_DOWN_AND_RIGHT = "\u250F";
    public static final String BOX_DRAWINGS_LIGHT_DOWN_AND_LEFT = "\u2510";
    public static final String BOX_DRAWINGS_DOWN_LIGHT_AND_LEFT_HEAVY = "\u2511";
    public static final String BOX_DRAWINGS_DOWN_HEAVY_AND_LEFT_LIGHT = "\u2512";
    public static final String BOX_DRAWINGS_HEAVY_DOWN_AND_LEFT = "\u2513";
    public static final String BOX_DRAWINGS_LIGHT_UP_AND_RIGHT = "\u2514";
    public static final String BOX_DRAWINGS_UP_LIGHT_AND_RIGHT_HEAVY = "\u2515";
    public static final String BOX_DRAWINGS_UP_HEAVY_AND_RIGHT_LIGHT = "\u2516";
    public static final String BOX_DRAWINGS_HEAVY_UP_AND_RIGHT = "\u2517";
    public static final String BOX_DRAWINGS_LIGHT_UP_AND_LEFT = "\u2518";
    public static final String BOX_DRAWINGS_UP_LIGHT_AND_LEFT_HEAVY = "\u2519";
    public static final String BOX_DRAWINGS_UP_HEAVY_AND_LEFT_LIGHT = "\u251A";
    public static final String BOX_DRAWINGS_HEAVY_UP_AND_LEFT = "\u251B";
    public static final String BOX_DRAWINGS_LIGHT_VERTICAL_AND_RIGHT = "\u251C";
    public static final String BOX_DRAWINGS_VERTICAL_LIGHT_AND_RIGHT_HEAVY = "\u251D";
    public static final String BOX_DRAWINGS_UP_HEAVY_AND_RIGHT_DOWN_LIGHT = "\u251E";
    public static final String BOX_DRAWINGS_DOWN_HEAVY_AND_RIGHT_UP_LIGHT = "\u251F";
    public static final String BOX_DRAWINGS_VERTICAL_HEAVY_AND_RIGHT_LIGHT = "\u2520";
    public static final String BOX_DRAWINGS_DOWN_LIGHT_AND_RIGHT_UP_HEAVY = "\u2521";
    public static final String BOX_DRAWINGS_UP_LIGHT_AND_RIGHT_DOWN_HEAVY = "\u2522";
    public static final String BOX_DRAWINGS_HEAVY_VERTICAL_AND_RIGHT = "\u2523";
    public static final String BOX_DRAWINGS_LIGHT_VERTICAL_AND_LEFT = "\u2524";
    public static final String BOX_DRAWINGS_VERTICAL_LIGHT_AND_LEFT_HEAVY = "\u2525";
    public static final String BOX_DRAWINGS_UP_HEAVY_AND_LEFT_DOWN_LIGHT = "\u2526";
    public static final String BOX_DRAWINGS_DOWN_HEAVY_AND_LEFT_UP_LIGHT = "\u2527";
    public static final String BOX_DRAWINGS_VERTICAL_HEAVY_AND_LEFT_LIGHT = "\u2528";
    public static final String BOX_DRAWINGS_DOWN_LIGHT_AND_LEFT_UP_HEAVY = "\u2529";
    public static final String BOX_DRAWINGS_UP_LIGHT_AND_LEFT_DOWN_HEAVY = "\u252A";
    public static final String BOX_DRAWINGS_HEAVY_VERTICAL_AND_LEFT = "\u252B";
    public static final String BOX_DRAWINGS_LIGHT_DOWN_AND_HORIZONTAL = "\u252C";
    public static final String BOX_DRAWINGS_LEFT_HEAVY_AND_RIGHT_DOWN_LIGHT = "\u252D";
    public static final String BOX_DRAWINGS_RIGHT_HEAVY_AND_LEFT_DOWN_LIGHT = "\u252E";
    public static final String BOX_DRAWINGS_DOWN_LIGHT_AND_HORIZONTAL_HEAVY = "\u252F";
    public static final String BOX_DRAWINGS_DOWN_HEAVY_AND_HORIZONTAL_LIGHT = "\u2530";
    public static final String BOX_DRAWINGS_RIGHT_LIGHT_AND_LEFT_DOWN_HEAVY = "\u2531";
    public static final String BOX_DRAWINGS_LEFT_LIGHT_AND_RIGHT_DOWN_HEAVY = "\u2532";
    public static final String BOX_DRAWINGS_HEAVY_DOWN_AND_HORIZONTAL = "\u2533";
    public static final String BOX_DRAWINGS_LIGHT_UP_AND_HORIZONTAL = "\u2534";
    public static final String BOX_DRAWINGS_LEFT_HEAVY_AND_RIGHT_UP_LIGHT = "\u2535";
    public static final String BOX_DRAWINGS_RIGHT_HEAVY_AND_LEFT_UP_LIGHT = "\u2536";
    public static final String BOX_DRAWINGS_UP_LIGHT_AND_HORIZONTAL_HEAVY = "\u2537";
    public static final String BOX_DRAWINGS_UP_HEAVY_AND_HORIZONTAL_LIGHT = "\u2538";
    public static final String BOX_DRAWINGS_RIGHT_LIGHT_AND_LEFT_UP_HEAVY = "\u2539";
    public static final String BOX_DRAWINGS_LEFT_LIGHT_AND_RIGHT_UP_HEAVY = "\u253A";
    public static final String BOX_DRAWINGS_HEAVY_UP_AND_HORIZONTAL = "\u253B";
    public static final String BOX_DRAWINGS_LIGHT_VERTICAL_AND_HORIZONTAL = "\u253C";
    public static final String BOX_DRAWINGS_LEFT_HEAVY_AND_RIGHT_VERTICAL_LIGHT = "\u253D";
    public static final String BOX_DRAWINGS_RIGHT_HEAVY_AND_LEFT_VERTICAL_LIGHT = "\u253E";
    public static final String BOX_DRAWINGS_VERTICAL_LIGHT_AND_HORIZONTAL_HEAVY = "\u253F";
    public static final String BOX_DRAWINGS_UP_HEAVY_AND_DOWN_HORIZONTAL_LIGHT = "\u2540";
    public static final String BOX_DRAWINGS_DOWN_HEAVY_AND_UP_HORIZONTAL_LIGHT = "\u2541";
    public static final String BOX_DRAWINGS_VERTICAL_HEAVY_AND_HORIZONTAL_LIGHT = "\u2542";
    public static final String BOX_DRAWINGS_LEFT_UP_HEAVY_AND_RIGHT_DOWN_LIGHT = "\u2543";
    public static final String BOX_DRAWINGS_RIGHT_UP_HEAVY_AND_LEFT_DOWN_LIGHT = "\u2544";
    public static final String BOX_DRAWINGS_LEFT_DOWN_HEAVY_AND_RIGHT_UP_LIGHT = "\u2545";
    public static final String BOX_DRAWINGS_RIGHT_DOWN_HEAVY_AND_LEFT_UP_LIGHT = "\u2546";
    public static final String BOX_DRAWINGS_DOWN_LIGHT_AND_UP_HORIZONTAL_HEAVY = "\u2547";
    public static final String BOX_DRAWINGS_UP_LIGHT_AND_DOWN_HORIZONTAL_HEAVY = "\u2548";
    public static final String BOX_DRAWINGS_RIGHT_LIGHT_AND_LEFT_VERTICAL_HEAVY = "\u2549";
    public static final String BOX_DRAWINGS_LEFT_LIGHT_AND_RIGHT_VERTICAL_HEAVY = "\u254A";
    public static final String BOX_DRAWINGS_HEAVY_VERTICAL_AND_HORIZONTAL = "\u254B";
    public static final String BOX_DRAWINGS_LIGHT_DOUBLE_DASH_HORIZONTAL = "\u254C";
    public static final String BOX_DRAWINGS_HEAVY_DOUBLE_DASH_HORIZONTAL = "\u254D";
    public static final String BOX_DRAWINGS_LIGHT_DOUBLE_DASH_VERTICAL = "\u254E";
    public static final String BOX_DRAWINGS_HEAVY_DOUBLE_DASH_VERTICAL = "\u254F";
    public static final String BOX_DRAWINGS_DOUBLE_HORIZONTAL = "\u2550";
    public static final String BOX_DRAWINGS_DOUBLE_VERTICAL = "\u2551";
    public static final String BOX_DRAWINGS_DOWN_SINGLE_AND_RIGHT_DOUBLE = "\u2552";
    public static final String BOX_DRAWINGS_DOWN_DOUBLE_AND_RIGHT_SINGLE = "\u2553";
    public static final String BOX_DRAWINGS_DOUBLE_DOWN_AND_RIGHT = "\u2554";
    public static final String BOX_DRAWINGS_DOWN_SINGLE_AND_LEFT_DOUBLE = "\u2555";
    public static final String BOX_DRAWINGS_DOWN_DOUBLE_AND_LEFT_SINGLE = "\u2556";
    public static final String BOX_DRAWINGS_DOUBLE_DOWN_AND_LEFT = "\u2557";
    public static final String BOX_DRAWINGS_UP_SINGLE_AND_RIGHT_DOUBLE = "\u2558";
    public static final String BOX_DRAWINGS_UP_DOUBLE_AND_RIGHT_SINGLE = "\u2559";
    public static final String BOX_DRAWINGS_DOUBLE_UP_AND_RIGHT = "\u255A";
    public static final String BOX_DRAWINGS_UP_SINGLE_AND_LEFT_DOUBLE = "\u255B";
    public static final String BOX_DRAWINGS_UP_DOUBLE_AND_LEFT_SINGLE = "\u255C";
    public static final String BOX_DRAWINGS_DOUBLE_UP_AND_LEFT = "\u255D";
    public static final String BOX_DRAWINGS_VERTICAL_SINGLE_AND_RIGHT_DOUBLE = "\u255E";
    public static final String BOX_DRAWINGS_VERTICAL_DOUBLE_AND_RIGHT_SINGLE = "\u255F";
    public static final String BOX_DRAWINGS_DOUBLE_VERTICAL_AND_RIGHT = "\u2560";
    public static final String BOX_DRAWINGS_VERTICAL_SINGLE_AND_LEFT_DOUBLE = "\u2561";
    public static final String BOX_DRAWINGS_VERTICAL_DOUBLE_AND_LEFT_SINGLE = "\u2562";
    public static final String BOX_DRAWINGS_DOUBLE_VERTICAL_AND_LEFT = "\u2563";
    public static final String BOX_DRAWINGS_DOWN_SINGLE_AND_HORIZONTAL_DOUBLE = "\u2564";
    public static final String BOX_DRAWINGS_DOWN_DOUBLE_AND_HORIZONTAL_SINGLE = "\u2565";
    public static final String BOX_DRAWINGS_DOUBLE_DOWN_AND_HORIZONTAL = "\u2566";
    public static final String BOX_DRAWINGS_UP_SINGLE_AND_HORIZONTAL_DOUBLE = "\u2567";
    public static final String BOX_DRAWINGS_UP_DOUBLE_AND_HORIZONTAL_SINGLE = "\u2568";
    public static final String BOX_DRAWINGS_DOUBLE_UP_AND_HORIZONTAL = "\u2569";
    public static final String BOX_DRAWINGS_VERTICAL_SINGLE_AND_HORIZONTAL_DOUBLE = "\u256A";
    public static final String BOX_DRAWINGS_VERTICAL_DOUBLE_AND_HORIZONTAL_SINGLE = "\u256B";
    public static final String BOX_DRAWINGS_DOUBLE_VERTICAL_AND_HORIZONTAL = "\u256C";
    public static final String BOX_DRAWINGS_LIGHT_ARC_DOWN_AND_RIGHT = "\u256D";
    public static final String BOX_DRAWINGS_LIGHT_ARC_DOWN_AND_LEFT = "\u256E";
    public static final String BOX_DRAWINGS_LIGHT_ARC_UP_AND_LEFT = "\u256F";
    public static final String BOX_DRAWINGS_LIGHT_ARC_UP_AND_RIGHT = "\u2570";
    public static final String BOX_DRAWINGS_LIGHT_DIAGONAL_UPPER_RIGHT_TO_LOWER_LEFT = "\u2571";
    public static final String BOX_DRAWINGS_LIGHT_DIAGONAL_UPPER_LEFT_TO_LOWER_RIGHT = "\u2572";
    public static final String BOX_DRAWINGS_LIGHT_DIAGONAL_CROSS = "\u2573";
    public static final String BOX_DRAWINGS_LIGHT_LEFT = "\u2574";
    public static final String BOX_DRAWINGS_LIGHT_UP = "\u2575";
    public static final String BOX_DRAWINGS_LIGHT_RIGHT = "\u2576";
    public static final String BOX_DRAWINGS_LIGHT_DOWN = "\u2577";
    public static final String BOX_DRAWINGS_HEAVY_LEFT = "\u2578";
    public static final String BOX_DRAWINGS_HEAVY_UP = "\u2579";
    public static final String BOX_DRAWINGS_HEAVY_RIGHT = "\u257A";
    public static final String BOX_DRAWINGS_HEAVY_DOWN = "\u257B";
    public static final String BOX_DRAWINGS_LIGHT_LEFT_AND_HEAVY_RIGHT = "\u257C";
    public static final String BOX_DRAWINGS_LIGHT_UP_AND_HEAVY_DOWN = "\u257D";
    public static final String BOX_DRAWINGS_HEAVY_LEFT_AND_LIGHT_RIGHT = "\u257E";
    public static final String BOX_DRAWINGS_HEAVY_UP_AND_LIGHT_DOWN = "\u257F";

    public static double calculateDivision(String expression) {
        String[] xy = expression.split("/");
        if (xy.length == 1) return Double.parseDouble(expression);
        double x = Double.parseDouble(xy[0]);
        double y = Double.parseDouble(xy[1]);
        return x / y;
    }

    public static double sum(List<Double> list) {
        double sum = 0;
        for (double value:list) {
            sum += value;
        }
        return sum;
    }

    public static int[] toArray(List<Integer> list) {
        int[] array = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            array[i] = list.get(i);
        }
        return array;
    }

    public static String stringMultiple(String element, int count) {
        StringBuilder sb = new StringBuilder(count * element.length());
        while (--count > 0) {
            sb.append(element);
        }
        return sb.toString();
    }

    public static String spaceMultiple(int count) {
        return stringMultiple(" ", count);
    }

    public static String extendCentering(String element, int length) {
        return stringMultiple(" ", (length - element.length()) / 2)
                + element + stringMultiple(" ", length - (length - element.length()) / 2 - element.length());
    }

    public static double max(double lhs, double rhs) {
        return Math.max(lhs, rhs);
    }

    public static int max(int lhs, int rhs) {
        return Math.max(lhs, rhs);
    }

    public static double min(double lhs, double rhs) {
        return Math.min(lhs, rhs);
    }

    public static int min(int lhs, int rhs) {
        return Math.min(lhs, rhs);
    }

    /**
     * The actual length of the string on console, stripped ANSI escape characters.
     * @param string the original string
     * @return the printed length
     */
    public static int stringActualLength(String string) {
        return string.replaceAll("\\e\\[[\\d;]*[^\\d;]","").length(); // remove ANSI escape characters
    }

    public static <T> int maxNumber(Iterable<T> list, Function<T, Integer> valueFunction) {
        int max = Integer.MIN_VALUE;
        for (T element: list) {
            max = max > valueFunction.apply(element) ? max : valueFunction.apply(element);
        }
        return max;
    }

    public static void updateIfGreater(List<Double> list, int index, double newValue) {
        if (index < list.size()) { // The if is devoted to keep the column widths, which is the max width of element in the column
            if (list.get(index) < newValue)
                list.set(index, newValue);
        } else {
            list.add(newValue);
        }
    }

    public static Point2D vectorToPoint(Vector2D vector2D) {
        return new Point2D(vector2D.getX(), vector2D.getY());
    }

    public static Color getColorByName(String name) {
        try {
            return (Color)Color.class.getField(name.toUpperCase()).get(null);
        } catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
            e.printStackTrace();
            return Color.BLACK;
        }
    }

    public static <T extends Enum> T getEnumerationByName(Class<T> enumerationClass, String name) {
        try {
            return (T) enumerationClass.getField(name.toUpperCase()).get(null);
        } catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
            e.printStackTrace();
            try {
                return (T) ((Object[]) enumerationClass.getMethod("values").invoke(null))[0];
            } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e1) {
                e1.printStackTrace();
                return null;
            }
        }
    }

    public static <T extends Enum> T getEnumerationByObject(Class<T> enumerationClass, Object object) {
        if (enumerationClass.isInstance(object)) {
            return (T) object;
        } else if (object instanceof String) {
            return getEnumerationByName(enumerationClass, (String) object);
        }
        return null;
    }

    public static <V, T> List<T> listOf(Iterable<V> elements, Function<V, T> foreach) {
        List<T> list = new ArrayList<>();
        for (V element: elements) {
            list.add(foreach.apply(element));
        }
        return list;
    }

    enum Number { ONE, TWO, THREE }

    class DummyClass {
        public String getClassName() {
            return this.getClass().getSimpleName();
        }
    }

    public static String firstLetterToLower(String string) {
        return Character.toLowerCase(string.charAt(0)) + string.substring(1);
    }

    public static <T> T replace(List<? extends  List<T>> array, int row, int column, T element) {
        T replaced = array.get(row).get(column);
        array.get(row).remove(column);
        array.get(row).add(column, element);
        return replaced;
    }

    public static <T> List<List<T>> copy(List<? extends List<T>> array) {
        List<List<T>> newArray = new ArrayList<>();
        for (List<T> row: array) {
            newArray.add(new ArrayList<>(row));
        }
        return newArray;
    }

    public static <T> List<List<T>> swapped(List<? extends List<T>> array, int row1, int column1, int row2, int column2) {
        List<List<T>> newArray = copy(array);
        T tmp = replace(newArray, row1, column1, newArray.get(row2).get(column2));
        replace(newArray, row2, column2, tmp);
        return newArray;
    }

    public static List<Double> weightedAverageOfDoubles(List<Double> lhs, List<Double> rhs, double weightOfLhs) {
        if (lhs.size() != rhs.size()) {
            WarningStream.putWarning("Average of lists of different sizes.", Util.class);
            return rhs;
        }
        List<Double> result = new ArrayList<>();
        for (int i = 0; i < lhs.size(); i++) {
            result.add(weightOfLhs * lhs.get(i) + (1 - weightOfLhs) * rhs.get(i));
        }
        return result;
    }

    public static List<PositionOffset> weightedAverageOfOffsets(List<PositionOffset> lhs, List<PositionOffset> rhs, double weightOfLhs) {
        if (lhs.size() != rhs.size()) {
            WarningStream.putWarning("Average of lists of different sizes.", Util.class);
            return rhs;
        }
        List<PositionOffset> result = new ArrayList<>();
        for (int i = 0; i < lhs.size(); i++) {
            result.add(new PositionOffset(
                    (int) (weightOfLhs * lhs.get(i).getIntX() + (1 - weightOfLhs) * rhs.get(i).getIntX()),
                    (int) (weightOfLhs * lhs.get(i).getIntY() + (1 - weightOfLhs) * rhs.get(i).getIntY())));
        }
        return result;
    }

    public static List<List<PositionOffset>> weightedAverageOfOffsetsArray(List<List<PositionOffset>> lhs, List<List<PositionOffset>> rhs, double weightOfLhs) {
        if (lhs.size() != rhs.size()) {
            WarningStream.putWarning("Average of lists of different sizes.", Util.class);
            return rhs;
        }
        List<List<PositionOffset>> result = new ArrayList<>();
        for (int i = 0; i < lhs.size(); i++)
            result.add(weightedAverageOfOffsets(lhs.get(i), rhs.get(i), weightOfLhs));
        return result;
    }

    @Nullable
    public static String[] groups(@RegEx String regex, String input) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        if (matcher.find()) {
            String[] result = new String[matcher.groupCount()];
            for (int i = 0; i < matcher.groupCount(); i++)
                result[i] = matcher.group(i + 1); // the wanted group index start from 1 and ends exactly at groupCount
            return result;
        } else {
            WarningStream.putWarning("Pattern not found.", Util.class);
            return null;
        }
    }

    public static BufferedImage toBufferedImage(Image img)
    {
        if (img instanceof BufferedImage)
        {
            return (BufferedImage) img;
        }

        // Create a buffered image with transparency
        BufferedImage bimage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);

        // Draw the image on to the buffered image
        Graphics2D bGr = bimage.createGraphics();
        bGr.drawImage(img, 0, 0, null);
        bGr.dispose();

        // Return the buffered image
        return bimage;
    }

    public static void main(String[] args) {
        Stack<Integer> si = new Stack<>();
        si.push(1);
        si.push(2);
        si.push(3);
        for (Integer i: si)
            System.out.println(i);
    }
}


