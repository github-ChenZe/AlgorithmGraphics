package van.de.la.sehen.diagramtopology;

import java.util.Objects;

public class FreeChildIndex implements ChildIndex {
    double xRation;
    double yRation;

    public FreeChildIndex(double xRation, double yRation) {
        this.xRation = xRation;
        this.yRation = yRation;
    }

    public double getXRation() {
        return xRation;
    }

    public double getYRation() {
        return yRation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FreeChildIndex that = (FreeChildIndex) o;
        return Double.compare(that.xRation, xRation) == 0 &&
                Double.compare(that.yRation, yRation) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(xRation, yRation);
    }
}
