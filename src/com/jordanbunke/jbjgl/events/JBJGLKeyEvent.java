package com.jordanbunke.jbjgl.events;

public class JBJGLKeyEvent extends JBJGLEvent {
    private final JBJGLKey key;
    private final char character;
    private final Action action;

    public enum Action {
        PRESS, RELEASE, TYPE
    }

    private JBJGLKeyEvent(final JBJGLKey key, final char character, final Action action) {
        super();
        this.key = key;
        this.character = character;
        this.action = action;
    }

    public static JBJGLKeyEvent generate(final JBJGLKey key, final Action action) {
        return new JBJGLKeyEvent(key, (char)0, action);
    }

    public static JBJGLKeyEvent generateTyped(final char character) {
        return new JBJGLKeyEvent(JBJGLKey.UNSUPPORTED, character, Action.TYPE);
    }

    public boolean matchesAction(final Action action) {
        return this.action == action;
    }

    public JBJGLKey getKey() {
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
        if (!(o instanceof JBJGLKeyEvent oke))
            return false;
        return this.key == oke.key && this.action == oke.action && this.character == oke.character;
    }

    @Override
    public int hashCode() {
        return key.ordinal() * action.ordinal();
    }
}
