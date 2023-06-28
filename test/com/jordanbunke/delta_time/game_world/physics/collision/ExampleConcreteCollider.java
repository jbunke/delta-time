package com.jordanbunke.delta_time.game_world.physics.collision;

import com.jordanbunke.delta_time.game_world.ecs.basic_components.collider.Collider;
import com.jordanbunke.delta_time.game_world.physics.vector.Vector;

public class ExampleConcreteCollider<E extends Vector<E>> extends Collider<E> {
    public final String name;
    public final double weight;
    private E position;
    private boolean colliding;

    @SafeVarargs
    public ExampleConcreteCollider(final String name, final double weight, final E position, final AABB<E>... boundingBoxes) {
        super(boundingBoxes);

        this.name = name;
        this.weight = weight;
        this.position = position;
        this.colliding = false;
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

    public void handleCollisionMovement(final double otherWeight, final E overlap, final double collisionFactor) {
        final double totalWeight = weight + otherWeight;
        final double proportion = weight <= 0d ? 0d
                : (otherWeight <= 0d ? 1d : otherWeight / totalWeight);
        move(overlap.scale(-proportion * collisionFactor));
    }

    public boolean isColliding() {
        return colliding;
    }

    public void setColliding(final boolean colliding) {
        this.colliding = colliding;
    }

    @Override
    public String toString() {
        return name + ":[" + position + "]";
    }

    @Override
    public void update(final double deltaTime) {

    }
}
