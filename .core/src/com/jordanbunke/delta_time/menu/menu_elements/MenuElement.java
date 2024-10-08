package com.jordanbunke.delta_time.menu.menu_elements;

import com.jordanbunke.delta_time._core.ProgramContext;
import com.jordanbunke.delta_time.debug.GameDebugger;
import com.jordanbunke.delta_time.image.GameImage;
import com.jordanbunke.delta_time.utility.math.Bounds2D;
import com.jordanbunke.delta_time.utility.math.Coord2D;

import java.awt.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.function.IntFunction;

public abstract class MenuElement implements ProgramContext {
    private Coord2D position;
    private Bounds2D dimensions;
    private final Anchor anchor;
    private final boolean visible;

    public enum Anchor {
        LEFT_TOP, CENTRAL_TOP, RIGHT_TOP,
        LEFT_CENTRAL, CENTRAL, RIGHT_CENTRAL,
        LEFT_BOTTOM, CENTRAL_BOTTOM, RIGHT_BOTTOM;

        @Override
        public String toString() {
            return switch (this) {
                case LEFT_TOP -> "left, top-aligned";
                case CENTRAL_TOP -> "top centered";
                case RIGHT_TOP -> "right, top-aligned";
                case LEFT_CENTRAL -> "left-aligned; y-centered";
                case CENTRAL -> "centered";
                case RIGHT_CENTRAL -> "right-aligned; y-centered";
                case LEFT_BOTTOM -> "left, bottom-aligned";
                case CENTRAL_BOTTOM -> "bottom centered";
                case RIGHT_BOTTOM -> "right, bottom-aligned";
            };
        }
    }

    public MenuElement(
            final Coord2D position, final Bounds2D dimensions,
            final Anchor anchor, final boolean visible
    ) {
        this.position = position;
        this.dimensions = dimensions;
        this.anchor = anchor;
        this.visible = visible;
    }

    public static MenuElement[] sortForRender(
            final MenuElement[] menuElements) {
        return Arrays.stream(menuElements)
                .sorted(Comparator.comparingInt(MenuElement::getRenderOrder))
                .toArray(MenuElement[]::new);
    }

    public static <T extends MenuElement> T[] sortForRender(
            final T[] menuElements, final IntFunction<T[]> generator) {
        return Arrays.stream(menuElements)
                .sorted(Comparator.comparingInt(MenuElement::getRenderOrder))
                .toArray(generator);
    }

    public void draw(final GameImage image, final GameImage canvas) {
        if (!visible)
            return;

        final Coord2D renderPosition = getRenderPosition();
        canvas.draw(image, renderPosition.x, renderPosition.y);
    }

    public int getRenderOrder() {
        return 0;
    }

    public Coord2D getRenderPosition() {
        return switch (anchor) {
            case LEFT_TOP -> new Coord2D(position.x, position.y);
            case CENTRAL_TOP -> new Coord2D(
                    position.x - (dimensions.width() / 2), position.y);
            case RIGHT_TOP -> new Coord2D(position.x - dimensions.width(), position.y);
            case LEFT_CENTRAL -> new Coord2D(position.x,
                    position.y - (dimensions.height() / 2));
            case CENTRAL -> new Coord2D(position.x - (dimensions.width() / 2),
                    position.y - (dimensions.height() / 2));
            case RIGHT_CENTRAL -> new Coord2D(position.x - dimensions.width(),
                    position.y - (dimensions.height() / 2));
            case LEFT_BOTTOM -> new Coord2D(position.x,
                    position.y - dimensions.height());
            case CENTRAL_BOTTOM -> new Coord2D(position.x - (dimensions.width() / 2),
                    position.y - dimensions.height());
            case RIGHT_BOTTOM -> new Coord2D(position.x - dimensions.width(),
                    position.y - dimensions.height());
        };
    }

    public boolean mouseIsWithinBounds(final Coord2D mousePosition) {
        if (!visible)
            return false;

        final Coord2D min = getRenderPosition();
        final Coord2D max = new Coord2D(min.x + dimensions.width(), min.y + dimensions.height());

        return mousePosition.x >= min.x && mousePosition.x < max.x &&
                mousePosition.y >= min.y && mousePosition.y < max.y;
    }

    public void renderBoundingBox(final GameImage canvas, final GameDebugger debugger) {
        if (debugger == null || !debugger.isShowingBoundingBoxes() || !visible)
            return;

        final Coord2D renderPosition = getRenderPosition();
        canvas.setColor(new Color(0, 255, 0, 255));

        final Graphics2D g = canvas.g();
        g.setStroke(new BasicStroke(1));
        g.drawRect(renderPosition.x, renderPosition.y,
                dimensions.width() - 1, dimensions.height() - 1);
    }

    public void setX(final int x) {
        position = new Coord2D(x, position.y);
    }

    public void setY(final int y) {
        position = new Coord2D(position.x, y);
    }

    public void setPosition(final Coord2D position) {
        this.position = position;
    }

    public void incrementX(final int deltaX) {
        setX(position.x + deltaX);
    }

    public void incrementY(final int deltaY) {
        setY(position.y + deltaY);
    }

    public void setDimensions(final Bounds2D dimensions) {
        this.dimensions = dimensions;
    }

    public void setWidth(final int width) {
        if (width > 0)
            dimensions = new Bounds2D(width, dimensions.height());
    }

    public void setHeight(final int height) {
        if (height > 0)
            dimensions = new Bounds2D(dimensions.width(), height);
    }

    public Coord2D getPosition() {
        return position;
    }

    public Bounds2D getDimensions() {
        return dimensions;
    }

    public int getX() {
        return position.x;
    }

    public int getY() {
        return position.y;
    }

    public int getWidth() {
        return dimensions.width();
    }

    public int getHeight() {
        return dimensions.height();
    }

    public Anchor getAnchor() {
        return anchor;
    }

    public boolean isVisible() {
        return visible;
    }

    public String typeName() {
        final String wholeName = getClass().getName();

        return wholeName.substring(wholeName.lastIndexOf('.') + 1);
    }

    @Override
    public String toString() {
        return typeName() +
                " of " + dimensions.width() + "x" + dimensions.height() +
                " at (" + position.x + ", " + position.y + ") [ " +
                anchor.toString() + " ]";
    }
}
