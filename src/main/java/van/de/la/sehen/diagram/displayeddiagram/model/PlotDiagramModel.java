package van.de.la.sehen.diagram.displayeddiagram.model;

import van.de.la.sehen.mathematics.*;

public interface PlotDiagramModel {
    String getFunctionVariableName();
    MathTree getMathTree();
    double getVarStart();
    double getVarEnd();
    double getYStart();
    double getYEnd();
    int screenWidth();
    int screenHeight();
    default int[] plotPixels(MathContext context) {
        int[] ys = new int[screenWidth()];
        for (int i = 0; i < screenWidth(); i++) {
            double value = getMathTree().evaluateToDouble(
                    context.put(getFunctionVariableName(), (i * getVarEnd() + (screenWidth() - 1 - i) * getVarStart()) / (screenWidth() - 1)));
            if (value > getYEnd()) {
                ys[i] = -1;
            } else if (value < getYStart()) {
                ys[i] = screenHeight();
            } else {
                // the screen Y is inverted
                ys[i] = (int) ((getYEnd() - value) * screenHeight() / (getYEnd() - getYStart()));
            }
        }
        for (int i = 1; i < ys.length - 1; i++) { // fix exceeded value
            if ((ys[i] < 0) && (ys[i + 1] >= 0 && ys[i + 1] < screenHeight())) { // this abnormal && next normal
                ys[i] = 0;
            } else if ((ys[i] >= screenHeight()) && (ys[i + 1] >= 0 && ys[i + 1] < screenHeight())) {
                ys[i] = screenHeight() - 1;
            } else if ((ys[i] >= 0 && ys[i] < screenHeight()) && ys[i + 1] < 0) { // this normal && next abnormal
                ys[i + 1] = 0;
            } else if ((ys[i] >= 0 && ys[i] < screenHeight()) && ys[i + 1] >= screenHeight()) {
                ys[i + 1] = screenHeight() - 1;
            }
        }
        return ys;
    }

    default int[] plotPixels() {
        return plotPixels(new MathContext());
    }
}
