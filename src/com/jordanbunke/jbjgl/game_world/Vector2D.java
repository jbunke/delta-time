package com.jordanbunke.jbjgl.game_world;

public final class Vector2D extends Vector {
    public final double x, y;

    public Vector2D(final double x, final double y) {
        super();
        this.x = x;
        this.y = y;
    }

    public Vector2D() {
        super();
        this.x = 0.0;
        this.y = 0.0;
    }

    public Vector2D displace(final Vector2D other) {
        return displace(other.x, other.y);
    }

    public Vector2D displace(final double deltaX, final double deltaY) {
        return new Vector2D(x + deltaX, y + deltaY);
    }
}
