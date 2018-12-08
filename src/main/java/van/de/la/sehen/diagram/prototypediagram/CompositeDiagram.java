package van.de.la.sehen.diagram.prototypediagram;

import org.springframework.lang.Nullable;
import van.de.la.sehen.diagram.prototypediagram.model.CompositeDiagramModel;
import van.de.la.sehen.diagram.readerinterface.DiagramNode;
import van.de.la.sehen.diagram.readerinterface.fieldreading.DiagramFieldsCollection;
import van.de.la.sehen.diagramimage.PortableDiagramCanvas;
import van.de.la.sehen.diagramstyle.DiagramStyle;
import van.de.la.sehen.diagramtopology.DiagramChildIndex;
import van.de.la.sehen.warning.WarningStream;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public abstract class CompositeDiagram extends Diagram implements CompositeDiagramModel<Diagram> {
    public CompositeDiagram(Diagram parent, DiagramStyle style) {
        super(parent, style);
    }
    public CompositeDiagram(Diagram parent, DiagramFieldsCollection node) {
        super(parent, node);
    }
    public CompositeDiagram(Diagram parent, DiagramNode node) {
        super(parent, node);
    }

    // the elements in these set have their correspondent record on base topological index map

    private Map<Integer, Diagram> indexToDiagram = new HashMap<>();
    private Map<Diagram, Integer> diagramToIndex = new HashMap<>();

    public int indexBound() {
        return indexToDiagram.size();
    }

    public void removeChild(Diagram child) {
        int index = diagramToIndex.get(child);
        diagramToIndex.remove(child);
        indexToDiagram.remove(index);
        removeTopologicalChild(child);
    }

    public <T extends Diagram> T putChildToIndex(T child) {
        int index = indexBound();
        indexToDiagram.put(index, child);
        diagramToIndex.put(child, index);
        return putTopologicalChild(new DiagramChildIndex(index), child);
    }

    public int getIndexOfChild(Diagram child) {
        checkMembership(child);
        return diagramToIndex.get(child);
    }

    @Override
    @Nullable
    public Diagram getChildByIndex(int i) {
        if (!indexToDiagram.containsKey(i)) {
            WarningStream.putWarning("Querying a key not existed.", this);
            return null;
        }
        return indexToDiagram.get(i);
    }

    @Override
    public void paintDiagram(PortableDiagramCanvas canvas) {
        Diagram[] children = new Diagram[indexToDiagram.size()];
        for (Map.Entry<Integer, Diagram> entry: indexToDiagram.entrySet()) {
            children[entry.getKey()] = entry.getValue();
        }
        for (Diagram diagram: children) {
            diagram.paintDiagram(canvas);
        }
    }

    @Override
    public void layout() {
        CompositeDiagramModel.super.layout();
    }
}
