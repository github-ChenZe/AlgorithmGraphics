package van.de.la.sehen.diagram.readerinterface.diagrambuilder.asciidiagrambuilder;

import van.de.la.sehen.diagram.prototypediagram.asciiprototypediagram.ASCIIDiagram;
import van.de.la.sehen.diagram.readerinterface.diagrambuilder.model.DelegatedDiagramBuilderModel;

import java.util.function.Consumer;

public abstract class ASCIIDelegatedDiagramBuilder<ThisT extends ASCIIDelegatedDiagramBuilder, U extends ASCIIDiagram> implements DelegatedDiagramBuilderModel<ThisT, U, ASCIIDiagram>, ASCIIDiagramBuilder<U> {
    private Consumer<ASCIIDiagram> afterBuilt;

    @Override
    public Consumer<ASCIIDiagram> getAfterBuilt() {
        return afterBuilt;
    }

    @Override
    public ThisT setAfterBuilt(Consumer<ASCIIDiagram> afterBuilt) {
        this.afterBuilt = afterBuilt;
        return (ThisT) this;
    }
}
