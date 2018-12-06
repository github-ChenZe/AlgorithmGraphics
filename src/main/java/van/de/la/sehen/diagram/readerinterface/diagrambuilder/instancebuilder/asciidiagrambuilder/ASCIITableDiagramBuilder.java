package van.de.la.sehen.diagram.readerinterface.diagrambuilder.instancebuilder.asciidiagrambuilder;

import van.de.la.sehen.diagram.displayeddiagram.asciidisplayeddiagram.ASCIITableDiagram;
import van.de.la.sehen.diagram.prototypediagram.asciiprototypediagram.ASCIIDiagram;
import van.de.la.sehen.diagram.readerinterface.diagrambuilder.asciidiagrambuilder.ASCIIDiagramBuilder;
import van.de.la.sehen.diagram.readerinterface.elementreading.DiagramElementsCollection;
import van.de.la.sehen.diagram.readerinterface.fieldreading.DiagramFieldsCollection;

public class ASCIITableDiagramBuilder<T extends ASCIIDiagramBuilder> extends ASCIIInstanceBuilder<ASCIITableDiagramBuilder<T>, ASCIITableDiagram, DiagramElementsCollection<DiagramElementsCollection<T>>> {
    @Override
    public ASCIITableDiagram buildDiagram(ASCIIDiagram parent, DiagramElementsCollection<DiagramElementsCollection<T>> elements, DiagramFieldsCollection fields) {
        return new ASCIITableDiagram(parent, elements, fields);
    }
}
