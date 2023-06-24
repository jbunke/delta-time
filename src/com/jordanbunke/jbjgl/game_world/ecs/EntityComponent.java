package com.jordanbunke.jbjgl.game_world.ecs;

import com.jordanbunke.jbjgl.game_world.physics.vector.Vector;

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
