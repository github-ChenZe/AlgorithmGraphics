package van.de.la.sehen.diagram.readerinterface.diagrambuilder.instancebuilder.asciidiagrambuilder;

import van.de.la.sehen.diagram.displayeddiagram.asciidisplayeddiagram.ASCIIArrowDiagram;
import van.de.la.sehen.diagram.prototypediagram.asciiprototypediagram.ASCIIDiagram;
import van.de.la.sehen.diagram.readerinterface.diagrambuilder.asciidiagrambuilder.ASCIIDiagramBuilder;
import van.de.la.sehen.diagram.readerinterface.elementreading.DiagramElementsCollection;
import van.de.la.sehen.diagram.readerinterface.fieldreading.DiagramFieldsCollection;

public class ASCIIArrowDiagramBuilder<T extends ASCIIDiagramBuilder> extends ASCIIInstanceBuilder<ASCIIArrowDiagramBuilder<T>, ASCIIArrowDiagram, DiagramElementsCollection> {
    @Override
    public ASCIIArrowDiagram buildDiagram(ASCIIDiagram parent, DiagramElementsCollection elements, DiagramFieldsCollection fields) {
        return new ASCIIArrowDiagram(parent, fields);
    }
}
