package van.de.la.sehen.diagramattributeparticle.diagramparticle;

import van.de.la.sehen.diagram.prototypediagram.Diagram;

import java.util.function.Function;

public class PseudoDiagramByIdAccessor extends PseudoDiagramByLambdaAccessor {
    public PseudoDiagramByIdAccessor(Function<String, Diagram> diagramFinder, String id, String name) {
        super(() -> diagramFinder.apply(id), name);
    }
}
