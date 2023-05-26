package com.jordanbunke.jbjgl.menus.menu_elements.button;

import com.jordanbunke.jbjgl.debug.JBJGLGameDebugger;
import com.jordanbunke.jbjgl.image.JBJGLImage;

import java.awt.*;

public class JBJGLSimpleMenuButton extends JBJGLMenuButton {
    private final JBJGLImage nonHighlightedImage, highlightedImage;

    private JBJGLSimpleMenuButton(
            final int[] position, final int[] dimensions,
            final Anchor anchor, final boolean isVisible,
            final Runnable chosenBehaviour,
            final JBJGLImage nonHighlightedImage,
            final JBJGLImage highlightedImage
    ) {
        super(position, dimensions, anchor, isVisible, chosenBehaviour);

        this.nonHighlightedImage = nonHighlightedImage;
        this.highlightedImage = highlightedImage;
    }

    public static JBJGLSimpleMenuButton generate(
            final int[] position, final int[] dimensions,
            final Anchor anchor, final boolean isVisible,
            final Runnable chosenBehaviour,
            final JBJGLImage nonHighlightedImage,
            final JBJGLImage highlightedImage
    ) {
        return new JBJGLSimpleMenuButton(
                position, dimensions, anchor, isVisible, chosenBehaviour,
                nonHighlightedImage, highlightedImage);
    }

    @Override
    public void update() {

    }

    @Override
    public void render(final Graphics g, final JBJGLGameDebugger debugger) {
        draw(isHighlighted() ? highlightedImage : nonHighlightedImage, g);

        renderBoundingBox(g, debugger);
    }
}
