package com.jordanbunke.delta_time.game_dev.physics.vector;

public final class Vector3D extends Vector<Vector3D> {
    public final double x, y, z;

    public Vector3D(final double... dimVectors) {
        final int X_INDEX = 0, Y_INDEX = 1, Z_INDEX = 2;

        this.x = dimVectors.length > X_INDEX ? dimVectors[X_INDEX] : DIM_ORIGIN;
        this.y = dimVectors.length > Y_INDEX ? dimVectors[Y_INDEX] : DIM_ORIGIN;
        this.z = dimVectors.length > Z_INDEX ? dimVectors[Z_INDEX] : DIM_ORIGIN;
    }

    public Vector3D() {
        super();
        this.x = DIM_ORIGIN;
        this.y = DIM_ORIGIN;
        this.z = DIM_ORIGIN;
    }

    public Vector3D displace(final double... dimVectors) {
        return displace(new Vector3D(dimVectors));
    }

    public Vector3D displace(final Vector3D displacement) {
        return new Vector3D(x + displacement.x, y + displacement.y, z + displacement.z);
    }

    @Override
    public Vector3D maxOfAxes(final Vector3D that) {
        return new Vector3D(
                Math.max(this.x, that.x),
                Math.max(this.y, that.y),
                Math.max(this.z, that.z)
        );
    }

    @Override
    public Vector3D minOfAxes(final Vector3D that) {
        return new Vector3D(
                Math.min(this.x, that.x),
                Math.min(this.y, that.y),
                Math.min(this.z, that.z)
        );
    }

    @Override
    public Vector3D normalize() {
        if (equals(new Vector3D()))
            return this;

        return scale(1 / magnitude());
    }

    @Override
    public Vector3D scale(final double factor) {
        return new Vector3D(x * factor, y * factor, z * factor);
    }

    @Override
    public double magnitude() {
        return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2) + Math.pow(z, 2));
    }

    @Override
    public double getAxisValue(final int index) {
        return switch (index) {
            case 0 -> x;
            case 1 -> y;
            case 2 -> z;
            default -> Double.NaN;
        };
    }

    @Override
    public boolean equals(final Vector3D that) {
        return that != null && this.x == that.x && this.y == that.y && this.z == that.z;
    }

    @Override
    public String toString() {
        return "x: " + x + ", y: " + y + ", z: " + z;
    }
}
