package com.jordanbunke.delta_time.menus.menu_elements;

import com.jordanbunke.delta_time.contexts.ProgramContext;
import com.jordanbunke.delta_time.debug.GameDebugger;
import com.jordanbunke.delta_time.image.GameImage;
import com.jordanbunke.delta_time.utility.Coord2D;

import java.awt.*;
import java.util.Arrays;
import java.util.Comparator;

public abstract class MenuElement implements ProgramContext {
    private Coord2D position;
    private final Coord2D dimensions;
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
            final Coord2D position, final Coord2D dimensions,
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
            case CENTRAL_TOP -> new Coord2D(position.x - (dimensions.x / 2), position.y);
            case RIGHT_TOP -> new Coord2D(position.x - dimensions.x, position.y);
            case LEFT_CENTRAL -> new Coord2D(position.x, position.y - (dimensions.y / 2));
            case CENTRAL -> new Coord2D(position.x - (dimensions.x / 2), position.y - (dimensions.y / 2));
            case RIGHT_CENTRAL -> new Coord2D(position.x - dimensions.x, position.y - (dimensions.y / 2));
            case LEFT_BOTTOM -> new Coord2D(position.x, position.y - dimensions.y);
            case CENTRAL_BOTTOM -> new Coord2D(position.x - (dimensions.x / 2), position.y - dimensions.y);
            case RIGHT_BOTTOM -> new Coord2D(position.x - dimensions.x, position.y - dimensions.y);
        };
    }

    public boolean mouseIsWithinBounds(final Coord2D mousePosition) {
        if (!visible)
            return false;

        final Coord2D min = getRenderPosition();
        final Coord2D max = new Coord2D(min.x + dimensions.x, min.y + dimensions.y);

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
                dimensions.x - 1, dimensions.y - 1);
    }

    public void setX(final int x) {
        position = new Coord2D(x, position.y);
    }

    public void setY(final int y) {
        position = new Coord2D(position.x, y);
    }

    public void incrementX(final int deltaX) {
        position = position.displace(deltaX, 0);
    }

    public void incrementY(final int deltaY) {
        position = position.displace(0, deltaY);
    }

    public int getX() {
        return position.x;
    }

    public int getY() {
        return position.y;
    }

    public int getWidth() {
        return dimensions.x;
    }

    public int getHeight() {
        return dimensions.y;
    }

    public Anchor getAnchor() {
        return anchor;
    }

    public boolean isVisible() {
        return visible;
    }

    @Override
    public String toString() {
        return "at (" + position.x +
                ", " + position.y +
                ") [ " + anchor.toString() + " ]";
    }
}
