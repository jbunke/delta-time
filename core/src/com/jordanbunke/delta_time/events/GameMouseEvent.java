package com.jordanbunke.delta_time.events;

import com.jordanbunke.delta_time.utility.Coord2D;

public final class GameMouseEvent extends GameEvent {
    public final Coord2D mousePosition;
    public final Action action;
    public final Button button;

    public enum Action {
        DOWN, UP, CLICK
    }

    public enum Button {
        LEFT, RIGHT, MIDDLE;

        public static Button fromInt(final int button) {
            return switch (button) {
                case 2 -> MIDDLE;
                case 3 -> RIGHT;
                default -> LEFT;
            };
        }
    }

    public GameMouseEvent(
            final Coord2D mousePosition, final Action action,
            final Button button
    ) {
        super();
        this.mousePosition = mousePosition;
        this.action = action;
        this.button = button;
    }

    public boolean matchesAction(final Action action) {
        return this.action == action;
    }

    public boolean matchesButton(final Button button) {
        return this.button == button;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof GameMouseEvent that)
            return this.mousePosition == that.mousePosition &&
                    this.action == that.action &&
                    this.button == that.button;
        return false;
    }

    @Override
    public int hashCode() {
        return (4000 * mousePosition.x) + mousePosition.y;
    }
}
