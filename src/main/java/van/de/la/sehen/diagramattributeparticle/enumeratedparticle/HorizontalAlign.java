package van.de.la.sehen.diagramattributeparticle.enumeratedparticle;

public enum HorizontalAlign implements SelfContainedStyleParticle<HorizontalAlign> {
    LEFT, CENTER, RIGHT;

    public static HorizontalAlign fromObject(Object object) {
        return SelfContainedStyleParticle.fromObject(HorizontalAlign.class, object);
    }
}
