package van.de.la.sehen.diagram.prototypediagram.model;

import org.springframework.lang.Nullable;
import van.de.la.sehen.diagram.pseudodiagram.PseudoDiagram;
import van.de.la.sehen.diagramstyle.DiagramStyle;
import van.de.la.sehen.diagramtopology.ChildIndex;
import van.de.la.sehen.diagramtopology.RootIndex;
import van.de.la.sehen.dimensionparticle.positionparticle.AbsoluteParticle;
import van.de.la.sehen.dimensionparticle.positionparticle.AbsoluteTuple;
import van.de.la.sehen.dimensionparticle.positionparticle.OffsetParticle;
import van.de.la.sehen.dimensionparticle.sizeparticle.DimensionComponent;
import van.de.la.sehen.warning.WarningStream;

import java.util.HashMap;
import java.util.Map;

public abstract class GeneralAbstractDiagram<
        ThisT extends GeneralAbstractDiagram<ThisT, PositionT, PositionTV, PositionComponentT, OffsetT, SizeComponentT, SizeOffsetT, CanvasT, ImageT>,
        PositionT extends AbsoluteTuple<PositionT, PositionTV, PositionComponentT>,
        PositionTV,
        PositionComponentT extends AbsoluteParticle<PositionComponentT, ?>,
        OffsetT extends OffsetParticle<PositionT, PositionTV, OffsetT>,
        SizeComponentT extends DimensionComponent<?, SizeOffsetT>,
        SizeOffsetT extends OffsetParticle<?, ?, SizeOffsetT>,
        CanvasT,
        ImageT
        > implements AbstractDiagramModel<ThisT, PositionT, PositionTV, PositionComponentT, OffsetT, SizeComponentT, SizeOffsetT, CanvasT, ImageT> {

    public static final double PI = 3.14159265358979;

    public GeneralAbstractDiagram(ThisT parent, DiagramStyle style) {
        this.parent = parent;
        this.style = style;
        this.position = baseOffset();
    }

    public DiagramStyle getStyle() {
        return style;
    }

    private DiagramStyle style;

    public OffsetT getPosition() {
        return position;
    }

    public void setPosition(OffsetT offset) { this.position = offset; }

    private OffsetT position;

    public ThisT getParent() {
        return parent;
    }

    private ThisT parent;

    @Nullable
    public SizeComponentT getWidth() {
        if (width == null) {
            WarningStream.putWarning("Width not set yet.", this);
        }
        return width;
    }

    public void setWidth(SizeComponentT width) {
        this.width = width;
    }

    private SizeComponentT width;

    @Nullable
    public SizeComponentT getHeight() {
        if (height == null) {
            WarningStream.putWarning("Height not set yet.", this);
        }
        return height;
    }

    public void setHeight(SizeComponentT height) {
        this.height = height;
    }

    private SizeComponentT height;

    private Map<ChildIndex, ThisT> indexToChild = new HashMap<>();
    private Map<ThisT, ChildIndex> childToIndex = new HashMap<>();

    // wild child holds those pseudo diagrams which are not indexed, like free-pseudo and fixed-pseudo

    @Nullable
    public ThisT getChildFromTopologicalIndex(@Nullable ChildIndex index) {
        if (!indexToChild.containsKey(index)) {
            ThisT wildChild = getWildChildFromTopologicalIndex(index);
            if (wildChild != null) return wildChild;
            WarningStream.putWarning("This node does not contain the required child " + index + ".", this);
            return null;
        }
        return indexToChild.get(index);
    }

    @Nullable
    protected ThisT getWildChildFromTopologicalIndex(@Nullable ChildIndex index) { return null; }

    @Nullable
    public ChildIndex getTopologicalIndexOfChild(@Nullable ThisT child) {
        if (!childToIndex.containsKey(child)) {
            ChildIndex wildIndex = getTopologicalIndexOfWildChild(child);
            if (wildIndex != null) return wildIndex;
            WarningStream.putWarning("This child " + child + " had not been indexed.", this);
            return null;
        }
        return childToIndex.get(child);
    }

    @Nullable
    protected ChildIndex getTopologicalIndexOfWildChild(@Nullable ThisT wildChild) { return null; }

    /**
     * This function may not return the same index as from getTopologicalIndexOfChild
     * if the child diagram has overridden this method
     * @return the index of this diagram in parent diagram
     */

    @Nullable
    public ChildIndex getTopologicalIndex() {
        ThisT parent = getParent();
        if (parent == null) return new RootIndex(this);
        return parent.getTopologicalIndexOfChild((ThisT) this);
    }

    public void removeTopologicalChild(ThisT child) {
        if (!childToIndex.containsKey(child)) {
            WarningStream.putWarning("Removing an unindexed child.", this);
            return;
        }
        ChildIndex index = childToIndex.get(child);
        childToIndex.remove(child);
        indexToChild.remove(index);
    }

    public <T extends ThisT> T putTopologicalChild(ChildIndex index, T child) {
        indexToChild.put(index, child);
        childToIndex.put(child, index);
        return child;
    }

    public ThisT getTopologicalCorrespondence(ThisT correspondentParent) {
        // use getTopologicalIndexOfChild to avoid child which has overridden getTopologicalIndex mess up the index
        return correspondentParent.getChildFromTopologicalIndex(getParent().getTopologicalIndexOfChild((ThisT) this));
    }
}
