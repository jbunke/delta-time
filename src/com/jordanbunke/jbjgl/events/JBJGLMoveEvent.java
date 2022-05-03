package com.jordanbunke.jbjgl.events;

public class JBJGLMoveEvent extends JBJGLEvent {
    private final static int X = 0, Y = 1;

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

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof JBJGLMoveEvent ome))
            return false;
        return this.mouseLocation[X] == ome.mouseLocation[X] &&
                this.mouseLocation[Y] == ome.mouseLocation[Y] && this.action == ome.action;
    }

    @Override
    public int hashCode() {
        return (1000 * mouseLocation[X]) + mouseLocation[Y];
    }

}
