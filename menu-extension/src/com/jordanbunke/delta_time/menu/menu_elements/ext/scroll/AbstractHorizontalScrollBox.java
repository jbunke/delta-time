package com.jordanbunke.delta_time.menu.menu_elements.ext.scroll;

import com.jordanbunke.delta_time.menu.menu_elements.ext.drawing_functions.ScrollBoxDrawingFunction;
import com.jordanbunke.delta_time.utility.math.Bounds2D;
import com.jordanbunke.delta_time.utility.math.Coord2D;

public abstract class AbstractHorizontalScrollBox extends ScrollBox {
    private int offsetX;

    private final AbstractSlider slider;

    public AbstractHorizontalScrollBox(
            final Coord2D position, final Bounds2D dimensions,
            final Scrollable[] menuElements,
            final ScrollBoxDrawingFunction fDraw,
            final int pixelsPerScrollClick,
            final int realRightX, final int initialOffsetX

    ) {
        super(position, dimensions, menuElements, pixelsPerScrollClick, fDraw);

        final int minOffsetX = 0, maxOffsetX = (realRightX - getX()) - getWidth();

        offsetX = -Math.max(minOffsetX, Math.min(initialOffsetX, maxOffsetX));

        final boolean canScroll = realRightX > position.x + dimensions.width();
        slider = canScroll ? makeSlider(maxOffsetX) : null;
    }

    protected abstract AbstractSlider makeSlider(final int maxOffsetX);

    public void setOffsetX(final int offsetX) {
        this.offsetX = offsetX;
    }

    @Override
    protected Coord2D getOffset() {
        return new Coord2D(offsetX, 0);
    }

    @Override
    boolean renderAndProcessChild(final Scrollable child) {
        final Coord2D rp = getRenderPosition(), childRP = child.getRenderPosition();
        final int w = getWidth(), childW = child.getWidth();

        return rp.x <= childRP.x && rp.x + w >= childRP.x + childW;
    }

    @Override
    boolean hasSlider() {
        return slider != null;
    }

    @Override
    public AbstractSlider getSlider() {
        return slider;
    }
}
