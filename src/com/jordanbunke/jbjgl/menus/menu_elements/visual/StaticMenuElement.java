package com.jordanbunke.jbjgl.menus.menu_elements.visual;

import com.jordanbunke.jbjgl.debug.GameDebugger;
import com.jordanbunke.jbjgl.image.GameImage;
import com.jordanbunke.jbjgl.io.InputEventLogger;
import com.jordanbunke.jbjgl.menus.menu_elements.MenuElement;
import com.jordanbunke.jbjgl.text.Text;
import com.jordanbunke.jbjgl.utility.Coord2D;

public class StaticMenuElement extends MenuElement {
    private final GameImage image;

    public StaticMenuElement(
            final Coord2D position, final Coord2D dimensions, final Anchor anchor,
            final GameImage image
    ) {
        super(position, dimensions, anchor, true);

        this.image = image;
    }

    public StaticMenuElement(
            final Coord2D position, final Anchor anchor, final GameImage image
    ) {
        this(position, new Coord2D(image.getWidth(), image.getHeight()), anchor, image);
    }

    public static StaticMenuElement fromText(
            final Coord2D position, final Anchor anchor, final Text text
    ) {
        final GameImage image = text.draw();
        final Coord2D dimensions = new Coord2D(image.getWidth(), image.getHeight());
        return new StaticMenuElement(position, dimensions, anchor, text.draw());
    }

    @Override
    public void update(final double deltaTime) {

    }

    @Override
    public void render(final GameImage canvas) {
        draw(image, canvas);
    }

    @Override
    public void debugRender(final GameImage canvas, final GameDebugger debugger) {
        renderBoundingBox(canvas, debugger);
    }

    @Override
    public void process(final InputEventLogger eventLogger) {

    }
}
