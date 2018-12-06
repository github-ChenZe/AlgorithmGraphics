package van.de.la.sehen.diagram.readerinterface.attributereading;

import van.de.la.sehen.util.Util;
import van.de.la.sehen.warning.WarningStream;

import java.awt.*;

public class DiagramAttributeColorParticle implements DiagramAttributeParticle<Color> {
    private Color value;

    public DiagramAttributeColorParticle(Color value) {
        this.value = value;
    }

    public static DiagramAttributeColorParticle fromObject(Object object) {
        if (object instanceof String) {
            return new DiagramAttributeColorParticle(Util.getColorByName((String) object));
        } else if (object instanceof Color) {
            return new DiagramAttributeColorParticle((Color) object);
        } else if (object instanceof DiagramAttributeColorParticle) {
            return new DiagramAttributeColorParticle(((DiagramAttributeColorParticle) object).get());
        }
        WarningStream.putWarning("Unknown object type.", DiagramAttributeColorParticle.class);
        return null;
    }

    @Override
    public Color get() {
        return value;
    }
}
