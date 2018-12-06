package van.de.la.sehen.diagram.readerinterface.diagrambuilder.instancebuilder.asciidiagrambuilder;

import van.de.la.sehen.diagram.prototypediagram.asciiprototypediagram.ASCIIDiagram;
import van.de.la.sehen.diagram.readerinterface.diagrambuilder.asciidiagrambuilder.ASCIIDelegatedDiagramBuilder;
import van.de.la.sehen.diagram.readerinterface.fieldreading.DiagramFieldsCollection;
import van.de.la.sehen.warning.WarningStream;

public abstract class ASCIIInstanceBuilder<ThisBuilderT extends ASCIIDelegatedDiagramBuilder, BuiltDiagramT extends ASCIIDiagram, AcceptElementT> extends ASCIIDelegatedDiagramBuilder<ThisBuilderT, BuiltDiagramT> {
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

    public abstract BuiltDiagramT buildDiagram(ASCIIDiagram parent, AcceptElementT elements, DiagramFieldsCollection fields);

    @Override
    public BuiltDiagramT buildDiagram(ASCIIDiagram parent) {
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
