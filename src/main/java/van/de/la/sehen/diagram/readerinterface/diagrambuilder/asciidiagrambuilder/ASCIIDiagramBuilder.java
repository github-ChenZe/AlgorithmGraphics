package van.de.la.sehen.diagram.readerinterface.diagrambuilder.asciidiagrambuilder;

import van.de.la.sehen.diagram.prototypediagram.asciiprototypediagram.ASCIIDiagram;
import van.de.la.sehen.diagram.readerinterface.diagrambuilder.model.DiagramBuilderModel;

import java.util.function.Consumer;

public interface ASCIIDiagramBuilder<T extends ASCIIDiagram> extends DiagramBuilderModel<T, ASCIIDiagram> { }
