package com.jordanbunke.jbjgl.menus.menu_elements.button;

import com.jordanbunke.jbjgl.debug.GameDebugger;
import com.jordanbunke.jbjgl.image.GameImage;
import com.jordanbunke.jbjgl.utility.Coord2D;

import java.awt.*;

public class SimpleMenuButton extends MenuButton {
    private final GameImage nonHighlightedImage, highlightedImage;

    public SimpleMenuButton(
            final Coord2D position, final Coord2D dimensions,
            final Anchor anchor, final boolean isVisible,
            final Runnable chosenBehaviour,
            final GameImage nonHighlightedImage,
            final GameImage highlightedImage
    ) {
        super(position, dimensions, anchor, isVisible, chosenBehaviour);

        this.nonHighlightedImage = nonHighlightedImage;
        this.highlightedImage = highlightedImage;
    }

    @Override
    public void update(final double deltaTime) {

    }

    @Override
    public void render(final Graphics2D g) {
        draw(isHighlighted() ? highlightedImage : nonHighlightedImage, g);
    }

    @Override
    public void debugRender(final Graphics2D g, final GameDebugger debugger) {
        renderBoundingBox(g, debugger);
    }
}
