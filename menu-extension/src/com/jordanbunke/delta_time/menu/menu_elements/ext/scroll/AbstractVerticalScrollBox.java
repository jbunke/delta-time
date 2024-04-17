package com.jordanbunke.delta_time.menu.menu_elements.ext.scroll;

import com.jordanbunke.delta_time.menu.menu_elements.ext.drawing_functions.ScrollBoxDrawingFunction;
import com.jordanbunke.delta_time.utility.math.Bounds2D;
import com.jordanbunke.delta_time.utility.math.Coord2D;

public abstract class AbstractVerticalScrollBox extends ScrollBox {
    private int offsetY;

    private final AbstractSlider slider;

    public AbstractVerticalScrollBox(
            final Coord2D position, final Bounds2D dimensions,
            final Scrollable[] menuElements,
            final ScrollBoxDrawingFunction fDraw,
            final int pixelsPerScrollClick,
            final int realBottomY, final int initialOffsetY

    ) {
        super(position, dimensions, menuElements, pixelsPerScrollClick, fDraw);

        final int minOffsetY = 0, maxOffsetY = (realBottomY - getY()) - getHeight();

        offsetY = -Math.max(minOffsetY, Math.min(initialOffsetY, maxOffsetY));

        final boolean canScroll = realBottomY > position.y + dimensions.height();
        slider = canScroll ? makeSlider(maxOffsetY) : null;
    }

    protected abstract AbstractSlider makeSlider(final int maxOffsetY);

    public void setOffsetY(final int offsetY) {
        this.offsetY = offsetY;
    }

    @Override
    protected Coord2D getOffset() {
        return new Coord2D(0, offsetY);
    }

    @Override
    protected boolean renderAndProcessChild(final Scrollable child) {
        final Coord2D rp = getRenderPosition(), childRP = child.getRenderPosition();
        final int h = getHeight(), childH = child.getHeight();

        return rp.y <= childRP.y && rp.y + h >= childRP.y + childH;
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
