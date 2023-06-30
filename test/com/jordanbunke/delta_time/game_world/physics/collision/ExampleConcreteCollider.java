package com.jordanbunke.delta_time.game_world.physics.collision;

import com.jordanbunke.delta_time.game_world.ecs.basic_components.collider.WeightedCollider;
import com.jordanbunke.delta_time.game_world.physics.vector.Vector;

public class ExampleConcreteCollider<E extends Vector<E>> extends WeightedCollider<E> {
    public final String name;
    private E position;

    @SafeVarargs
    public ExampleConcreteCollider(
            final String name, final double weight, final E position,
            final AABB<E>... boundingBoxes
    ) {
        super(weight, boundingBoxes);

        this.name = name;
        this.position = position;
    }

    @Override
    public E getPosition() {
        if (getEntity() != null)
            return getEntity().getPosition();

        return position;
    }

    public void move(final E displacement) {
        position = position.displace(displacement);
    }

    public void setPosition(final E position) {
        this.position = position;
    }

    @Override
    public String toString() {
        return name + ":[" + position + "]";
    }
}
