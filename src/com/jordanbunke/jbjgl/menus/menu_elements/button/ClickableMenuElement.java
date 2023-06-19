package com.jordanbunke.jbjgl.menus.menu_elements.button;

import com.jordanbunke.jbjgl.contexts.JBJGLMenuManager;
import com.jordanbunke.jbjgl.debug.JBJGLGameDebugger;
import com.jordanbunke.jbjgl.events.JBJGLEvent;
import com.jordanbunke.jbjgl.events.JBJGLMouseEvent;
import com.jordanbunke.jbjgl.image.GameImage;
import com.jordanbunke.jbjgl.io.JBJGLListener;
import com.jordanbunke.jbjgl.menus.menu_elements.MenuElement;

import java.awt.*;
import java.util.List;

/**
 * Behaviour essentially replaced by {@link SimpleMenuButton}
 * */
@Deprecated(since = "0.1.1.38")
public class ClickableMenuElement extends MenuElement {

    private boolean isHighlighted;
    private final GameImage nonHighlightedImage, highlightedImage;
    private final Runnable onClickBehaviour;

    private ClickableMenuElement(
            final int[] position, final int[] dimensions, final Anchor anchor,
            final GameImage nonHighlightedImage, final GameImage highlightedImage,
            final Runnable onClickBehaviour
    ) {
        super(position, dimensions, anchor, true);

        this.nonHighlightedImage = nonHighlightedImage;
        this.highlightedImage = highlightedImage;
        this.onClickBehaviour = onClickBehaviour;
    }

    public static ClickableMenuElement generate(
            final int[] position, final int[] dimensions, final Anchor anchor,
            final GameImage nonHighlightedImage, final GameImage highlightedImage,
            final Runnable onClickBehaviour
    ) {
        return new ClickableMenuElement(position, dimensions, anchor,
                nonHighlightedImage, highlightedImage,
                onClickBehaviour);
    }

    @Override
    public void update() {

    }

    @Override
    public void render(final Graphics g, final JBJGLGameDebugger debugger) {
        draw(isHighlighted ? highlightedImage : nonHighlightedImage, g);

        // Debug
        renderBoundingBox(g, debugger);
    }

    @Override
    public void process(final JBJGLListener listener, final JBJGLMenuManager menuManager) {
        isHighlighted = mouseIsWithinBounds(listener.getMousePosition());

        if (!isHighlighted)
            return;

        final List<JBJGLEvent> unprocessed = listener.getUnprocessedEvents();
        for (JBJGLEvent e : unprocessed) {
            if (e instanceof JBJGLMouseEvent mouseEvent &&
                    mouseEvent.matchesAction(JBJGLMouseEvent.Action.CLICK)) {
                mouseEvent.markAsProcessed();

                onClickBehaviour.run();

                isHighlighted = false;
            }
        }
    }
}
