package com.jordanbunke.jbjgl.menus.menu_elements.button;

import com.jordanbunke.jbjgl.events.GameEvent;
import com.jordanbunke.jbjgl.events.GameMouseEvent;
import com.jordanbunke.jbjgl.io.InputEventLogger;
import com.jordanbunke.jbjgl.menus.menu_elements.SelectableMenuElement;

import java.util.List;

public abstract class MenuButton extends SelectableMenuElement {
    private final Runnable chosenBehaviour;
    private boolean highlighted;

    public MenuButton(
            final int[] position, final int[] dimensions,
            final Anchor anchor, final boolean isVisible,
            final Runnable chosenBehaviour
    ) {
        super(position, dimensions, anchor, isVisible);

        this.chosenBehaviour = chosenBehaviour;
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
                chosenBehaviour.run();
                return;
            }
        }
    }

    @Override
    public final void choose() {
        chosenBehaviour.run();
    }

    public void setHighlighted(final boolean highlighted) {
        this.highlighted = highlighted;
    }

    public boolean isHighlighted() {
        return highlighted;
    }
}
