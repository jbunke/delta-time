package com.jordanbunke.delta_time.game_dev.ecs.basic_components;

import com.jordanbunke.delta_time.game_dev.physics.vector.Vector;
import com.jordanbunke.delta_time.game_dev.ecs.GameEntity;

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
