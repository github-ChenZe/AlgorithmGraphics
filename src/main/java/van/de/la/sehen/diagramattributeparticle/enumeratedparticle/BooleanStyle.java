package van.de.la.sehen.diagramattributeparticle.enumeratedparticle;

import van.de.la.sehen.diagram.readerinterface.attributereading.DiagramAttributeIntegerParticle;
import van.de.la.sehen.diagram.readerinterface.attributereading.DiagramAttributeParticle;
import van.de.la.sehen.util.Util;
import van.de.la.sehen.warning.WarningStream;

public enum BooleanStyle implements DiagramAttributeParticle<Boolean> {
    TRUE, FALSE;

    public static BooleanStyle fromBoolean(Boolean bool) {
        return bool ? TRUE : FALSE;
    }

    public static BooleanStyle fromObject(Object object) {
        BooleanStyle style = Util.getEnumerationByObject(BooleanStyle.class, object);
        if (style == null && object instanceof Boolean) style = fromBoolean((Boolean) object);
        if (style == null) WarningStream.putWarning("Unknown object type.", DiagramAttributeIntegerParticle.class);
        return style;
    }

    public boolean toBoolean() {
        return this == TRUE;
    }

    @Override
    public Boolean get() {
        return toBoolean();
    }
}
