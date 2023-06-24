package com.jordanbunke.jbjgl.game_world.object;

import com.jordanbunke.jbjgl.game_world.physics.vector.Vector;

public abstract class GameObject<E extends Vector<E>> {
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

    public void move(final E displacement) {
        position = position.displace(displacement);
    }
}
