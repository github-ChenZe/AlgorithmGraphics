package van.de.la.sehen.diagram.readerinterface.attributereading;

import van.de.la.sehen.warning.WarningStream;

public class DiagramAttributeStringParticle implements DiagramAttributeParticle<String> {
    private String literalValue;

    public DiagramAttributeStringParticle(String literalValue) {
        this.literalValue = literalValue;
    }

    public static DiagramAttributeStringParticle fromObject(Object object) {
        if (object instanceof DiagramAttributeStringParticle) {
            return new DiagramAttributeStringParticle(((DiagramAttributeStringParticle) object).get());
        } else if (object instanceof String) {
            return new DiagramAttributeStringParticle((String) object);
        }
        WarningStream.putWarning("Unknown object type.", DiagramAttributeStringParticle.class);
        return null;
    }

    @Override
    public String get() {
        return literalValue;
    }
}