package com.jordanbunke.jbjgl.game_world.entity;

import com.jordanbunke.jbjgl.game_world.Collider;
import com.jordanbunke.jbjgl.game_world.Vector;

public abstract class CollisionEntity<E extends Vector> extends RenderEntity<E> {
    private final Collider<E> collider;

    public CollisionEntity(final E position, final Collider<E> collider) {
        super(position);

        this.collider = collider;
    }

    public abstract boolean collisionCheck(final CollisionEntity<E> other);

    public Collider<E> getCollider() {
        return collider;
    }
}
