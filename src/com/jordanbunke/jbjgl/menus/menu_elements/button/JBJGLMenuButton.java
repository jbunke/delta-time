package com.jordanbunke.jbjgl.menus.menu_elements.button;

import com.jordanbunke.jbjgl.contexts.JBJGLMenuManager;
import com.jordanbunke.jbjgl.events.JBJGLEvent;
import com.jordanbunke.jbjgl.events.JBJGLMouseEvent;
import com.jordanbunke.jbjgl.io.JBJGLListener;
import com.jordanbunke.jbjgl.menus.menu_elements.JBJGLSelectableMenuElement;

import java.util.List;

public abstract class JBJGLMenuButton extends JBJGLSelectableMenuElement {
    private final Runnable chosenBehaviour;
    private boolean highlighted;

    public JBJGLMenuButton(
            final int[] position, final int[] dimensions,
            final Anchor anchor, final boolean isVisible,
            final Runnable chosenBehaviour
    ) {
        super(position, dimensions, anchor, isVisible);

        this.chosenBehaviour = chosenBehaviour;
        highlighted = false;
    }

    @Override
    public void process(final JBJGLListener listener, final JBJGLMenuManager menuManager) {
        final boolean mouseInBounds = mouseIsWithinBounds(listener.getAdjustedMousePosition());

        setHighlighted(isSelected() || mouseInBounds);

        if (!mouseInBounds)
            return;

        final List<JBJGLEvent> unprocessed = listener.getUnprocessedEvents();
        for (JBJGLEvent e : unprocessed) {
            if (e instanceof JBJGLMouseEvent mouseEvent &&
                    mouseEvent.matchesAction(JBJGLMouseEvent.Action.CLICK)) {
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
