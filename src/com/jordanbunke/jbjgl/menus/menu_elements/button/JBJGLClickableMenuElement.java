package com.jordanbunke.jbjgl.menus.menu_elements.button;

import com.jordanbunke.jbjgl.contexts.JBJGLMenuManager;
import com.jordanbunke.jbjgl.debug.JBJGLGameDebugger;
import com.jordanbunke.jbjgl.events.JBJGLEvent;
import com.jordanbunke.jbjgl.events.JBJGLMouseEvent;
import com.jordanbunke.jbjgl.image.JBJGLImage;
import com.jordanbunke.jbjgl.io.JBJGLListener;
import com.jordanbunke.jbjgl.menus.menu_elements.JBJGLMenuElement;

import java.awt.*;
import java.util.List;

/**
 * Behaviour essentially replaced by {@link JBJGLSimpleMenuButton}
 * */
@Deprecated(since = "0.1.1.38")
public class JBJGLClickableMenuElement extends JBJGLMenuElement {

    private boolean isHighlighted;
    private final JBJGLImage nonHighlightedImage, highlightedImage;
    private final Runnable onClickBehaviour;

    private JBJGLClickableMenuElement(
            final int[] position, final int[] dimensions, final Anchor anchor,
            final JBJGLImage nonHighlightedImage, final JBJGLImage highlightedImage,
            final Runnable onClickBehaviour
    ) {
        super(position, dimensions, anchor, true);

        this.nonHighlightedImage = nonHighlightedImage;
        this.highlightedImage = highlightedImage;
        this.onClickBehaviour = onClickBehaviour;
    }

    public static JBJGLClickableMenuElement generate(
            final int[] position, final int[] dimensions, final Anchor anchor,
            final JBJGLImage nonHighlightedImage, final JBJGLImage highlightedImage,
            final Runnable onClickBehaviour
    ) {
        return new JBJGLClickableMenuElement(position, dimensions, anchor,
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
