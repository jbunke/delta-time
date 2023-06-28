package com.jordanbunke.jbjgl.game_world.ecs.basic_components.collider;

import com.jordanbunke.jbjgl.game_world.ecs.basic_components.EntityComponent;
import com.jordanbunke.jbjgl.game_world.physics.collision.AABB;
import com.jordanbunke.jbjgl.game_world.physics.vector.Vector;

public abstract class Collider<E extends Vector<E>> extends EntityComponent<E> {
    public final AABB<E>[] boundingBoxes;

    @SafeVarargs
    public Collider(final AABB<E>... boundingBoxes) {
        this.boundingBoxes = boundingBoxes;
    }

    public abstract E getPosition();
}
