package com.jordanbunke.delta_time.game_dev.ecs.basic_components.collider;

import com.jordanbunke.delta_time.game_dev.physics.vector.Vector;
import com.jordanbunke.delta_time.game_dev.ecs.basic_components.EntityComponent;
import com.jordanbunke.delta_time.game_dev.physics.collision.AABB;

public abstract class Collider<E extends Vector<E>> extends EntityComponent<E> {
    public final AABB<E>[] boundingBoxes;
    private boolean colliding;

    @SafeVarargs
    public Collider(final AABB<E>... boundingBoxes) {
        this.boundingBoxes = boundingBoxes;

        colliding = false;
    }

    public abstract E getPosition();

    public E beginning() {
        E beginning = getPosition();

        for (AABB<E> boundingBox : boundingBoxes)
            beginning = beginning.minOfAxes(boundingBox.beginning(getPosition()));

        return beginning;
    }

    public E end() {
        E end = getPosition();

        for (AABB<E> boundingBox : boundingBoxes)
            end = end.maxOfAxes(boundingBox.end(getPosition()));

        return end;
    }

    public abstract void checkCollision(final Collider<E> that, final double collisionFactor);

    public boolean isColliding() {
        return colliding;
    }

    public void setColliding(final boolean colliding) {
        this.colliding = colliding;
    }
}
