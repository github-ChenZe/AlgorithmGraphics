package van.de.la.sehen.diagram.prototypediagram.model;

public interface TreeModel<T extends TreeModel<T>> {
    T getParent();

    default boolean isChildOf(TreeModel<?> diagram) {
        if (diagram == this) return true;
        if (getParent() == null || getParent() == this) {
            return false;
        }
        return getParent().isChildOf(diagram);
    }

    default boolean isAncestorOf(TreeModel<?> diagram) {
        return diagram.isChildOf(this);
    }
}
