package com.jordanbunke.jbjgl.game_world.object;

import com.jordanbunke.jbjgl.game_world.Collider;
import com.jordanbunke.jbjgl.game_world.Vector;

public abstract class CollisionGameObject<E extends Vector<E>> extends RenderGameObject<E> {
    private final Collider<E> collider;

    public CollisionGameObject(final E position, final Collider<E> collider) {
        super(position);

        this.collider = collider;
    }

    public abstract boolean collisionCheck(final CollisionGameObject<E> other);

    public Collider<E> getCollider() {
        return collider;
    }
}
