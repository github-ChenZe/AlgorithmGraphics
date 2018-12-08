package van.de.la.sehen.diagramtopology;

import java.util.Objects;

public class RootIndex implements ChildIndex {
    private Object root;

    public RootIndex(Object root) {
        this.root = root;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RootIndex rootIndex = (RootIndex) o;
        return Objects.equals(root, rootIndex.root);
    }

    @Override
    public int hashCode() {
        return Objects.hash(root);
    }
}
