package com.jordanbunke.delta_time.game_world.ecs.basic_components.collider;

import com.jordanbunke.delta_time.game_world.ecs.basic_components.EntityComponent;
import com.jordanbunke.delta_time.game_world.physics.collision.AABB;
import com.jordanbunke.delta_time.game_world.physics.vector.Vector;

public abstract class Collider<E extends Vector<E>> extends EntityComponent<E> {
    public final AABB<E>[] boundingBoxes;

    @SafeVarargs
    public Collider(final AABB<E>... boundingBoxes) {
        this.boundingBoxes = boundingBoxes;
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
}
