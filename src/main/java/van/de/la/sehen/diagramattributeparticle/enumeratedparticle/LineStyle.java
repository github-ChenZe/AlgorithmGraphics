package van.de.la.sehen.diagramattributeparticle.enumeratedparticle;

public enum LineStyle implements SelfContainedStyleParticle<LineStyle> {
    SOLID, DASHED;

    public static LineStyle fromObject(Object object) {
        return SelfContainedStyleParticle.fromObject(LineStyle.class, object);
    }
}
