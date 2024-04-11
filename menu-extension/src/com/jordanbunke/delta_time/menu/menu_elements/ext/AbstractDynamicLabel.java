package com.jordanbunke.delta_time.menu.menu_elements.ext;

import com.jordanbunke.delta_time.debug.GameDebugger;
import com.jordanbunke.delta_time.image.GameImage;
import com.jordanbunke.delta_time.io.InputEventLogger;
import com.jordanbunke.delta_time.menu.menu_elements.MenuElement;
import com.jordanbunke.delta_time.menu.menu_elements.ext.drawing_functions.LabelDrawingFunction;
import com.jordanbunke.delta_time.utility.math.Coord2D;

import java.awt.*;
import java.util.function.Supplier;

public abstract class AbstractDynamicLabel extends MenuElement {
    private final Supplier<String> getter;

    private final LabelDrawingFunction fDraw;
    private final Color textColor;

    private String text;
    private GameImage labelImage;

    public AbstractDynamicLabel(
            final Coord2D position,
            final Coord2D dimensions,
            final Anchor anchor,
            final Color textColor,
            final Supplier<String> getter,
            final LabelDrawingFunction fDraw
    ) {
        super(position, dimensions, anchor, true);

        this.fDraw = fDraw;
        this.textColor = textColor;

        this.getter = getter;
        text = getter.get();

        updateAssets();
    }

    public AbstractDynamicLabel(
            final Coord2D position,
            final String widestCase,
            final int height,
            final Anchor anchor,
            final Color textColor,
            final Supplier<String> getter,
            final LabelDrawingFunction fDraw
    ) {
        this(position, new Coord2D(
                fDraw.draw(widestCase, textColor).getWidth(),
                height), anchor, textColor, getter, fDraw);
    }

    private void updateAssets() {
        final GameImage l = fDraw.draw(text, textColor);
        labelImage = new GameImage(getWidth(), getHeight());

        final Coord2D offset = switch (getAnchor()) {
            case RIGHT_TOP, RIGHT_CENTRAL, RIGHT_BOTTOM ->
                    new Coord2D((labelImage.getWidth() - l.getWidth()), 0);
            case CENTRAL, CENTRAL_BOTTOM, CENTRAL_TOP ->
                    new Coord2D((labelImage.getWidth() - l.getWidth()) / 2, 0);
            default -> new Coord2D();
        };

        labelImage.draw(l, offset.x, offset.y);

        labelImage.free();
    }

    @Override
    public void process(final InputEventLogger eventLogger) {

    }

    @Override
    public void update(final double deltaTime) {
        final String fetched = getter.get();

        if (!text.equals(fetched)) {
            text = fetched;
            updateAssets();
        }
    }

    @Override
    public void render(final GameImage canvas) {
        draw(labelImage, canvas);
    }

    @Override
    public void debugRender(final GameImage canvas, final GameDebugger debugger) {

    }
}
