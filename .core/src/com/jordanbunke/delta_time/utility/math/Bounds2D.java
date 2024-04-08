package com.jordanbunke.delta_time.utility.math;

public record Bounds2D(int width, int height) {
    public Bounds2D {
        if (width <= 0 || height <= 0)
            throw new IllegalArgumentException();
    }
}
