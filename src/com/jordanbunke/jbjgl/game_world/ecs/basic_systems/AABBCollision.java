package com.jordanbunke.jbjgl.game_world.ecs.basic_systems;

import com.jordanbunke.jbjgl.game_world.ecs.GameEntity;
import com.jordanbunke.jbjgl.game_world.ecs.basic_components.collider.WeightedCollider;
import com.jordanbunke.jbjgl.game_world.physics.vector.Vector;

import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public abstract class AABBCollision<E extends Vector<E>> implements GameSystem<E, Double> {
    public static final String COLLISION_FACTOR = "collision_factor";
    private static final double DEFAULT_COLLISION_FACTOR = 1d;

    @Override
    public void handle(final Collection<GameEntity<E>> entities, final Map<String, Double> args) {
        final double collisionFactor = args.getOrDefault(
                COLLISION_FACTOR, DEFAULT_COLLISION_FACTOR);

        final Set<WeightedCollider<E>> colliders = new HashSet<>();
        entities.forEach(e -> e.executeIfComponentPresent(WeightedCollider.class, colliders::add));
        final Set<WeightedCollider<E>> processedColliders = new HashSet<>();

        for (WeightedCollider<E> c : colliders)
            c.setColliding(false);

        for (WeightedCollider<E> reference : colliders) {
            processedColliders.add(reference);

            for (WeightedCollider<E> collider : colliders) {
                if (collider.equals(reference) || (processedColliders.contains(collider)))
                    continue;

                reference.checkCollision(collider, collisionFactor);
            }
        }
    }
}
