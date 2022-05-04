package com.jordanbunke.jbjgl.events;

import static com.jordanbunke.jbjgl.utility.RenderConstants.*;

public class JBJGLMouseEvent extends JBJGLEvent {
    private final int[] mousePosition;
    private final Action action;

    public enum Action {
        DOWN, UP, CLICK
    }

    private JBJGLMouseEvent(final int[] mousePosition, final Action action) {
        super();
        this.mousePosition = mousePosition;
        this.action = action;
    }

    public static JBJGLMouseEvent generate(final int[] mouseLocation, final Action action) {
        return new JBJGLMouseEvent(mouseLocation, action);
    }

    public boolean matchesAction(final Action action) {
        return this.action == action;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof JBJGLMouseEvent ome))
            return false;
        return this.mousePosition[X] == ome.mousePosition[X] &&
                this.mousePosition[Y] == ome.mousePosition[Y] && this.action == ome.action;
    }

    @Override
    public int hashCode() {
        return (1000 * mousePosition[X]) + mousePosition[Y];
    }
}
