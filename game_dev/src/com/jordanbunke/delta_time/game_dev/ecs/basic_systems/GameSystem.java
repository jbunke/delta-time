package com.jordanbunke.delta_time.game_dev.ecs.basic_systems;

import com.jordanbunke.delta_time.game_dev.physics.vector.Vector;
import com.jordanbunke.delta_time.game_dev.ecs.GameEntity;

import java.util.Collection;
import java.util.Map;

public interface GameSystem<E extends Vector<E>, T> {
    void handle(final Collection<GameEntity<E>> entities, final Map<String, T> args);
    default void handle(final Collection<GameEntity<E>> entities) {
        handle(entities, Map.of());
    }
}
