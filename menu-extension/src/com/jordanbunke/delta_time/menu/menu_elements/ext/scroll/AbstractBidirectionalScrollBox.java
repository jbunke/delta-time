package com.jordanbunke.delta_time.menu.menu_elements.ext.scroll;

import com.jordanbunke.delta_time.image.GameImage;
import com.jordanbunke.delta_time.io.InputEventLogger;
import com.jordanbunke.delta_time.menu.menu_elements.ext.drawing_functions.ScrollBoxDrawingFunction;
import com.jordanbunke.delta_time.utility.math.Bounds2D;
import com.jordanbunke.delta_time.utility.math.Coord2D;

public abstract class AbstractBidirectionalScrollBox extends ScrollBox {
    private Coord2D offset;

    private final AbstractSlider verticalSlider, horizontalSlider;

    public AbstractBidirectionalScrollBox(
            final Coord2D position,
            final Bounds2D dimensions,
            final Scrollable[] menuElements,
            final int pixelsPerScrollClick,
            final ScrollBoxDrawingFunction fDraw,
            final int realRightX, final int realBottomY,
            final int initialOffsetX, final int initialOffsetY
    ) {
        super(position, dimensions, menuElements, pixelsPerScrollClick, fDraw);

        final int minOffsetX = 0,
                minOffsetY = 0,
                maxOffsetX = (realRightX - getX()) - getWidth(),
                maxOffsetY = (realBottomY - getY()) - getHeight();

        final int offsetX = -Math.max(minOffsetX,
                Math.min(initialOffsetX, maxOffsetX)),
                offsetY = -Math.max(minOffsetY,
                        Math.min(initialOffsetY, maxOffsetY));

        offset = new Coord2D(offsetX, offsetY);

        final boolean canScrollX = realRightX > position.x + dimensions.width();
        horizontalSlider = canScrollX ? makeHorizontalSlider(maxOffsetX) : null;

        final boolean canScrollY = realBottomY > position.y + dimensions.height();
        verticalSlider = canScrollY ? makeVerticalSlider(maxOffsetY) : null;
    }

    protected abstract AbstractSlider makeHorizontalSlider(final int maxOffsetX);
    protected abstract AbstractSlider makeVerticalSlider(final int maxOffsetY);

    public void setOffsetX(final int offsetX) {
        offset = new Coord2D(offsetX, offset.y);
    }

    public void setOffsetY(final int offsetY) {
        offset = new Coord2D(offset.x, offsetY);
    }

    private boolean hasBothSliders() {
        return verticalSlider != null && horizontalSlider != null;
    }

    @Override
    public void render(final GameImage canvas) {
        super.render(canvas);

        if (hasBothSliders())
            horizontalSlider.render(canvas);
    }

    @Override
    public void process(final InputEventLogger eventLogger) {
        if (hasBothSliders())
            horizontalSlider.process(eventLogger);

        super.process(eventLogger);
    }

    @Override
    protected Coord2D getOffset() {
        return offset;
    }

    @Override
    protected boolean renderAndProcessChild(Scrollable child) {
        final Coord2D rp = getRenderPosition(), childRP = child.getRenderPosition();
        final int w = getWidth(), childW = child.getWidth(),
                h = getHeight(), childH = child.getHeight();

        return rp.x <= childRP.x && rp.x + w >= childRP.x + childW &&
                rp.y <= childRP.y && rp.y + h >= childRP.y + childH;
    }

    @Override
    boolean hasSlider() {
        return verticalSlider != null || horizontalSlider != null;
    }

    @Override
    AbstractSlider getSlider() {
        return verticalSlider != null ? verticalSlider : horizontalSlider;
    }
}
