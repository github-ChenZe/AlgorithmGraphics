package van.de.la.sehen.diagram.pseudodiagram;

import van.de.la.sehen.diagram.prototypediagram.model.AbstractDiagramModel;
import van.de.la.sehen.diagram.prototypediagram.model.StyleModel;
import van.de.la.sehen.diagram.prototypediagram.model.TreeModel;
import van.de.la.sehen.diagram.readerinterface.attributereading.DiagramAttributeParticle;
import van.de.la.sehen.dimensionparticle.positionparticle.OffsetParticle;
import van.de.la.sehen.warning.WarningStream;

interface PseudoDiagramModel<
        OffsetT extends OffsetParticle<?, ?, OffsetT>,
        ParentT extends AbstractDiagramModel<ParentT, ?, ?, ?, ?, ?, OffsetT, ?, ?>> extends TreeModel<ParentT>, StyleModel<ParentT> {
    OffsetT getX();
    OffsetT getY();
}

interface CPseudoDiagramModel<
        OffsetT extends OffsetParticle<?, ?, OffsetT>,
        ParentT extends AbstractDiagramModel<ParentT, ?, ?, ?, ?, ?, OffsetT, ?, ?>> extends PseudoDiagramModel<OffsetT, ParentT> {
    default OffsetT getX() {
        return getParent().midX();
    }

    default OffsetT getY() {
        return getParent().midY();
    }
}

interface EPseudoDiagramModel<
        OffsetT extends OffsetParticle<?, ?, OffsetT>,
        ParentT extends AbstractDiagramModel<ParentT, ?, ?, ?, ?, ?, OffsetT, ?, ?>> extends PseudoDiagramModel<OffsetT, ParentT> {
    default OffsetT getX() {
        return getParent().maxX();
    }

    default OffsetT getY() {
        return getParent().midY();
    }
}

interface NEPseudoDiagramModel<
        OffsetT extends OffsetParticle<?, ?, OffsetT>,
        ParentT extends AbstractDiagramModel<ParentT, ?, ?, ?, ?, ?, OffsetT, ?, ?>> extends PseudoDiagramModel<OffsetT, ParentT> {
    default OffsetT getX() {
        return getParent().maxX();
    }

    default OffsetT getY() {
        return getParent().minY();
    }
}

interface NPseudoDiagramModel<
        OffsetT extends OffsetParticle<?, ?, OffsetT>,
        ParentT extends AbstractDiagramModel<ParentT, ?, ?, ?, ?, ?, OffsetT, ?, ?>> extends PseudoDiagramModel<OffsetT, ParentT> {
    default OffsetT getX() {
        return getParent().midX();
    }

    default OffsetT getY() {
        return getParent().minY();
    }
}

interface NWPseudoDiagramModel<
        OffsetT extends OffsetParticle<?, ?, OffsetT>,
        ParentT extends AbstractDiagramModel<ParentT, ?, ?, ?, ?, ?, OffsetT, ?, ?>> extends PseudoDiagramModel<OffsetT, ParentT> {
    default OffsetT getX() {
        return getParent().minX();
    }

    default OffsetT getY() {
        return getParent().minY();
    }
}

interface SEPseudoDiagramModel<
        OffsetT extends OffsetParticle<?, ?, OffsetT>,
        ParentT extends AbstractDiagramModel<ParentT, ?, ?, ?, ?, ?, OffsetT, ?, ?>> extends PseudoDiagramModel<OffsetT, ParentT> {
    default OffsetT getX() {
        return getParent().maxX();
    }

    default OffsetT getY() {
        return getParent().maxY();
    }
}

interface SPseudoDiagramModel<
        OffsetT extends OffsetParticle<?, ?, OffsetT>,
        ParentT extends AbstractDiagramModel<ParentT, ?, ?, ?, ?, ?, OffsetT, ?, ?>> extends PseudoDiagramModel<OffsetT, ParentT> {
    default OffsetT getX() {
        return getParent().midX();
    }

    default OffsetT getY() {
        return getParent().maxY();
    }
}

interface SWPseudoDiagramModel<
        OffsetT extends OffsetParticle<?, ?, OffsetT>,
        ParentT extends AbstractDiagramModel<ParentT, ?, ?, ?, ?, ?, OffsetT, ?, ?>> extends PseudoDiagramModel<OffsetT, ParentT> {
    default OffsetT getX() {
        return getParent().minX();
    }

    default OffsetT getY() {
        return getParent().maxY();
    }
}

interface WPseudoDiagramModel<
        OffsetT extends OffsetParticle<?, ?, OffsetT>,
        ParentT extends AbstractDiagramModel<ParentT, ?, ?, ?, ?, ?, OffsetT, ?, ?>> extends PseudoDiagramModel<OffsetT, ParentT> {
    default OffsetT getX() {
        return getParent().minX();
    }

    default OffsetT getY() {
        return getParent().midY();
    }
}