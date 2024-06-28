package com.jordanbunke.delta_time.menu.menu_elements.ext.scroll;

import com.jordanbunke.delta_time.debug.GameDebugger;
import com.jordanbunke.delta_time.events.GameEvent;
import com.jordanbunke.delta_time.events.GameMouseScrollEvent;
import com.jordanbunke.delta_time.image.GameImage;
import com.jordanbunke.delta_time.io.InputEventLogger;
import com.jordanbunke.delta_time.menu.menu_elements.MenuElement;
import com.jordanbunke.delta_time.menu.menu_elements.container.MenuElementContainer;
import com.jordanbunke.delta_time.menu.menu_elements.ext.drawing_functions.ScrollBoxDrawingFunction;
import com.jordanbunke.delta_time.utility.math.Bounds2D;
import com.jordanbunke.delta_time.utility.math.Coord2D;

import java.util.List;

public abstract class ScrollBox extends MenuElementContainer {
    private final Scrollable[] menuElements;

    private final int pixelsPerScrollClick;

    // cosmetic
    private final GameImage background;
    private boolean renderBeyondBounds;

    public ScrollBox(
            final Coord2D position, final Bounds2D dimensions,
            final Scrollable[] menuElements,
            final int pixelsPerScrollClick,
            final ScrollBoxDrawingFunction fDraw
    ) {
        super(position, dimensions, Anchor.LEFT_TOP, true);

        this.pixelsPerScrollClick = pixelsPerScrollClick;

        this.menuElements = MenuElement
                .sortForRender(menuElements, Scrollable[]::new);

        background = fDraw.draw(getWidth(), getHeight());
        renderBeyondBounds = false;
    }

    @Override
    public void update(final double deltaTime) {
        final Coord2D offset = getOffset();

        for (Scrollable sme : menuElements) {
            sme.setPositionFromOffset(offset);
            sme.update(deltaTime);
        }
    }

    @Override
    public void process(final InputEventLogger eventLogger) {
        // process slider first
        if (hasSlider()) {
            final AbstractSlider slider = getSlider();

            slider.process(eventLogger);

            if (mouseIsWithinBounds(eventLogger.getAdjustedMousePosition())) {
                final List<GameEvent> up = eventLogger.getUnprocessedEvents();

                for (GameEvent e : up) {
                    if (e instanceof GameMouseScrollEvent mse) {
                        mse.markAsProcessed();

                        slider.incrementValue(mse.clicksScrolled * pixelsPerScrollClick);
                    }
                }
            }
        }

        for (Scrollable sme : menuElements)
            if (renderAndProcessChild(sme))
                sme.process(eventLogger);
    }

    @Override
    public void render(final GameImage canvas) {
        // background
        draw(background, canvas);

        final Scrollable[] renderOrder =
                MenuElement.sortForRender(menuElements,
                        Scrollable[]::new);

        final GameImage childCanvas =
                new GameImage(canvas.getWidth(), canvas.getHeight());

        // contents
        for (Scrollable sme : renderOrder)
            if (renderAndProcessChild(sme))
                sme.render(renderBeyondBounds ? canvas : childCanvas);

        if (!renderBeyondBounds) {
            final Coord2D rp = getRenderPosition();
            final int right = Math.min(rp.x + getWidth(), canvas.getWidth()),
                    bottom = Math.min(rp.y + getHeight(), canvas.getHeight());

            if (rp.x >= 0 && rp.x < canvas.getWidth() &&
                    rp.y >= 0 && rp.y < canvas.getHeight() &&
                    right > rp.x && bottom > rp.y)
                canvas.draw(childCanvas.section(rp.x, rp.y, right, bottom),
                        rp.x, rp.y);
        }

        if (hasSlider())
            getSlider().render(canvas);
    }

    @Override
    public void debugRender(final GameImage canvas, final GameDebugger debugger) {
        for (Scrollable sme : menuElements)
            if (renderAndProcessChild(sme))
                sme.debugRender(canvas, debugger);
    }

    @Override
    public Scrollable[] getMenuElements() {
        return menuElements;
    }

    @Override
    public boolean hasNonTrivialBehaviour() {
        return true;
    }

    protected abstract Coord2D getOffset();

    protected abstract boolean renderAndProcessChild(final Scrollable child);

    abstract boolean hasSlider();

    abstract AbstractSlider getSlider();

    public final void setRenderBeyondBounds(final boolean renderBeyondBounds) {
        this.renderBeyondBounds = renderBeyondBounds;
    }
}
