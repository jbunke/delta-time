package com.jordanbunke.jbjgl.game_world;

public class Collider<E extends Vector> {
    private final boolean centered;
    private boolean active;
    private E dimensions;

    public Collider(final E dimensions, final boolean centered, final boolean active) {
        this.dimensions = dimensions;
        this.centered = centered;
        this.active = active;
    }

    public Collider(final E dimensions) {
        this.dimensions = dimensions;
        this.centered = true;
        this.active = true;
    }

    public void activate() {
        this.active = true;
    }

    public void deactivate() {
        this.active = false;
    }

    public void setDimensions(final E dimensions) {
        this.dimensions = dimensions;
    }

    public E getDimensions() {
        return dimensions;
    }

    public boolean isCentered() {
        return centered;
    }

    public boolean isActive() {
        return active;
    }
}
