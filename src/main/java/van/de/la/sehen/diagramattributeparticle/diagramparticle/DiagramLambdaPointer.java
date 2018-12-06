package van.de.la.sehen.diagramattributeparticle.diagramparticle;

import van.de.la.sehen.diagram.prototypediagram.Diagram;

import java.util.function.Supplier;

public class DiagramLambdaPointer<T extends Diagram> implements DiagramPointer<T> {
    private Supplier<T> supplier;

    public DiagramLambdaPointer(Supplier<T> supplier) {
        this.supplier = supplier;
    }

    @Override
    public T dereference() {
        return supplier.get();
    }
}
