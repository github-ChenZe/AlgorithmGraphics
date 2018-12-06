package van.de.la.sehen.diagram.readerinterface.diagrambuilder.instancebuilder.asciidiagrambuilder;

import van.de.la.sehen.diagram.displayeddiagram.asciidisplayeddiagram.ASCIIVerticalAlignDiagram;
import van.de.la.sehen.diagram.prototypediagram.asciiprototypediagram.ASCIIDiagram;
import van.de.la.sehen.diagram.readerinterface.diagrambuilder.asciidiagrambuilder.ASCIIDiagramBuilder;
import van.de.la.sehen.diagram.readerinterface.elementreading.DiagramElementsCollection;
import van.de.la.sehen.diagram.readerinterface.fieldreading.DiagramFieldsCollection;

public class ASCIIVerticalAlignDiagramBuilder<T extends ASCIIDiagramBuilder> extends ASCIIInstanceBuilder<ASCIIVerticalAlignDiagramBuilder<T>, ASCIIVerticalAlignDiagram, DiagramElementsCollection<T>> {
    @Override
    public ASCIIVerticalAlignDiagram buildDiagram(ASCIIDiagram parent, DiagramElementsCollection<T> elements, DiagramFieldsCollection fields) {
        return new ASCIIVerticalAlignDiagram(parent, elements, fields);
    }
}
