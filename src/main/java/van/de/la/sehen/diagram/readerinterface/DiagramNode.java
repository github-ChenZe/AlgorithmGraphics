package van.de.la.sehen.diagram.readerinterface;

import van.de.la.sehen.diagram.prototypediagram.Diagram;
import van.de.la.sehen.diagram.readerinterface.diagrambuilder.DiagramBuilder;
import van.de.la.sehen.diagram.readerinterface.elementreading.DiagramElementsCollection;
import van.de.la.sehen.diagram.readerinterface.fieldreading.DiagramFieldsCollection;

public interface DiagramNode<T, U extends Diagram> extends DiagramElementsCollection<T>, DiagramFieldsCollection, DiagramBuilder<U> {

}

