package van.de.la.sehen.diagram.readerinterface.elementreading;

import java.util.Queue;

public interface DiagramElementsCollection<T> {
    String getContent();
    Queue<T> getChildren();
    boolean hasChildren();

    default int childrenCount() {
        return getChildren().size();
    }


    default <S extends T> DiagramElementsCollection<T> addFrom(Queue<S> collection) {
        for (S child: collection) {
            getChildren().add(child);
        }
        return this;
    }
}
