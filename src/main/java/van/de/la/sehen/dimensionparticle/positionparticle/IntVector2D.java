package van.de.la.sehen.dimensionparticle.positionparticle;

import math.geom2d.Vector2D;

public class IntVector2D extends Vector2D {
    public IntVector2D() {
    }

    public IntVector2D(int x, int y) {
        super(x, y);
    }

    public int getIntX() {
        return (int)super.getX();
    }

    public int getIntY() {
        return (int)super.getY();
    }
}
