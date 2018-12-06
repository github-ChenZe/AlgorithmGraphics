package van.de.la.sehen.diagramattributeparticle.enumeratedparticle;

import van.de.la.sehen.diagram.readerinterface.attributereading.DiagramAttributeParticle;
import van.de.la.sehen.util.Util;
import van.de.la.sehen.warning.WarningStream;

public interface SelfContainedStyleParticle<T extends Enum> extends DiagramAttributeParticle<T> {
    @Override
    default T get() {
        return (T) this;
    }

    static <T extends Enum> T fromObject(Class<T> enumerationClass, Object object) {
        T result = Util.getEnumerationByObject(enumerationClass, object);
        if (result == null) WarningStream.putWarning("Unknown object type.", enumerationClass);
        return result;
    }
}
