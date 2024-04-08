package com.jordanbunke.delta_time.game_world.ecs.basic_components;

import com.jordanbunke.delta_time.game_world.ecs.GameEntity;
import com.jordanbunke.delta_time.game_world.physics.vector.Vector;

public abstract class EntityComponent<E extends Vector<E>> {
    private GameEntity<E> entity;

    public EntityComponent() {
        entity = null;
    }

    public void start() {}

    public abstract void update(final double deltaTime);

    public GameEntity<E> getEntity() {
        return entity;
    }

    public void setEntity(final GameEntity<E> entity) {
        this.entity = entity;
    }
}
