package com.jordanbunke.jbjgl.game_world;

public final class Vector3D extends Vector {
    public final double x, y, z;

    public Vector3D(final double x, final double y, final double z) {
        super();
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Vector3D() {
        super();
        this.x = 0.0;
        this.y = 0.0;
        this.z = 0.0;
    }

    public Vector3D displace(final Vector3D other) {
        return displace(other.x, other.y, other.z);
    }

    public Vector3D displace(final double deltaX, final double deltaY, final double deltaZ) {
        return new Vector3D(x + deltaX, y + deltaY, z + deltaZ);
    }
}
