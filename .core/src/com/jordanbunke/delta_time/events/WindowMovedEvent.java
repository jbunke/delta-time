package com.jordanbunke.delta_time.events;

public final class WindowMovedEvent extends GameEvent {
    public final int x, y;

    public WindowMovedEvent(final int x, final int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(final Object o) {
        return o instanceof WindowMovedEvent e && x == e.x && y == e.y;
    }

    @Override
    public int hashCode() {
        return 0;
    }
}
