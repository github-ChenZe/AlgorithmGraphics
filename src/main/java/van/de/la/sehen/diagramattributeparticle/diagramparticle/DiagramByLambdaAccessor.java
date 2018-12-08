package van.de.la.sehen.diagramattributeparticle.diagramparticle;

import van.de.la.sehen.diagram.prototypediagram.Diagram;
import van.de.la.sehen.diagram.pseudodiagram.PseudoDiagram;
import van.de.la.sehen.diagram.readerinterface.attributereading.DiagramAttributeParticle;

import java.util.function.Supplier;

public class DiagramByLambdaAccessor implements DiagramAttributeParticle<Diagram> {
    private Supplier<Diagram> supplier;

    public DiagramByLambdaAccessor(Supplier<Diagram> supplier) {
        this.supplier = supplier;
    }

    public Diagram getDiagram() {
        return supplier.get();
    }

    @Override
    public Diagram get() {
        return getDiagram();
    }
}
