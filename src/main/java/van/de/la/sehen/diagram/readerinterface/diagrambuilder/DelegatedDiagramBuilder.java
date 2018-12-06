package van.de.la.sehen.diagram.readerinterface.diagrambuilder;

import van.de.la.sehen.diagram.prototypediagram.Diagram;
import van.de.la.sehen.diagram.readerinterface.diagrambuilder.model.DelegatedDiagramBuilderModel;

import java.util.function.Consumer;

public abstract class DelegatedDiagramBuilder<ThisT extends DelegatedDiagramBuilder, U extends Diagram> implements DelegatedDiagramBuilderModel<ThisT, U, Diagram>, DiagramBuilder<U> {
    private Consumer<Diagram> afterBuilt;

    @Override
    public Consumer<Diagram> getAfterBuilt() {
        return afterBuilt;
    }

    @Override
    public ThisT setAfterBuilt(Consumer<Diagram> afterBuilt) {
        this.afterBuilt = afterBuilt;
        return (ThisT) this;
    }
}
