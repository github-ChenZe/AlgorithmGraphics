package van.de.la.sehen.diagram.readerinterface.asciireaderinterface;

import van.de.la.sehen.diagram.prototypediagram.asciiprototypediagram.ASCIIDiagram;
import van.de.la.sehen.diagram.readerinterface.diagrambuilder.asciidiagrambuilder.ASCIIDiagramBuilder;
import van.de.la.sehen.diagram.readerinterface.elementreading.DiagramElementsCollection;
import van.de.la.sehen.diagram.readerinterface.fieldreading.DiagramFieldsCollection;

public interface ASCIIDiagramNode<T, U extends ASCIIDiagram> extends DiagramElementsCollection<T>, DiagramFieldsCollection, ASCIIDiagramBuilder<U> {

}

