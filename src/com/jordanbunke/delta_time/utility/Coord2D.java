package com.jordanbunke.delta_time.utility;

public final class Coord2D {
    public final int x, y;

    public Coord2D(final int x, final int y) {
        this.x = x;
        this.y = y;
    }

    public Coord2D() {
        this(0, 0);
    }

    public static double unitDistanceBetween(final Coord2D a, final Coord2D b) {
        return Math.sqrt(
                Math.pow(Math.abs(a.x - b.x), 2) +
                Math.pow(Math.abs(a.y - b.y), 2)
        );
    }

    public Coord2D displace(final Coord2D displacement) {
        return displace(displacement.x, displacement.y);
    }

    public Coord2D displace(final int deltaX, final int deltaY) {
        return new Coord2D(x + deltaX, y + deltaY);
    }

    @Override
    public boolean equals(final Object o) {
        if (o instanceof Coord2D that)
            return this.x == that.x && this.y == that.y;
        return false;
    }

    @Override
    public int hashCode() {
        return x + y;
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}
