package van.de.la.sehen.diagram.readerinterface.attributereading;

import van.de.la.sehen.warning.WarningStream;

public class DiagramAttributeDoubleParticle implements DiagramAttributeParticle<Double> {
    private Double value;

    public DiagramAttributeDoubleParticle(Double value) {
        this.value = value;
    }

    public static DiagramAttributeDoubleParticle fromObject(Object object) {
        if (object instanceof DiagramAttributeDoubleParticle) {
            return new DiagramAttributeDoubleParticle(((DiagramAttributeDoubleParticle) object).get());
        } else if (object instanceof String) {
            return new DiagramAttributeDoubleParticle(Double.parseDouble((String) object));
        } else if (object instanceof Double) {
            return new DiagramAttributeDoubleParticle((Double) object);
        }
        WarningStream.putWarning("Unknown object type.", DiagramAttributeDoubleParticle.class);
        return null;
    }

    @Override
    public Double get() {
        return value;
    }
}
