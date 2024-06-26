package com.jordanbunke.delta_time.events;

import com.jordanbunke.delta_time.utility.math.Coord2D;

public final class GameMouseMoveEvent extends GameEvent {
    public final Coord2D mousePosition;
    public final Action action;

    public enum Action {
        MOVE, DRAG, ENTER, EXIT
    }

    public GameMouseMoveEvent(final Coord2D mousePosition, final Action action) {
        super();
        this.mousePosition = mousePosition;
        this.action = action;
    }

    public boolean matchesAction(final Action action) {
        return this.action == action;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof GameMouseMoveEvent that)
            return this.mousePosition == that.mousePosition && this.action == that.action;
        return false;
    }

    @Override
    public int hashCode() {
        return (1000 * mousePosition.x) + mousePosition.y;
    }

}
