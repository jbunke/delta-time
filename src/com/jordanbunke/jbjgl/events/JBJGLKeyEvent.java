package com.jordanbunke.jbjgl.events;

public class JBJGLKeyEvent extends JBJGLEvent {
    private final JBJGLKey key;
    private final Action action;

    public enum Action {
        PRESS, RELEASE, TYPE
    }

    private JBJGLKeyEvent(final JBJGLKey key, final Action action) {
        super();
        this.key = key;
        this.action = action;
    }

    public static JBJGLKeyEvent generate(final JBJGLKey key, final Action action) {
        return new JBJGLKeyEvent(key, action);
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

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof JBJGLKeyEvent oke))
            return false;
        return this.key == oke.key && this.action == oke.action;
    }

    @Override
    public int hashCode() {
        return key.ordinal() * action.ordinal();
    }
}
