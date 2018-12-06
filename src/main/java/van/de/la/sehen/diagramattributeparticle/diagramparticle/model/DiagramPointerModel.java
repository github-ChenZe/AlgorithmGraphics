package van.de.la.sehen.diagramattributeparticle.diagramparticle.model;

import van.de.la.sehen.diagram.readerinterface.attributereading.DiagramAttributeParticle;

public interface DiagramPointerModel<T extends ParentT, ParentT> extends DiagramAttributeParticle<ParentT> {
    T dereference();

    default ParentT get() {
        return dereference();
    }
}
