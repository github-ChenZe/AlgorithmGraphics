package van.de.la.sehen.diagramattributeparticle.diagramparticle.asciidiagramparticle;

import van.de.la.sehen.diagram.pseudodiagram.ASCIIPseudoDiagram;
import van.de.la.sehen.diagram.readerinterface.attributereading.DiagramAttributeParticle;

public class ASCIIPseudoDiagramDirectAccessor implements DiagramAttributeParticle<ASCIIPseudoDiagram> {
    private ASCIIPseudoDiagram pseudoDiagram;

    public ASCIIPseudoDiagramDirectAccessor(ASCIIPseudoDiagram pseudoDiagram) {
        this.pseudoDiagram = pseudoDiagram;
    }

    public ASCIIPseudoDiagram getPseudoDiagram() {
        return pseudoDiagram;
    }

    @Override
    public ASCIIPseudoDiagram get() {
        return getPseudoDiagram();
    }
}