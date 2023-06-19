package com.jordanbunke.jbjgl.game_world;

public class Collider<E extends Vector<E>> {
    private boolean active;
    private E dimensions, offset;

    public Collider(final E dimensions, final E offset, final boolean active) {
        this.dimensions = dimensions;
        this.offset = offset;
        this.active = active;
    }

    public Collider(final E dimensions) {
        this.dimensions = dimensions;
        /* Initialization that centers the collider on its owner's position */
        this.offset = dimensions.scale(-0.5);
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

    public E getOffset() {
        return offset;
    }

    public boolean isActive() {
        return active;
    }
}
