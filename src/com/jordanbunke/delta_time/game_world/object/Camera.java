package com.jordanbunke.delta_time.game_world.object;

import com.jordanbunke.delta_time.game_world.physics.vector.Vector;

public abstract class Camera<E extends Vector<E>> extends GameObject<E> {
    public Camera(final E position) {
        super(position);
    }
}
