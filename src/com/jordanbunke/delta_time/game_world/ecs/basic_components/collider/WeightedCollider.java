package com.jordanbunke.delta_time.game_world.ecs.basic_components.collider;

import com.jordanbunke.delta_time.game_world.physics.collision.AABB;
import com.jordanbunke.delta_time.game_world.physics.collision.AABBCollisionDetector;
import com.jordanbunke.delta_time.game_world.physics.vector.Vector;

public class WeightedCollider<E extends Vector<E>> extends Collider<E> {
    private double weight;

    @SafeVarargs
    public WeightedCollider(final double weight, final AABB<E>... boundingBoxes) {
        super(boundingBoxes);

        this.weight = weight;
    }

    @Override
    public E getPosition() {
        return getEntity().getPosition();
    }

    @Override
    public void checkCollision(final Collider<E> that, final double collisionFactor) {
        final E overlap = AABBCollisionDetector.collisionOverlap(this, that);
        final boolean colliding = !overlap.equals(Vector.origin(overlap));

        this.setColliding(isColliding() || colliding);
        that.setColliding(that.isColliding() || colliding);

        if (colliding) {
            if (that instanceof WeightedCollider<E> w) {
                this.handleCollision(w.getWeight(), overlap, collisionFactor);
                w.handleCollision(this.getWeight(),
                        overlap.scale(-1), collisionFactor);
            } else if (that instanceof TriggerCollider<E> t)
                t.handleCollision(this);
        }
    }

    @Override
    public void update(double deltaTime) {

    }

    public void handleCollision(
            final double otherWeight, final E overlap,
            final double collisionFactor
    ) {
        final double totalWeight = weight + otherWeight;
        final double proportion = weight <= 0d ? 0d
                : (otherWeight <= 0d ? 1d : otherWeight / totalWeight);
        getEntity().move(overlap.scale(-proportion * collisionFactor));
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }
}
