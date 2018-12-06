package van.de.la.sehen.diagramattributeparticle;

import van.de.la.sehen.diagram.prototypediagram.AbstractDiagram;
import van.de.la.sehen.diagram.prototypediagram.Diagram;
import van.de.la.sehen.diagramattributeparticle.model.GeneralStyleInheritWrapper;

import java.util.function.Supplier;

public class StyleInheritWrapper extends GeneralStyleInheritWrapper<Diagram, AbstractDiagram> {
    public StyleInheritWrapper(Supplier<Diagram> inheritFrom, String fieldName) {
        super(inheritFrom, fieldName);
    }
}
