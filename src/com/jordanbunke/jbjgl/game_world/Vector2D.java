package com.jordanbunke.jbjgl.game_world;

public final class Vector2D extends Vector<Vector2D> {
    public final double x, y;

    public Vector2D(final double... dimVectors) {
        final int X_INDEX = 0, Y_INDEX = 1;

        this.x = dimVectors.length > X_INDEX ? dimVectors[X_INDEX] : DIM_ORIGIN;
        this.y = dimVectors.length > Y_INDEX ? dimVectors[Y_INDEX] : DIM_ORIGIN;
    }

    public Vector2D() {
        super();
        this.x = DIM_ORIGIN;
        this.y = DIM_ORIGIN;
    }

    public Vector2D displace(final double... dimVectors) {
        return displace(new Vector2D(dimVectors));
    }

    public Vector2D displace(final Vector2D displacement) {
        return displace(displacement.x, displacement.y);
    }

    public Vector2D scale(final double factor) {
        return new Vector2D(x * factor, y * factor);
    }

    @Override
    public String toString() {
        return "x: " + x + ", y: " + y;
    }
}
