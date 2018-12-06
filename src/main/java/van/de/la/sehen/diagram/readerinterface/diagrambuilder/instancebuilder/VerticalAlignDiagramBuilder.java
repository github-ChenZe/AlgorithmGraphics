package van.de.la.sehen.diagram.readerinterface.diagrambuilder.instancebuilder;

import van.de.la.sehen.diagram.displayeddiagram.VerticalAlignDiagram;
import van.de.la.sehen.diagram.prototypediagram.Diagram;
import van.de.la.sehen.diagram.readerinterface.diagrambuilder.DiagramBuilder;
import van.de.la.sehen.diagram.readerinterface.elementreading.DiagramElementsCollection;
import van.de.la.sehen.diagram.readerinterface.fieldreading.DiagramFieldsCollection;

public class VerticalAlignDiagramBuilder<T extends DiagramBuilder> extends InstanceBuilder<VerticalAlignDiagramBuilder<T>, VerticalAlignDiagram, DiagramElementsCollection<T>> {
    @Override
    public VerticalAlignDiagram buildDiagram(Diagram parent, DiagramElementsCollection<T> elements, DiagramFieldsCollection fields) {
        return new VerticalAlignDiagram(parent, elements, fields);
    }
}
