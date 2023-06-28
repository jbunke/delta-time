package com.jordanbunke.jbjgl.game_world.ecs.basic_components.collider;

import com.jordanbunke.jbjgl.game_world.physics.collision.AABB;
import com.jordanbunke.jbjgl.game_world.physics.collision.AABBCollisionDetector;
import com.jordanbunke.jbjgl.game_world.physics.vector.Vector2D;

public abstract class WeightedCollider2D extends WeightedCollider<Vector2D> {
    @SafeVarargs
    public WeightedCollider2D(final double weight, final AABB<Vector2D>... boundingBoxes) {
        super(weight, boundingBoxes);
    }

    @Override
    public void checkCollision(final WeightedCollider<Vector2D> that, final double collisionFactor) {
        final Vector2D overlap = AABBCollisionDetector.collisionOverlap2D(this, that);
        final boolean colliding = !overlap.equals(new Vector2D());

        this.setColliding(isColliding() || colliding);
        that.setColliding(that.isColliding() || colliding);

        if (colliding) {
            this.handleCollision(that.getWeight(), overlap, collisionFactor);
            that.handleCollision(this.getWeight(), overlap.scale(-1), collisionFactor);
        }
    }
}
