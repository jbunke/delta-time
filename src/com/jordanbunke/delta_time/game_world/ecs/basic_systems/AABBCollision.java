package com.jordanbunke.delta_time.game_world.ecs.basic_systems;

import com.jordanbunke.delta_time.game_world.ecs.GameEntity;
import com.jordanbunke.delta_time.game_world.ecs.basic_components.collider.Collider;
import com.jordanbunke.delta_time.game_world.physics.vector.Vector;

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

        final Set<Collider<E>> colliders = new HashSet<>();
        entities.forEach(e -> e.executeIfComponentPresent(Collider.class, colliders::add));
        final Set<Collider<E>> processedColliders = new HashSet<>();

        for (Collider<E> c : colliders)
            c.setColliding(false);

        for (Collider<E> reference : colliders) {
            processedColliders.add(reference);

            for (Collider<E> collider : colliders) {
                if (collider.equals(reference) || (processedColliders.contains(collider)))
                    continue;

                reference.checkCollision(collider, collisionFactor);
            }
        }
    }
}
