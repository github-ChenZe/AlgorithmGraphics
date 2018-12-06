package van.de.la.sehen.diagram.readerinterface.diagrambuilder.instancebuilder;

import van.de.la.sehen.diagram.displayeddiagram.CellDiagram;
import van.de.la.sehen.diagram.prototypediagram.Diagram;
import van.de.la.sehen.diagram.readerinterface.diagrambuilder.DiagramBuilder;
import van.de.la.sehen.diagram.readerinterface.elementreading.DiagramElementsCollection;
import van.de.la.sehen.diagram.readerinterface.fieldreading.DiagramFieldsCollection;

public class CellDiagramBuilder<T extends DiagramBuilder> extends InstanceBuilder<CellDiagramBuilder<T>, CellDiagram, DiagramElementsCollection<T>> {
    @Override
    public CellDiagram buildDiagram(Diagram parent, DiagramElementsCollection<T> elements, DiagramFieldsCollection fields) {
        return new CellDiagram(parent, elements, fields);
    }
}
