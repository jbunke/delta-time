package com.jordanbunke.jbjgl.game_world.physics.collision;

import com.jordanbunke.jbjgl.game_world.physics.vector.Vector;

/**
 * AABB stands for axis-aligned bounding box
 * */
public class AABB<E extends Vector<E>> {
    public final E dimensions, offset;

    public AABB(final E dimensions, final E offset) {
        this.dimensions = dimensions;
        this.offset = offset;
    }

    public AABB(final E dimensions) {
        this(dimensions, dimensions.scale(-0.5));
    }

    public E start(final E position) {
        return position.displace(offset);
    }

    public E end(final E position) {
        return start(position).displace(dimensions);
    }
}