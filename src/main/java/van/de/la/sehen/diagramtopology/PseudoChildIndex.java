package van.de.la.sehen.diagramtopology;

import java.util.Objects;

public class PseudoChildIndex implements ChildIndex {
    private String name;

    public PseudoChildIndex(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PseudoChildIndex that = (PseudoChildIndex) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
