package com.jordanbunke.delta_time.menu.menu_elements.ext.scroll;

import com.jordanbunke.delta_time.events.GameEvent;
import com.jordanbunke.delta_time.events.GameMouseEvent;
import com.jordanbunke.delta_time.image.GameImage;
import com.jordanbunke.delta_time.io.InputEventLogger;
import com.jordanbunke.delta_time.menu.menu_elements.MenuElement;
import com.jordanbunke.delta_time.utility.math.Coord2D;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;

public abstract class AbstractSlider extends MenuElement {
    public final int minValue, maxValue, sliderBallDim;
    private int value, onClickDiffDim;

    private final Supplier<Integer> getter;
    private final Consumer<Integer> setter;
    private final boolean canSetImplicitly;

    private boolean sliding, highlighted;

    public AbstractSlider(
            final Coord2D position, final Coord2D dimensions, final Anchor anchor,
            final int minValue, final int maxValue, final Supplier<Integer> getter,
            final Consumer<Integer> setter, final boolean canSetImplicitly,
            final int sliderBallDim
    ) {
        super(position, dimensions, anchor, true);

        this.minValue = minValue;
        this.maxValue = maxValue;
        this.sliderBallDim = sliderBallDim;

        this.getter = getter;
        value = this.getter.get();

        this.setter = setter;
        this.canSetImplicitly = canSetImplicitly;

        sliding = false;
        onClickDiffDim = 0;
    }

    @Override
    public void update(final double deltaTime) {
        if (!sliding)
            setValue(getter.get());
    }

    @Override
    public void render(final GameImage canvas) {
        final GameImage asset = isSliding()
                ? drawSliding()
                : (isHighlighted() ? drawHighlighted() : drawBasic());

        draw(asset, canvas);
    }

    protected abstract GameImage drawSliding();

    protected abstract GameImage drawHighlighted();

    protected abstract GameImage drawBasic();

    @Override
    public void process(final InputEventLogger eventLogger) {
        final Coord2D mp = eventLogger.getAdjustedMousePosition();
        final boolean mouseInBounds = mouseIsWithinBounds(mp);

        final int mpd = getCoordDimension(mp);
        highlighted = mouseInBounds && isValidDimForHighlight(mpd);

        final List<GameEvent> unprocessed = eventLogger.getUnprocessedEvents();
        for (GameEvent e : unprocessed) {
            if (e instanceof GameMouseEvent me) {
                switch (me.action) {
                    case DOWN -> {
                        if (mouseInBounds) {
                            me.markAsProcessed();
                            final boolean validDim = highlighted;

                            if (validDim) {
                                sliding = true;
                                // TODO: - 1 is a hotfix; investigate, understand,
                                //  and document solution methodology later
                                onClickDiffDim = (mpd - getSliderBallDim()) - 1;
                            }
                        }
                    }
                    case UP -> {
                        if (sliding) {
                            me.markAsProcessed();
                            sliding = false;
                        }
                    }
                }
            }
        }

        if (sliding) {
            final int sliderBallDim = mpd - onClickDiffDim, lastValue = value;
            setValueFromSliderBallDim(sliderBallDim);

            if (lastValue != value)
                setter.accept(value);
        }
    }

    // HELPER

    protected abstract int getCoordDimension(final Coord2D position);

    protected abstract int getSizeDimension();

    public double getSliderFraction() {
        return (value - minValue) / (double)(maxValue - minValue);
    }

    public int getValueFromSliderFraction(final double sliderFraction) {
        return minValue + (int)(sliderFraction * (maxValue - minValue));
    }

    public boolean isValidDimForHighlight(final int mouseDim) {
        return Math.abs(mouseDim - getSliderBallDim()) <= sliderBallDim / 2;
    }

    private int getSliderBallDim() {
        final Coord2D rp = getRenderPosition();
        return getCoordDimension(rp) + (sliderBallDim / 2) +
                (int)(getSliderFraction() *
                        (getSizeDimension() - sliderBallDim));
    }

    private void setValueFromSliderBallDim(final int sliderBallDim) {
        final int valueWas = getValue();

        final Coord2D rp = getRenderPosition();
        final int sd = getSizeDimension() - this.sliderBallDim,
                startDim = getCoordDimension(rp) + (this.sliderBallDim / 2);
        final double sliderFraction = (sliderBallDim - startDim) / (double) sd;

        final int prospect = getValueFromSliderFraction(sliderFraction);
        value = Math.max(minValue, Math.min(prospect, maxValue));

        if (getValue() != valueWas)
            updateAssets();
    }

    protected abstract void updateAssets();

    public void setValue(final int value) {
        final int valueWas = getValue();
        this.value = Math.max(minValue, Math.min(value, maxValue));

        if (canSetImplicitly)
            setter.accept(this.value);

        if (getValue() != valueWas)
            updateAssets();
    }

    public void incrementValue(final int delta) {
        setValue(value + delta);
    }

    // GETTERS

    public boolean isHighlighted() {
        return highlighted;
    }

    public boolean isSliding() {
        return sliding;
    }

    public int getValue() {
        return value;
    }
}
