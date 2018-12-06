package van.de.la.sehen.diagram.readerinterface.elementreading;

import java.util.LinkedList;
import java.util.Queue;

public class DiagramElementCollectionBuilder<T> implements DiagramElementsCollection<T> {
    public DiagramElementCollectionBuilder() {
        children = new LinkedList<>();
    }

    private String content;

    @Override
    public String getContent() {
        return content;
    }

    public DiagramElementCollectionBuilder setContent(String content) {
        this.content = content;
        return this;
    }

    private Queue<T> children;

    @Override
    public Queue<T> getChildren() {
        return children;
    }

    public DiagramElementCollectionBuilder<T> addChild(T child) {
        children.add(child);
        return this;
    }

    @Override
    public boolean hasChildren() {
        return children.size() > 0;
    }
}
