package van.de.la.sehen.diagramtopology;

import java.util.Objects;

public class DiagramChildIndex implements ChildIndex {
    private int index;

    public DiagramChildIndex(int index) {
        this.index = index;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DiagramChildIndex that = (DiagramChildIndex) o;
        return index == that.index;
    }

    @Override
    public int hashCode() {
        return Objects.hash(index);
    }
}
