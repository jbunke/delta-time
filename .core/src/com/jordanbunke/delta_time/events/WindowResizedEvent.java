package com.jordanbunke.delta_time.events;

public final class WindowResizedEvent extends GameEvent {
    public final int width, height;

    public WindowResizedEvent(final int width, final int height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public boolean equals(final Object o) {
        return o instanceof WindowResizedEvent e &&
                width == e.width && height == e.height;
    }

    @Override
    public int hashCode() {
        return width + height;
    }
}
