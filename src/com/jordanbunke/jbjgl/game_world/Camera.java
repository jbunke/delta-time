package com.jordanbunke.jbjgl.game_world;

public abstract class Camera<E extends Vector> extends Entity<E> {
    public Camera(final E position) {
        super(position);
    }
}
