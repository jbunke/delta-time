package com.jordanbunke.jbjgl.game_world;

public final class Coord2D {
    public final int x, y;

    public Coord2D(final int x, final int y) {
        this.x = x;
        this.y = y;
    }

    public static double unitDistanceBetween(final Coord2D a, final Coord2D b) {
        return Math.sqrt(
                Math.pow(Math.abs(a.x - b.x), 2) +
                Math.pow(Math.abs(a.y - b.y), 2)
        );
    }

    @Override
    public String toString() {
        return "x: " + x + ", y: " + y;
    }
}
