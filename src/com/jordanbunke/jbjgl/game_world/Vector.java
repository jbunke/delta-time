package com.jordanbunke.jbjgl.game_world;

public abstract class Vector<E extends Vector<E>> {
    static final double DIM_ORIGIN = 0d;

    public abstract E displace(final double... dimVectors);
    public abstract E displace(final E displacement);
    public abstract E scale(final double factor);
}
