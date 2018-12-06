package van.de.la.sehen.diagram.prototypediagram.model;

import van.de.la.sehen.diagram.readerinterface.attributereading.DiagramAttributeCluster;

public interface DiagramModel<ThisT extends TreeModel<ParentT>, ParentT extends TreeModel<ParentT> & StyleModel<ParentT>> extends TreeModel<ParentT>, StyleModel<ParentT> {
}
