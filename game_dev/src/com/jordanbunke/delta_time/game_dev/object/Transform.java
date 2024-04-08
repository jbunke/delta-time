package com.jordanbunke.delta_time.game_dev.object;

import com.jordanbunke.delta_time.game_dev.physics.vector.Vector;

public abstract class Transform<E extends Vector<E>> {
    private E position;

    public Transform(final E position) {
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
