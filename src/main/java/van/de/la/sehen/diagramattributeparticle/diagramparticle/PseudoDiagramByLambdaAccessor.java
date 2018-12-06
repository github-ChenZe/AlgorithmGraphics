package van.de.la.sehen.diagramattributeparticle.diagramparticle;

import van.de.la.sehen.diagram.prototypediagram.Diagram;
import van.de.la.sehen.diagram.pseudodiagram.PseudoDiagram;
import van.de.la.sehen.diagram.readerinterface.attributereading.DiagramAttributeParticle;

import java.util.function.Supplier;

public class PseudoDiagramByLambdaAccessor implements DiagramAttributeParticle<PseudoDiagram> {
    private String pseudoName;
    private Supplier<Diagram> parent;

    public PseudoDiagramByLambdaAccessor(Supplier<Diagram> parent, String name) {
        pseudoName = name;
        this.parent = parent;
    }

    public PseudoDiagram getPseudoDiagram() {
        return parent.get().getPseudo(pseudoName);
    }

    @Override
    public PseudoDiagram get() {
        return getPseudoDiagram();
    }
}
