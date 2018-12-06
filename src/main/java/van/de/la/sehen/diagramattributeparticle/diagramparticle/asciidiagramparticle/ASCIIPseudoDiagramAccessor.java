package van.de.la.sehen.diagramattributeparticle.diagramparticle.asciidiagramparticle;

import van.de.la.sehen.diagram.prototypediagram.asciiprototypediagram.ASCIIDiagram;

public class ASCIIPseudoDiagramAccessor extends ASCIIPseudoDiagramByLambdaAccessor {
    public ASCIIPseudoDiagramAccessor(ASCIIDiagram parent, String name) {
        super(() -> parent, name);
    }
}