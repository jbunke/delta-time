package com.jordanbunke.jbjgl.menus.menu_elements;

import com.jordanbunke.jbjgl.contexts.JBJGLMenuManager;
import com.jordanbunke.jbjgl.debug.JBJGLGameDebugger;
import com.jordanbunke.jbjgl.events.JBJGLEvent;
import com.jordanbunke.jbjgl.events.JBJGLMouseEvent;
import com.jordanbunke.jbjgl.image.JBJGLImage;
import com.jordanbunke.jbjgl.io.JBJGLListener;

import java.awt.*;
import java.util.List;

public class JBJGLToggleClickableMenuElement extends JBJGLMenuElement {
    private boolean isHighlighted;
    private final JBJGLImage[] nonHighlightedImages;
    private final JBJGLImage[] highlightedImages;
    private final Runnable[] onClickBehaviours;

    private int index;
    private final int length;

    private JBJGLToggleClickableMenuElement(
            final int[] position, final int[] dimensions, final Anchor anchor,
            final JBJGLImage[] nonHighlightedImages, final JBJGLImage[] highlightedImages,
            final Runnable[] onClickBehaviours
    ) {
        super(position, dimensions, anchor, true);

        this.index = 0;
        this.length = nonHighlightedImages.length;

        this.nonHighlightedImages = nonHighlightedImages;
        this.highlightedImages = highlightedImages;
        this.onClickBehaviours = onClickBehaviours;
    }

    public static JBJGLToggleClickableMenuElement generate(
            final int[] position, final int[] dimensions, final Anchor anchor,
            final JBJGLImage[] nonHighlightedImages, final JBJGLImage[] highlightedImages,
            final Runnable[] onClickBehaviours
    ) {
        return new JBJGLToggleClickableMenuElement(
                position, dimensions, anchor,
                nonHighlightedImages, highlightedImages, onClickBehaviours
        );
    }

    @Override
    public void update() {

    }

    @Override
    public void render(final Graphics g, final JBJGLGameDebugger debugger) {
        draw(isHighlighted ? highlightedImages[index] : nonHighlightedImages[index], g);

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
                onClickBehaviours[index].run();
                index++;
                index %= length;

                isHighlighted = false;
            }
        }
    }
}
