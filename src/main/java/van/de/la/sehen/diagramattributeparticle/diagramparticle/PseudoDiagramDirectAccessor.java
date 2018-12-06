package van.de.la.sehen.diagramattributeparticle.diagramparticle;

import van.de.la.sehen.diagram.pseudodiagram.PseudoDiagram;
import van.de.la.sehen.diagram.readerinterface.attributereading.DiagramAttributeParticle;

public class PseudoDiagramDirectAccessor implements DiagramAttributeParticle<PseudoDiagram> {
    private PseudoDiagram pseudoDiagram;

    public PseudoDiagramDirectAccessor(PseudoDiagram pseudoDiagram) {
        this.pseudoDiagram = pseudoDiagram;
    }

    public PseudoDiagram getPseudoDiagram() {
        return pseudoDiagram;
    }

    @Override
    public PseudoDiagram get() {
        return getPseudoDiagram();
    }
}