package com.jordanbunke.delta_time.events;

import com.jordanbunke.delta_time.utility.Coord2D;

public final class GameMouseEvent extends GameEvent {
    public final Coord2D mousePosition;
    public final Action action;

    public enum Action {
        DOWN, UP, CLICK
    }

    public GameMouseEvent(final Coord2D mousePosition, final Action action) {
        super();
        this.mousePosition = mousePosition;
        this.action = action;
    }

    public boolean matchesAction(final Action action) {
        return this.action == action;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof GameMouseEvent that)
            return this.mousePosition == that.mousePosition && this.action == that.action;
        return false;
    }

    @Override
    public int hashCode() {
        return (1000 * mousePosition.x) + mousePosition.y;
    }
}
