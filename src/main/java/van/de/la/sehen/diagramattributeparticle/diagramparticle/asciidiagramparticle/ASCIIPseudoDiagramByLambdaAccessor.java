package van.de.la.sehen.diagramattributeparticle.diagramparticle.asciidiagramparticle;

import van.de.la.sehen.diagram.prototypediagram.asciiprototypediagram.ASCIIDiagram;
import van.de.la.sehen.diagram.pseudodiagram.ASCIIPseudoDiagram;
import van.de.la.sehen.diagram.readerinterface.attributereading.DiagramAttributeParticle;

import java.util.function.Supplier;

public class ASCIIPseudoDiagramByLambdaAccessor implements DiagramAttributeParticle<ASCIIPseudoDiagram> {
    private String pseudoName;
    private Supplier<ASCIIDiagram> parent;

    public ASCIIPseudoDiagramByLambdaAccessor(Supplier<ASCIIDiagram> parent, String name) {
        pseudoName = name;
        this.parent = parent;
    }

    public ASCIIPseudoDiagram getPseudoDiagram() {
        return parent.get().getPseudo(pseudoName);
    }

    @Override
    public ASCIIPseudoDiagram get() {
        return getPseudoDiagram();
    }
}
