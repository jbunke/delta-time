package com.jordanbunke.jbjgl.game_world.ecs;

import com.jordanbunke.jbjgl.game_world.Vector;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GameEntity<E extends Vector<E>> {
    private boolean started, locked;

    private E position;
    private final Set<EntityComponent<E>> components;

    public GameEntity(final E position) {
        this.position = position;
        this.components = new HashSet<>();

        started = false;
        locked = false;
    }

    public E getPosition() {
        return position;
    }

    public void setPosition(final E position) {
        this.position = position;
    }

    public void move(final E displacement) {
        position = position.displace(displacement);
    }

    public <C extends EntityComponent<E>> C getComponent(final Class<C> componentClass) {
        final List<EntityComponent<E>> matches = components.stream()
                .filter(x -> componentClass.isAssignableFrom(x.getClass())).toList();

        if (matches.size() != 1)
            return null;

        return componentClass.cast(matches.get(0));
    }

    public <C extends EntityComponent<E>> boolean hasComponent(final Class<C> componentClass) {
        return getComponent(componentClass) != null;
    }

    public void addComponent(final EntityComponent<E> toAdd) {
        if (!locked) {
            components.add(toAdd);
            toAdd.setEntity(this);
        }

        if (started)
            toAdd.start();
    }

    public void lock() {
        locked = true;
    }

    public void start() {
        if (started)
            return;

        components.forEach(EntityComponent::start);

        started = true;
    }

    public void update(final double deltaTime) {
        components.forEach(x -> x.update(deltaTime));
    }
}