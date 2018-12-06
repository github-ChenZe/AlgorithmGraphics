package van.de.la.sehen.diagramattributeparticle.enumeratedparticle;

public enum ArrowStyle implements SelfContainedStyleParticle<ArrowStyle> {
    ARROW, NONE;

    public static ArrowStyle fromObject(Object object) {
        return SelfContainedStyleParticle.fromObject(ArrowStyle.class, object);
    }
}
