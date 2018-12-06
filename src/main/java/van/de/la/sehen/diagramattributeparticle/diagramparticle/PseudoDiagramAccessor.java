package van.de.la.sehen.diagramattributeparticle.diagramparticle;

import van.de.la.sehen.diagram.prototypediagram.Diagram;

public class PseudoDiagramAccessor extends PseudoDiagramByLambdaAccessor {
    public PseudoDiagramAccessor(Diagram parent, String name) {
        super(() -> parent, name);
    }
}