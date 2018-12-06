package van.de.la.sehen.diagramattributeparticle.diagramparticle.asciidiagramparticle;

import van.de.la.sehen.diagram.prototypediagram.asciiprototypediagram.ASCIIDiagram;

import java.util.function.Supplier;

public class ASCIIDiagramLambdaPointer<T extends ASCIIDiagram> implements ASCIIDiagramPointer<T> {
    private Supplier<T> supplier;

    public ASCIIDiagramLambdaPointer(Supplier<T> supplier) {
        this.supplier = supplier;
    }

    @Override
    public T dereference() {
        return supplier.get();
    }
}
