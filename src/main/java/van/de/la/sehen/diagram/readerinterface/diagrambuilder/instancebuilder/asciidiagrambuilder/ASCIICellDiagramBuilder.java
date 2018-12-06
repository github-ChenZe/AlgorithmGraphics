package van.de.la.sehen.diagram.readerinterface.diagrambuilder.instancebuilder.asciidiagrambuilder;

import van.de.la.sehen.diagram.displayeddiagram.asciidisplayeddiagram.ASCIICellDiagram;
import van.de.la.sehen.diagram.prototypediagram.asciiprototypediagram.ASCIIDiagram;
import van.de.la.sehen.diagram.readerinterface.diagrambuilder.asciidiagrambuilder.ASCIIDiagramBuilder;
import van.de.la.sehen.diagram.readerinterface.elementreading.DiagramElementsCollection;
import van.de.la.sehen.diagram.readerinterface.fieldreading.DiagramFieldsCollection;

public class ASCIICellDiagramBuilder<T extends ASCIIDiagramBuilder> extends ASCIIInstanceBuilder<ASCIICellDiagramBuilder<T>, ASCIICellDiagram, DiagramElementsCollection<T>> {
    @Override
    public ASCIICellDiagram buildDiagram(ASCIIDiagram parent, DiagramElementsCollection<T> elements, DiagramFieldsCollection fields) {
        return new ASCIICellDiagram(parent, elements, fields);
    }
}
