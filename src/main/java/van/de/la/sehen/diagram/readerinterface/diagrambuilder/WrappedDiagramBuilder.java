package van.de.la.sehen.diagram.readerinterface.diagrambuilder;

import van.de.la.sehen.diagram.prototypediagram.Diagram;
import van.de.la.sehen.diagram.readerinterface.DiagramNode;
import van.de.la.sehen.diagram.readerinterface.diagrambuilder.model.GeneralWrappedDiagramBuilder;
import van.de.la.sehen.warning.WarningStream;

import java.util.function.Consumer;

public class WrappedDiagramBuilder extends GeneralWrappedDiagramBuilder<WrappedDiagramBuilder, Diagram, DiagramNode<?, ?>> implements DiagramBuilder<Diagram> {
    public WrappedDiagramBuilder(DiagramNode<?, ?> primitiveBuilder) {
        super(primitiveBuilder);
    }

    private Consumer<Diagram> afterBuilt;

    @Override
    public Consumer<Diagram> getAfterBuilt() {
        return afterBuilt;
    }

    @Override
    public WrappedDiagramBuilder setAfterBuilt(Consumer<Diagram> afterBuilt) {
        if (this.afterBuilt != null) {
            WarningStream.putWarning("Resetting after built. The new one will override the old one", this);
        }
        this.afterBuilt = afterBuilt;
        return this;
    }
}
