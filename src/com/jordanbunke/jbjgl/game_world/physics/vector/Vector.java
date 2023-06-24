package com.jordanbunke.jbjgl.game_world.physics.vector;

public abstract class Vector<E extends Vector<E>> {
    static final double DIM_ORIGIN = 0d;

    public Vector() {

    }
    public abstract E displace(final double... dimVectors);
    public abstract E displace(final E displacement);
    public abstract E normalize();
    public abstract E scale(final double factor);
    public abstract double magnitude();
    public abstract boolean equals(final E that);
}
