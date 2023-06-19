package com.jordanbunke.jbjgl.events;

public class GameWindowEvent extends GameEvent {
    private final Action action;

    public enum Action {
        OPENED, CLOSING, CLOSED, ICONIFIED, DEICONIFIED, ACTIVATED, DEACTIVATED
    }

    public GameWindowEvent(final Action action) {
        super();
        this.action = action;
    }

    public static GameWindowEvent generate(final Action action) {
        return new GameWindowEvent(action);
    }

    public boolean matchesAction(final Action action) {
        return this.action == action;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof GameWindowEvent owe))
            return false;
        return this.action == owe.action;
    }

    @Override
    public int hashCode() {
        return action.ordinal();
    }
}
