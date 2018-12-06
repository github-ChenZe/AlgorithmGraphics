package van.de.la.sehen.diagram.readerinterface.diagrambuilder.model;

import van.de.la.sehen.diagramattributeparticle.diagramparticle.model.DiagramPointerModel;

public interface DiagramBuilderModel<GenerateT extends ParentT, ParentT> extends DiagramPointerModel<GenerateT, ParentT> {
    GenerateT buildDiagram(ParentT parent);
}
