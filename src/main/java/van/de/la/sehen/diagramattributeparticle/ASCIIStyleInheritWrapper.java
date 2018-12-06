package van.de.la.sehen.diagramattributeparticle;

import van.de.la.sehen.diagram.prototypediagram.asciiprototypediagram.ASCIIAbstractDiagram;
import van.de.la.sehen.diagram.prototypediagram.asciiprototypediagram.ASCIIDiagram;
import van.de.la.sehen.diagramattributeparticle.model.GeneralStyleInheritWrapper;

import java.util.function.Supplier;

public class ASCIIStyleInheritWrapper extends GeneralStyleInheritWrapper<ASCIIDiagram, ASCIIAbstractDiagram> {
    public ASCIIStyleInheritWrapper(Supplier<ASCIIDiagram> inheritFrom, String fieldName) {
        super(inheritFrom, fieldName);
    }
}
