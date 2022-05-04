package com.jordanbunke.jbjgl.events;

public class JBJGLWindowEvent extends JBJGLEvent {
    private final Action action;

    public enum Action {
        OPENED, CLOSING, CLOSED, ICONIFIED, DEICONIFIED, ACTIVATED, DEACTIVATED
    }

    private JBJGLWindowEvent(final Action action) {
        super();
        this.action = action;
    }

    public static JBJGLWindowEvent generate(final Action action) {
        return new JBJGLWindowEvent(action);
    }

    public boolean matchesAction(final Action action) {
        return this.action == action;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof JBJGLWindowEvent owe))
            return false;
        return this.action == owe.action;
    }

    @Override
    public int hashCode() {
        return action.ordinal();
    }
}
