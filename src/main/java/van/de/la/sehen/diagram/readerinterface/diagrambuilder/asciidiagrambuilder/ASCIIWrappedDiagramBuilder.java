package van.de.la.sehen.diagram.readerinterface.diagrambuilder.asciidiagrambuilder;

import van.de.la.sehen.diagram.prototypediagram.asciiprototypediagram.ASCIIDiagram;
import van.de.la.sehen.diagram.readerinterface.asciireaderinterface.ASCIIDiagramNode;
import van.de.la.sehen.diagram.readerinterface.diagrambuilder.model.GeneralWrappedDiagramBuilder;

import java.util.function.Consumer;

public class ASCIIWrappedDiagramBuilder extends GeneralWrappedDiagramBuilder<ASCIIWrappedDiagramBuilder, ASCIIDiagram, ASCIIDiagramNode<?, ?>> implements ASCIIDiagramBuilder<ASCIIDiagram> {
    public ASCIIWrappedDiagramBuilder(ASCIIDiagramNode<?, ?> primitiveBuilder) {
        super(primitiveBuilder);
    }

    private Consumer<ASCIIDiagram> afterBuilt;

    @Override
    public Consumer<ASCIIDiagram> getAfterBuilt() {
        return afterBuilt;
    }

    @Override
    public ASCIIWrappedDiagramBuilder setAfterBuilt(Consumer<ASCIIDiagram> afterBuilt) {
        this.afterBuilt = afterBuilt;
        return this;
    }
}
