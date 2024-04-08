package com.jordanbunke.delta_time.game_dev.object;

import com.jordanbunke.delta_time.game_dev.physics.vector.Vector;

public abstract class Camera<E extends Vector<E>> extends Transform<E> {
    public Camera(final E position) {
        super(position);
    }
}
