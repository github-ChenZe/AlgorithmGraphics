package van.de.la.sehen.diagram.readerinterface.attributereading;

import van.de.la.sehen.warning.WarningStream;

public class DiagramAttributeIntegerParticle implements DiagramAttributeParticle<Integer> {
    private Integer value;

    public DiagramAttributeIntegerParticle(Integer value) {
        this.value = value;
    }

    public static DiagramAttributeIntegerParticle fromObject(Object object) {
        if (object instanceof DiagramAttributeIntegerParticle) {
            return new DiagramAttributeIntegerParticle(((DiagramAttributeIntegerParticle) object).get());
        } else if (object instanceof String) {
            return new DiagramAttributeIntegerParticle(Integer.parseInt((String) object));
        } else if (object instanceof Integer) {
            return new DiagramAttributeIntegerParticle((Integer) object);
        }
        WarningStream.putWarning("Unknown object type.", DiagramAttributeIntegerParticle.class);
        return null;
    }

    @Override
    public Integer get() {
        return value;
    }
}
