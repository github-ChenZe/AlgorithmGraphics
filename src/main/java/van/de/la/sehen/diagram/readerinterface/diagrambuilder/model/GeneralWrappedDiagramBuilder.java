package van.de.la.sehen.diagram.readerinterface.diagrambuilder.model;

import van.de.la.sehen.diagram.readerinterface.attributereading.DiagramAttributesCollection;
import van.de.la.sehen.diagram.readerinterface.elementreading.DiagramElementsCollection;
import van.de.la.sehen.diagram.readerinterface.fieldreading.DiagramFieldsCollection;
import van.de.la.sehen.warning.WarningStream;

public abstract class GeneralWrappedDiagramBuilder<ThisT extends GeneralWrappedDiagramBuilder<ThisT, DiagramT, DiagramNodeT>, DiagramT, DiagramNodeT extends DiagramElementsCollection<?> & DiagramFieldsCollection & DiagramBuilderModel<?, DiagramT>> implements DelegatedDiagramBuilderModel<GeneralWrappedDiagramBuilder, DiagramT, DiagramT> {
    private DiagramT diagramBuilt;
    private DiagramAttributesCollection attributes;

    public ThisT setFields(DiagramAttributesCollection attributes) {
        this.attributes = attributes;
        return (ThisT) this;
    }

    private DiagramNodeT primitiveBuilder;

    public GeneralWrappedDiagramBuilder(DiagramNodeT primitiveBuilder) {
        this.primitiveBuilder = primitiveBuilder;
    }

    @Override
    public DiagramT buildDiagram(DiagramT parent) {
        if (attributes != null) primitiveBuilder.getAttributes().putAll(attributes.getAttributes());
        diagramBuilt = primitiveBuilder.buildDiagram(parent);
        if (getAfterBuilt() != null) getAfterBuilt().accept(diagramBuilt);
        return diagramBuilt;
    }

    @Override
    public DiagramT dereference() {
        if (diagramBuilt == null) WarningStream.putWarning("Dereferencing from unbuilt diagram.", this);
        return diagramBuilt;
    }
}
