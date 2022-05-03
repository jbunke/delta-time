package com.jordanbunke.jbjgl.events;

public class JBJGLMouseEvent extends JBJGLEvent {
    private final int[] mouseLocation;
    private final Action action;

    public enum Action {
        DOWN, UP, CLICK
    }

    private JBJGLMouseEvent(final int[] mouseLocation, final Action action) {
        super();
        this.mouseLocation = mouseLocation;
        this.action = action;
    }

    public static JBJGLMouseEvent generate(final int[] mouseLocation, final Action action) {
        return new JBJGLMouseEvent(mouseLocation, action);
    }
}
