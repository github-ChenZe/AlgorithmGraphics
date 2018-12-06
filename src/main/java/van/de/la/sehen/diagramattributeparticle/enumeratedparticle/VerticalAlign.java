package van.de.la.sehen.diagramattributeparticle.enumeratedparticle;

public enum VerticalAlign implements SelfContainedStyleParticle<VerticalAlign> {
    TOP, CENTER, BOTTOM;

    public static VerticalAlign fromObject(Object object) {
        return SelfContainedStyleParticle.fromObject(VerticalAlign.class, object);
    }
}
