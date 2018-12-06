package van.de.la.sehen.diagram.readerinterface.diagrambuilder.instancebuilder;

import van.de.la.sehen.diagram.displayeddiagram.TableDiagram;
import van.de.la.sehen.diagram.prototypediagram.Diagram;
import van.de.la.sehen.diagram.readerinterface.diagrambuilder.DiagramBuilder;
import van.de.la.sehen.diagram.readerinterface.elementreading.DiagramElementsCollection;
import van.de.la.sehen.diagram.readerinterface.fieldreading.DiagramFieldsCollection;

public class TableDiagramBuilder<T extends DiagramBuilder> extends InstanceBuilder<TableDiagramBuilder<T>, TableDiagram, DiagramElementsCollection<DiagramElementsCollection<T>>> {
    @Override
    public TableDiagram buildDiagram(Diagram parent, DiagramElementsCollection<DiagramElementsCollection<T>> elements, DiagramFieldsCollection fields) {
        return new TableDiagram(parent, elements, fields);
    }
}
