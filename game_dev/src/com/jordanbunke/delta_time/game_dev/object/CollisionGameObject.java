package com.jordanbunke.delta_time.game_dev.object;

import com.jordanbunke.delta_time.game_dev.physics.vector.Vector;
import com.jordanbunke.delta_time.game_dev.ecs.basic_components.collider.Collider;

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
