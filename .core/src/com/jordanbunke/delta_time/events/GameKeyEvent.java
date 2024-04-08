package com.jordanbunke.delta_time.events;

public final class GameKeyEvent extends GameEvent {
    public final Key key;
    public final char character;
    public final Action action;

    public enum Action {
        PRESS, RELEASE, TYPE
    }

    private GameKeyEvent(final Key key, final char character, final Action action) {
        super();
        this.key = key;
        this.character = character;
        this.action = action;
    }

    public static GameKeyEvent newKeyStroke(final Key key, final Action action) {
        return new GameKeyEvent(key, (char)0, action);
    }

    public static GameKeyEvent newTypedKey(final char character) {
        return new GameKeyEvent(Key.UNSUPPORTED, character, Action.TYPE);
    }

    public boolean matchesAction(final Action action) {
        return this.action == action;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof GameKeyEvent that)
            return this.key == that.key && this.action == that.action && this.character == that.character;
        return false;
    }

    @Override
    public int hashCode() {
        return key.ordinal() * action.ordinal();
    }
}
