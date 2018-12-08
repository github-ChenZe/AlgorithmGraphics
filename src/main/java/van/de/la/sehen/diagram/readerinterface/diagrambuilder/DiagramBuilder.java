package van.de.la.sehen.diagram.readerinterface.diagrambuilder;

import van.de.la.sehen.diagram.prototypediagram.Diagram;
import van.de.la.sehen.diagram.readerinterface.diagrambuilder.model.DiagramBuilderModel;

import java.util.function.Consumer;

public interface DiagramBuilder<T extends Diagram> extends DiagramBuilderModel<T, Diagram> { }
