package com.jordanbunke.jbjgl.events;

public class JBJGLMoveEvent extends JBJGLEvent {
    private final int[] mouseLocation;
    private final Action action;

    public enum Action {
        MOVE, DRAG, ENTER, EXIT
    }

    private JBJGLMoveEvent(final int[] mouseLocation, final Action action) {
        super();
        this.mouseLocation = mouseLocation;
        this.action = action;
    }

    public static JBJGLMoveEvent generate(final int[] mouseLocation, final Action action) {
        return new JBJGLMoveEvent(mouseLocation, action);
    }
}
