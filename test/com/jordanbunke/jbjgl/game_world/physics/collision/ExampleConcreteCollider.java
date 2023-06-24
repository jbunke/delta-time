package com.jordanbunke.jbjgl.game_world.physics.collision;

import com.jordanbunke.jbjgl.game_world.physics.vector.Vector;

public class ExampleConcreteCollider<E extends Vector<E>> extends Collider<E> {
    private final String name;
    private E position;

    @SafeVarargs
    public ExampleConcreteCollider(final String name, final E position, final AABB<E>... boundingBoxes) {
        super(boundingBoxes);

        this.name = name;
        this.position = position;
    }

    @Override
    public E getPosition() {
        return position;
    }

    public void move(final E displacement) {
        position = position.displace(displacement);
    }

    public void setPosition(final E position) {
        this.position = position;
    }

    @Override
    public String toString() {
        return name + ":[" + position + "]";
    }
}
