package com.jordanbunke.delta_time.menu.menu_elements.button;

import com.jordanbunke.delta_time.debug.GameDebugger;
import com.jordanbunke.delta_time.image.GameImage;
import com.jordanbunke.delta_time.utility.math.Bounds2D;
import com.jordanbunke.delta_time.utility.math.Coord2D;

public class SimpleMenuButton extends MenuButton {
    private final GameImage nonHighlightedImage, highlightedImage;

    public SimpleMenuButton(
            final Coord2D position, final Bounds2D dimensions,
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
    public void render(final GameImage canvas) {
        draw(isHighlighted() ? highlightedImage : nonHighlightedImage, canvas);
    }

    @Override
    public void debugRender(final GameImage canvas, final GameDebugger debugger) {
        renderBoundingBox(canvas, debugger);
    }
}
