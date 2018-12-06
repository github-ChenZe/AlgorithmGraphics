package van.de.la.sehen.diagram.readerinterface.diagrambuilder.instancebuilder;

import van.de.la.sehen.diagram.prototypediagram.Diagram;
import van.de.la.sehen.diagram.readerinterface.diagrambuilder.DelegatedDiagramBuilder;
import van.de.la.sehen.diagram.readerinterface.fieldreading.DiagramFieldsCollection;
import van.de.la.sehen.warning.WarningStream;

public abstract class InstanceBuilder<ThisBuilderT extends DelegatedDiagramBuilder, BuiltDiagramT extends Diagram, AcceptElementT> extends DelegatedDiagramBuilder<ThisBuilderT, BuiltDiagramT> {
    private BuiltDiagramT diagramBuilt;

    public ThisBuilderT setElements(AcceptElementT elements) {
        this.elements = elements;
        return (ThisBuilderT) this;
    }

    public ThisBuilderT setFields(DiagramFieldsCollection fields) {
        this.fields = fields;
        return (ThisBuilderT)this;
    }

    private AcceptElementT elements;
    private DiagramFieldsCollection fields;

    public abstract BuiltDiagramT buildDiagram(Diagram parent, AcceptElementT elements, DiagramFieldsCollection fields);

    @Override
    public BuiltDiagramT buildDiagram(Diagram parent) {
        diagramBuilt = buildDiagram(parent, elements, fields);
        if (getAfterBuilt() != null) getAfterBuilt().accept(diagramBuilt);
        return diagramBuilt;
    }

    @Override
    public BuiltDiagramT dereference() {
        if (diagramBuilt == null) WarningStream.putWarning("Dereferencing from unbuilt diagram.", this);
        return diagramBuilt;
    }
}
