package com.jordanbunke.delta_time.menus.menu_elements.button;

import com.jordanbunke.delta_time.events.GameEvent;
import com.jordanbunke.delta_time.events.GameMouseEvent;
import com.jordanbunke.delta_time.io.InputEventLogger;
import com.jordanbunke.delta_time.menus.menu_elements.SelectableMenuElement;
import com.jordanbunke.delta_time.utility.Coord2D;

import java.util.List;

public abstract class MenuButtonStub extends SelectableMenuElement {
    private boolean highlighted;

    public MenuButtonStub(
            final Coord2D position, final Coord2D dimensions,
            final Anchor anchor, final boolean visible
    ) {
        super(position, dimensions, anchor, visible);

        highlighted = false;
    }

    @Override
    public void process(final InputEventLogger eventLogger) {
        final boolean mouseInBounds = mouseIsWithinBounds(eventLogger.getAdjustedMousePosition());

        setHighlighted(isSelected() || mouseInBounds);

        if (!mouseInBounds)
            return;

        final List<GameEvent> unprocessed = eventLogger.getUnprocessedEvents();
        for (GameEvent e : unprocessed) {
            if (e instanceof GameMouseEvent mouseEvent &&
                    mouseEvent.matchesAction(GameMouseEvent.Action.CLICK)) {
                mouseEvent.markAsProcessed();
                execute();
                return;
            }
        }
    }

    public abstract void execute();

    @Override
    public final void choose() {
        execute();
    }

    public void setHighlighted(final boolean highlighted) {
        this.highlighted = highlighted;
    }

    public boolean isHighlighted() {
        return highlighted;
    }
}
