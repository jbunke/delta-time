package com.jordanbunke.jbjgl.game_world;

public abstract class Entity<E extends Vector> {
    private E position;

    public Entity(final E position) {
        this.position = position;
    }

    public E getPosition() {
        return position;
    }

    public void setPosition(final E position) {
        this.position = position;
    }
}
