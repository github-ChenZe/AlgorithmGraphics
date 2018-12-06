package van.de.la.sehen.diagram.readerinterface.diagrambuilder.instancebuilder.asciidiagrambuilder;

import van.de.la.sehen.diagram.displayeddiagram.asciidisplayeddiagram.ASCIIPaneDiagram;
import van.de.la.sehen.diagram.prototypediagram.asciiprototypediagram.ASCIIDiagram;
import van.de.la.sehen.diagram.readerinterface.diagrambuilder.asciidiagrambuilder.ASCIIDiagramBuilder;
import van.de.la.sehen.diagram.readerinterface.elementreading.DiagramElementsCollection;
import van.de.la.sehen.diagram.readerinterface.fieldreading.DiagramFieldsCollection;

public class ASCIIPaneDiagramBuilder<T extends ASCIIDiagramBuilder> extends ASCIIInstanceBuilder<ASCIIPaneDiagramBuilder<T>, ASCIIPaneDiagram, DiagramElementsCollection<T>> {
    @Override
    public ASCIIPaneDiagram buildDiagram(ASCIIDiagram parent, DiagramElementsCollection<T> elements, DiagramFieldsCollection fields) {
        return new ASCIIPaneDiagram(parent, elements, fields);
    }
}
