package com.jordanbunke.jbjgl.game_world.ecs.basic_systems;

import com.jordanbunke.jbjgl.game_world.ecs.GameEntity;
import com.jordanbunke.jbjgl.game_world.physics.vector.Vector;

import java.util.Collection;
import java.util.Map;

public interface GameSystem<E extends Vector<E>, T> {
    void handle(final Collection<GameEntity<E>> entities, final Map<String, T> args);
    default void handle(final Collection<GameEntity<E>> entities) {
        handle(entities, Map.of());
    }
}
