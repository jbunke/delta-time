package com.jordanbunke.jbjgl.events;

import static com.jordanbunke.jbjgl.utility.RenderConstants.*;

public class GameMouseMoveEvent extends GameEvent {
    private final int[] mousePosition;
    private final Action action;

    public enum Action {
        MOVE, DRAG, ENTER, EXIT
    }

    public GameMouseMoveEvent(final int[] mousePosition, final Action action) {
        super();
        this.mousePosition = mousePosition;
        this.action = action;
    }

    public boolean matchesAction(final Action action) {
        return this.action == action;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof GameMouseMoveEvent ome))
            return false;
        return this.mousePosition[X] == ome.mousePosition[X] &&
                this.mousePosition[Y] == ome.mousePosition[Y] && this.action == ome.action;
    }

    @Override
    public int hashCode() {
        return (1000 * mousePosition[X]) + mousePosition[Y];
    }

}
