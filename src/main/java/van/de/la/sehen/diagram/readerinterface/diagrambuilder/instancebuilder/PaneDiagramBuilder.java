package van.de.la.sehen.diagram.readerinterface.diagrambuilder.instancebuilder;

import van.de.la.sehen.diagram.displayeddiagram.PaneDiagram;
import van.de.la.sehen.diagram.prototypediagram.Diagram;
import van.de.la.sehen.diagram.readerinterface.diagrambuilder.DiagramBuilder;
import van.de.la.sehen.diagram.readerinterface.elementreading.DiagramElementsCollection;
import van.de.la.sehen.diagram.readerinterface.fieldreading.DiagramFieldsCollection;

public class PaneDiagramBuilder<T extends DiagramBuilder> extends InstanceBuilder<PaneDiagramBuilder<T>, PaneDiagram, DiagramElementsCollection<T>> {
    @Override
    public PaneDiagram buildDiagram(Diagram parent, DiagramElementsCollection<T> elements, DiagramFieldsCollection fields) {
        return new PaneDiagram(parent, elements, fields);
    }
}
