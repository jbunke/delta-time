package com.jordanbunke.jbjgl.game_world.object;

import com.jordanbunke.jbjgl.game_world.Vector;

public abstract class GameObject<E extends Vector> {
    private E position;

    public GameObject(final E position) {
        this.position = position;
    }

    public E getPosition() {
        return position;
    }

    public void setPosition(final E position) {
        this.position = position;
    }
}
