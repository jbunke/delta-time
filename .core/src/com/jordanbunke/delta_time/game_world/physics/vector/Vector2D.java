package com.jordanbunke.delta_time.game_world.physics.vector;

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
        return new Vector2D(x + displacement.x, y + displacement.y);
    }

    @Override
    public Vector2D maxOfAxes(final Vector2D that) {
        return new Vector2D(
                Math.max(this.x, that.x),
                Math.max(this.y, that.y)
        );
    }

    @Override
    public Vector2D minOfAxes(final Vector2D that) {
        return new Vector2D(
                Math.min(this.x, that.x),
                Math.min(this.y, that.y)
        );
    }

    @Override
    public Vector2D normalize() {
        if (equals(new Vector2D()))
            return this;

        return scale(1 / magnitude());
    }

    public Vector2D scale(final double factor) {
        return new Vector2D(x * factor, y * factor);
    }

    @Override
    public double magnitude() {
        return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
    }

    @Override
    public double getAxisValue(final int index) {
        return switch (index) {
            case 0 -> x;
            case 1 -> y;
            default -> Double.NaN;
        };
    }

    @Override
    public boolean equals(final Vector2D that) {
        return that != null && this.x == that.x && this.y == that.y;
    }

    @Override
    public String toString() {
        return "x: " + x + ", y: " + y;
    }
}
