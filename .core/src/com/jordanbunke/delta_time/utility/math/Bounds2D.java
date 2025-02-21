package com.jordanbunke.delta_time.utility.math;

public record Bounds2D(int width, int height) {
    public Bounds2D {
        if (width <= 0 || height <= 0)
            throw new IllegalArgumentException();
    }

    public Bounds2D augment(final int deltaW, final int deltaH) {
        return new Bounds2D(width + deltaW, height + deltaH);
    }

    @Override
    public boolean equals(final Object o) {
        return o instanceof Bounds2D that &&
                this.width == that.width && this.height == that.height;
    }

    @Override
    public int hashCode() {
        return width + height;
    }
}
