package com.jordanbunke.delta_time.game_dev.physics.vector;

public abstract class Vector<E extends Vector<E>> {
    static final double DIM_ORIGIN = 0d;

    public Vector() {

    }
    public abstract E displace(final double... dimVectors);
    public abstract E displace(final E displacement);
    public abstract E maxOfAxes(final E that);
    public abstract E minOfAxes(final E that);
    public abstract E normalize();
    public abstract E scale(final double factor);
    public abstract double magnitude();
    public abstract double getAxisValue(final int index);
    public abstract boolean equals(final E that);

    public static <E extends Vector<E>> E origin(final E reference) {
        if (reference instanceof Vector3D)
            return (E) new Vector3D();
        else
            return (E) new Vector2D();
    }
}
