package van.de.la.sehen.diagram.readerinterface.diagrambuilder.model;

import java.util.function.Consumer;

public interface DelegatedDiagramBuilderModel<ThisT extends DelegatedDiagramBuilderModel, GenerateT extends ParentT, ParentT> extends DiagramBuilderModel<GenerateT, ParentT> {
    Consumer<ParentT> getAfterBuilt();
    ThisT setAfterBuilt(Consumer<ParentT> afterBuilt);
}
