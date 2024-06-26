package com.jordanbunke.delta_time.game_dev.physics.collision;

import com.jordanbunke.delta_time.game_dev.ecs.basic_components.collider.WeightedCollider;
import com.jordanbunke.delta_time.game_dev.physics.vector.Vector;

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

    @Override
    public void handleCollision(
            final double otherWeight, final E overlap,
            final double collisionFactor
    ) {
        final double totalWeight = getWeight() + otherWeight;
        final double proportion = getWeight() <= 0d ? 0d
                : (otherWeight <= 0d ? 1d : otherWeight / totalWeight);
        move(overlap.scale(-proportion * collisionFactor));
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
