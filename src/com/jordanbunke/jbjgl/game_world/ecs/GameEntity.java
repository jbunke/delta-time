package com.jordanbunke.jbjgl.game_world.ecs;

import com.jordanbunke.jbjgl.game_world.ecs.basic_components.EntityComponent;
import com.jordanbunke.jbjgl.game_world.object.GameObject;
import com.jordanbunke.jbjgl.game_world.physics.vector.Vector;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Consumer;

public class GameEntity<E extends Vector<E>> extends GameObject<E> {
    private boolean started, locked;

    private final Set<EntityComponent<E>> components;

    public GameEntity(final E position) {
        super(position);
        this.components = new HashSet<>();

        started = false;
        locked = false;
    }

    public <C extends EntityComponent<E>> C getComponent(final Class<C> componentClass) {
        final List<EntityComponent<E>> matches = components.stream()
                .filter(x -> componentClass.isAssignableFrom(x.getClass())).toList();

        if (matches.size() != 1)
            return null;

        return componentClass.cast(matches.get(0));
    }

    public <C extends EntityComponent<E>> Optional<C> getComponentIfPresent(final Class<C> componentClass) {
        return Optional.ofNullable(getComponent(componentClass));
    }

    public <C extends EntityComponent<E>> boolean hasComponent(final Class<C> componentClass) {
        return getComponentIfPresent(componentClass).isPresent();
    }

    public <C extends EntityComponent<E>> boolean executeIfComponentPresent(
            final Class<C> componentClass, final Consumer<C> componentInstruction
    ) {
        final Optional<C> component = getComponentIfPresent(componentClass);

        if (component.isEmpty())
            return false;

        componentInstruction.accept(component.get());
        return true;
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
