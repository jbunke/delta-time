package com.jordanbunke.delta_time.game_world.ecs.basic_components.collider;

import com.jordanbunke.delta_time.game_world.physics.collision.AABB;
import com.jordanbunke.delta_time.game_world.physics.vector.Vector;

public abstract class WeightedCollider<E extends Vector<E>> extends Collider<E> {
    private boolean colliding;
    private double weight;

    @SafeVarargs
    public WeightedCollider(final double weight, final AABB<E>... boundingBoxes) {
        super(boundingBoxes);

        this.weight = weight;
        this.colliding = false;
    }

    public abstract void checkCollision(final WeightedCollider<E> that, final double collisionFactor);

    public void handleCollision(
            final double otherWeight, final E overlap,
            final double collisionFactor
    ) {
        final double totalWeight = weight + otherWeight;
        final double proportion = weight <= 0d ? 0d
                : (otherWeight <= 0d ? 1d : otherWeight / totalWeight);
        getEntity().move(overlap.scale(-proportion * collisionFactor));
    }

    public boolean isColliding() {
        return colliding;
    }

    public double getWeight() {
        return weight;
    }

    public void setColliding(final boolean colliding) {
        this.colliding = colliding;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }
}
