package van.de.la.sehen.diagramattributeparticle.diagramparticle;

import van.de.la.sehen.diagram.prototypediagram.Diagram;

public class PseudoDiagramByPointerAccessor extends PseudoDiagramByLambdaAccessor {

    public PseudoDiagramByPointerAccessor(DiagramPointer<Diagram> parent, String name) { super(parent::dereference, name); }
}
