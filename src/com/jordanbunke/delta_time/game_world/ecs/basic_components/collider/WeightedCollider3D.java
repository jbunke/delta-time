package com.jordanbunke.delta_time.game_world.ecs.basic_components.collider;

import com.jordanbunke.delta_time.game_world.physics.collision.AABB;
import com.jordanbunke.delta_time.game_world.physics.collision.AABBCollisionDetector;
import com.jordanbunke.delta_time.game_world.physics.vector.Vector3D;

public abstract class WeightedCollider3D extends WeightedCollider<Vector3D> {
    @SafeVarargs
    public WeightedCollider3D(final double weight, final AABB<Vector3D>... boundingBoxes) {
        super(weight, boundingBoxes);
    }

    @Override
    public void checkCollision(final WeightedCollider<Vector3D> that, final double collisionFactor) {
        final Vector3D overlap = AABBCollisionDetector.collisionOverlap3D(this, that);
        final boolean colliding = !overlap.equals(new Vector3D());

        this.setColliding(isColliding() || colliding);
        that.setColliding(that.isColliding() || colliding);

        if (colliding) {
            this.handleCollision(that.getWeight(), overlap, collisionFactor);
            that.handleCollision(this.getWeight(), overlap.scale(-1), collisionFactor);
        }
    }
}
