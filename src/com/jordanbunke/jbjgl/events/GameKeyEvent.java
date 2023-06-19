package com.jordanbunke.jbjgl.events;

public class GameKeyEvent extends GameEvent {
    private final Key key;
    private final char character;
    private final Action action;

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

    public Key getKey() {
        return key;
    }

    public Action getAction() {
        return action;
    }

    public char getCharacter() {
        return character;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof GameKeyEvent oke))
            return false;
        return this.key == oke.key && this.action == oke.action && this.character == oke.character;
    }

    @Override
    public int hashCode() {
        return key.ordinal() * action.ordinal();
    }
}
