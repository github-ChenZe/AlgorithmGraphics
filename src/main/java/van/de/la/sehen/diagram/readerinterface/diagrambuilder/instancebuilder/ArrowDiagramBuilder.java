package van.de.la.sehen.diagram.readerinterface.diagrambuilder.instancebuilder;

import van.de.la.sehen.diagram.displayeddiagram.ArrowDiagram;
import van.de.la.sehen.diagram.prototypediagram.Diagram;
import van.de.la.sehen.diagram.readerinterface.diagrambuilder.DiagramBuilder;
import van.de.la.sehen.diagram.readerinterface.elementreading.DiagramElementsCollection;
import van.de.la.sehen.diagram.readerinterface.fieldreading.DiagramFieldsCollection;

public class ArrowDiagramBuilder<T extends DiagramBuilder> extends InstanceBuilder<ArrowDiagramBuilder<T>, ArrowDiagram, DiagramElementsCollection> {
    @Override
    public ArrowDiagram buildDiagram(Diagram parent, DiagramElementsCollection elements, DiagramFieldsCollection fields) {
        return new ArrowDiagram(parent, fields);
    }
}
