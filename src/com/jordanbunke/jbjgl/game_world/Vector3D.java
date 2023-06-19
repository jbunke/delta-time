package com.jordanbunke.jbjgl.game_world;

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
        return displace(displacement.x, displacement.y, displacement.z);
    }

    @Override
    public Vector3D scale(final double factor) {
        return new Vector3D(x * factor, y * factor, z * factor);
    }

    @Override
    public String toString() {
        return "x: " + x + ", y: " + y + ", z: " + z;
    }
}
