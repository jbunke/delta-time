package com.jordanbunke.jbjgl.game_world.physics.collision;

import com.jordanbunke.jbjgl.game_world.physics.vector.Vector;

public abstract class Collider<E extends Vector<E>> {
    public final AABB<E>[] boundingBoxes;

    @SafeVarargs
    public Collider(final AABB<E>... boundingBoxes) {
        this.boundingBoxes = boundingBoxes;
    }

    public abstract E getPosition();
}
