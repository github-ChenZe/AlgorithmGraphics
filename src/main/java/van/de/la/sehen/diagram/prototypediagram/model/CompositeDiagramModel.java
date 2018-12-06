package van.de.la.sehen.diagram.prototypediagram.model;

import van.de.la.sehen.warning.WarningStream;

public interface CompositeDiagramModel<ChildT extends TreeModel<?>> extends LayoutModel {
    void layoutChildren();
    void setChildrenPosition();

    default void layout() {
        layoutChildren();
        calculateSize();
        setChildrenPosition(); // setChildrenPosition must be after calculate size since the position of children may
        // depend on the size of box, e.g. when children are center-aligned.
    }

    default void checkMembership(ChildT expectToBeChild) {
        if (expectToBeChild.getParent() != this) {
            WarningStream.putWarning("Got a child " + expectToBeChild + " not belonging to this van.de.la.sehen.diagram.", this);
        }
    }

    ChildT getChildByIndex(int i);
}
