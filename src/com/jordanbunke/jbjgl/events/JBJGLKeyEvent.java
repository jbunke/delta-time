package com.jordanbunke.jbjgl.events;

public class JBJGLKeyEvent extends JBJGLEvent {
    private final char keyChar;
    private final Action action;

    public enum Action {
        PRESS, RELEASE, TYPE
    }

    private JBJGLKeyEvent(final char keyChar, final Action action) {
        super();
        this.keyChar = keyChar;
        this.action = action;
    }

    public static JBJGLKeyEvent generate(final char keyChar, final Action action) {
        return new JBJGLKeyEvent(keyChar, action);
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof JBJGLKeyEvent oke))
            return false;
        return this.keyChar == oke.keyChar && this.action == oke.action;
    }

    @Override
    public int hashCode() {
        return keyChar * action.ordinal();
    }

}
