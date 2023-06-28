package com.jordanbunke.delta_time.events;

public final class GameWindowEvent extends GameEvent {
    public final Action action;

    public enum Action {
        OPENED, CLOSING, CLOSED, ICONIFIED, DEICONIFIED, ACTIVATED, DEACTIVATED
    }

    public GameWindowEvent(final Action action) {
        super();
        this.action = action;
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof GameWindowEvent that && this.action == that.action;
    }

    @Override
    public int hashCode() {
        return action.ordinal();
    }
}
