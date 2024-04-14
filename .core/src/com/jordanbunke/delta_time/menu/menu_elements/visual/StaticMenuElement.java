package com.jordanbunke.delta_time.menu.menu_elements.visual;

import com.jordanbunke.delta_time.debug.GameDebugger;
import com.jordanbunke.delta_time.image.GameImage;
import com.jordanbunke.delta_time.io.InputEventLogger;
import com.jordanbunke.delta_time.menu.menu_elements.MenuElement;
import com.jordanbunke.delta_time.text.Text;
import com.jordanbunke.delta_time.utility.math.Bounds2D;
import com.jordanbunke.delta_time.utility.math.Coord2D;

public class StaticMenuElement extends MenuElement {
    private final GameImage image;

    public StaticMenuElement(
            final Coord2D position, final Bounds2D dimensions, final Anchor anchor,
            final GameImage image
    ) {
        super(position, dimensions, anchor, true);

        this.image = image;
    }

    public StaticMenuElement(
            final Coord2D position, final Anchor anchor, final GameImage image
    ) {
        this(position, new Bounds2D(image.getWidth(), image.getHeight()), anchor, image);
    }

    public static StaticMenuElement fromText(
            final Coord2D position, final Anchor anchor, final Text text
    ) {
        final GameImage image = text.draw();

        return new StaticMenuElement(
                position, new Bounds2D(image.getWidth(), image.getHeight()),
                anchor, image);
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
