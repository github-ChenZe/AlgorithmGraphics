package van.de.la.sehen.diagram.readerinterface.attributereading;

import van.de.la.sehen.util.Util;
import van.de.la.sehen.warning.WarningStream;

public class DiagramAttributeSwapAnimationParticle implements DiagramAttributeParticle<DiagramAttributeSwapAnimationParticle> {
    private boolean swap;
    private int rowFrom;
    private int rowTo;
    private int columnFrom;
    private int columnTo;

    public boolean isSwap() {
        return swap;
    }

    public int getRowFrom() {
        return rowFrom;
    }

    public int getRowTo() {
        return rowTo;
    }

    public int getColumnFrom() {
        return columnFrom;
    }

    public int getColumnTo() {
        return columnTo;
    }

    public DiagramAttributeSwapAnimationParticle(boolean swap, int rowFrom, int rowTo, int columnFrom, int columnTo) {
        this.swap = swap;
        this.rowFrom = rowFrom;
        this.rowTo = rowTo;
        this.columnFrom = columnFrom;
        this.columnTo = columnTo;
    }

    public DiagramAttributeSwapAnimationParticle(DiagramAttributeSwapAnimationParticle old) {
        this(old.swap, old.rowFrom, old.rowTo, old.columnFrom, old.columnTo);
    }

    public static DiagramAttributeSwapAnimationParticle fromObject(Object object) {
        if (object instanceof DiagramAttributeSwapAnimationParticle) {
            return new DiagramAttributeSwapAnimationParticle((DiagramAttributeSwapAnimationParticle) object);
        } else if (object instanceof String) {
            String[] groups = Util.groups("\\(([0-9]*),([0-9]*)\\)->\\(([0-9]*),([0-9]*)\\)", (String) object);
            if (groups == null || groups.length != 4) {
                WarningStream.putWarning("Invalid pattern for swap animation.", DiagramAttributeSwapAnimationParticle.class);
                return null;
            }
            return new DiagramAttributeSwapAnimationParticle(
                    true,
                    Integer.parseInt(groups[0]),
                    Integer.parseInt(groups[2]),
                    Integer.parseInt(groups[1]),
                    Integer.parseInt(groups[3]));
        }
        WarningStream.putWarning("Unknown object type.", DiagramAttributeSwapAnimationParticle.class);
        return null;
    }

    @Override
    public DiagramAttributeSwapAnimationParticle get() {
        return this;
    }
}
