package com.jordanbunke.delta_time.game_world.ecs.basic_components.collider;

import com.jordanbunke.delta_time.game_world.ecs.GameEntity;
import com.jordanbunke.delta_time.game_world.ecs.basic_components.EntityComponent;
import com.jordanbunke.delta_time.game_world.physics.collision.AABB;
import com.jordanbunke.delta_time.game_world.physics.collision.AABBCollisionDetector;
import com.jordanbunke.delta_time.game_world.physics.vector.Vector;
import com.jordanbunke.delta_time.game_world.physics.vector.Vector2D;
import com.jordanbunke.delta_time.game_world.physics.vector.Vector3D;

import java.util.function.Function;

public class TriggerCollider<E extends Vector<E>> extends Collider<E> {
    public static final AABB<Vector2D> TRIVIAL_BOUNDING_BOX_2D =
            new AABB<>(new Vector2D(1d, 1d),
                    new Vector2D(-0.5, -0.5));
    public static final AABB<Vector3D> TRIVIAL_BOUNDING_BOX_3D =
            new AABB<>(new Vector3D(1d, 1d, 1d),
                    new Vector3D(-0.5, -0.5, -0.5));

    private final Function<GameEntity<E>, Boolean> precondition;
    private final Runnable behaviour;
    private final boolean repeats;

    private boolean executed, validCollidingEntity;

    @SafeVarargs
    public TriggerCollider(
            final Runnable behaviour, final boolean repeats,
            final Function<GameEntity<E>, Boolean> precondition,
            final AABB<E>... boundingBoxes
    ) {
        super(boundingBoxes);

        this.behaviour = behaviour;
        this.repeats = repeats;
        this.precondition = precondition;

        executed = false;
    }

    @SafeVarargs
    public TriggerCollider(
            final Runnable behaviour, final boolean repeats,
            final AABB<E>... boundingBoxes
    ) {
        this(behaviour, repeats, e -> true, boundingBoxes);
    }

    @SafeVarargs
    public static <E extends Vector<E>> TriggerCollider<E> collidingEntityExactMatch(
            final Runnable behaviour, final boolean repeats,
            final GameEntity<E> toMatch,
            final AABB<E>... boundingBoxes
    ) {
        final Function<GameEntity<E>, Boolean> precondition =
                e -> e.equals(toMatch);

        return new TriggerCollider<>(behaviour,
                repeats, precondition, boundingBoxes);
    }

    @SafeVarargs
    public static <E extends Vector<E>> TriggerCollider<E>
    collidingEntityPossessesNecessaryComponents(
            final Runnable behaviour, final boolean repeats,
            final Class<EntityComponent<E>>[] necessaryComponents,
            final AABB<E>... boundingBoxes
    ) {
        final Function<GameEntity<E>, Boolean> precondition = e -> {
            for (Class<EntityComponent<E>> c : necessaryComponents)
                if (!e.hasComponent(c))
                    return false;

            return true;
        };

        return new TriggerCollider<>(behaviour,
                repeats, precondition, boundingBoxes);
    }

    public void handleCollision(final Collider<E> that) {
        validCollidingEntity |= precondition.apply(that.getEntity());
    }

    @Override
    public void update(double deltaTime) {
        if (isColliding() && validCollidingEntity && !executed) {
            behaviour.run();

            if (!repeats)
                executed = true;
        }

        validCollidingEntity = false;
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

        if (colliding)
            handleCollision(that);
    }
}
