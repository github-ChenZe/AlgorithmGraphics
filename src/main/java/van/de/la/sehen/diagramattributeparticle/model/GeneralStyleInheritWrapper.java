package van.de.la.sehen.diagramattributeparticle.model;

import van.de.la.sehen.diagram.prototypediagram.model.DiagramModel;
import van.de.la.sehen.diagram.prototypediagram.model.StyleModel;
import van.de.la.sehen.diagram.prototypediagram.model.TreeModel;
import van.de.la.sehen.diagram.readerinterface.attributereading.DiagramAttributeCluster;
import van.de.la.sehen.diagram.readerinterface.attributereading.DiagramAttributeParticle;
import van.de.la.sehen.warning.WarningStream;

import java.util.function.Supplier;

public class GeneralStyleInheritWrapper<T extends DiagramModel<T, ParentT>, ParentT extends TreeModel<ParentT> & StyleModel<ParentT>> extends DiagramAttributeCluster implements DiagramAttributeParticle<DiagramAttributeCluster> {
    private Supplier<T> inheritFrom;
    private String fieldName;

    public GeneralStyleInheritWrapper(Supplier<T> inheritFrom, String fieldName) {
        this.inheritFrom = inheritFrom;
        this.fieldName = fieldName;
    }

    @Override
    public DiagramAttributeParticle get(int index) {
        if (index > 0) WarningStream.putWarning("Getting index > 0 on Wrapper.", this);
        return get().get(index);
    }

    public boolean isRecursionSafe(TreeModel<?> diagram) {
        T fromDiagram = inheritFrom.get();
        if (diagram == fromDiagram) {
            WarningStream.putWarning("Inheriting attribute from oneself '" + fieldName + "'.", this);
            return false;
        }
        if (!fromDiagram.isAncestorOf(diagram)) {
            WarningStream.putWarning("Inheriting attribute from a non ancestor '" + fieldName + "'.", this);
            return false;
        }
        return true;
    }

    @Override
    public DiagramAttributeCluster get() {
        T fromDiagram = inheritFrom.get();
        return fromDiagram.getStyle(fieldName);
    }
}
