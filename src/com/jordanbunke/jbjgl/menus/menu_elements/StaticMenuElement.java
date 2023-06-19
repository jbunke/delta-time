package com.jordanbunke.jbjgl.menus.menu_elements;

import com.jordanbunke.jbjgl.debug.GameDebugger;
import com.jordanbunke.jbjgl.image.GameImage;
import com.jordanbunke.jbjgl.io.InputEventLogger;

import java.awt.*;

public class StaticMenuElement extends MenuElement {
    private final GameImage image;

    private StaticMenuElement(
            final int[] position, final int[] dimensions, final Anchor anchor,
            final GameImage image
    ) {
        super(position, dimensions, anchor, true);

        this.image = image;
    }

    public static StaticMenuElement generate(
            final int[] position, final Anchor anchor, final GameImage image
    ) {
        return new StaticMenuElement(
                position, new int[] { image.getWidth(), image.getHeight() },
                anchor, image
        );
    }

    @Override
    public void update(final double deltaTime) {

    }

    @Override
    public void render(final Graphics2D g) {
        draw(image, g);
    }

    @Override
    public void debugRender(final Graphics2D g, final GameDebugger debugger) {
        renderBoundingBox(g, debugger);
    }

    @Override
    public void process(final InputEventLogger eventLogger) {

    }
}
