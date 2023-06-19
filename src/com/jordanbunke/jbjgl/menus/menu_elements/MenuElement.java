package com.jordanbunke.jbjgl.menus.menu_elements;

import com.jordanbunke.jbjgl.contexts.JBJGLMenuManager;
import com.jordanbunke.jbjgl.debug.JBJGLGameDebugger;
import com.jordanbunke.jbjgl.image.GameImage;
import com.jordanbunke.jbjgl.io.JBJGLListener;
import com.jordanbunke.jbjgl.utility.RenderConstants;

import java.awt.*;

public abstract class MenuElement {
    private final int[] position;
    private final int[] dimensions;
    private final Anchor anchor;
    private final boolean isVisible;

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
            final int[] position, final int[] dimensions,
            final Anchor anchor, final boolean isVisible
    ) {
        this.position = position;
        this.dimensions = dimensions;
        this.anchor = anchor;
        this.isVisible = isVisible;
    }

    public abstract void update();
    public abstract void render(final Graphics g, final JBJGLGameDebugger debugger);
    public abstract void process(final JBJGLListener listener, final JBJGLMenuManager menuManager);

    public void draw(final GameImage image, final Graphics g) {
        if (!isVisible)
            return;

        final int[] offset = new int[] {
                (dimensions[RenderConstants.WIDTH] - image.getWidth()) / 2,
                (dimensions[RenderConstants.HEIGHT] - image.getHeight()) / 2
        };
        final int[] renderPosition = getRenderPosition(offset);
        g.drawImage(
                image, renderPosition[RenderConstants.X],
                renderPosition[RenderConstants.Y], null
        );
    }

    public int[] getRenderPosition(final int[] offset) {
        final int[] bounds = new int[2];

        switch (anchor) {
            case LEFT_TOP -> {
                bounds[RenderConstants.X] =
                        position[RenderConstants.X];
                bounds[RenderConstants.Y] =
                        position[RenderConstants.Y];
            }
            case CENTRAL_TOP -> {
                bounds[RenderConstants.X] =
                        position[RenderConstants.X] -
                                (dimensions[RenderConstants.WIDTH] / 2);
                bounds[RenderConstants.Y] =
                        position[RenderConstants.Y];
            }
            case RIGHT_TOP -> {
                bounds[RenderConstants.X] =
                        position[RenderConstants.X] -
                                dimensions[RenderConstants.WIDTH];
                bounds[RenderConstants.Y] =
                        position[RenderConstants.Y];
            }
            case LEFT_CENTRAL -> {
                bounds[RenderConstants.X] =
                        position[RenderConstants.X];
                bounds[RenderConstants.Y] =
                        position[RenderConstants.Y] -
                                (dimensions[RenderConstants.HEIGHT] / 2);
            }
            case CENTRAL -> {
                bounds[RenderConstants.X] =
                        position[RenderConstants.X] -
                                (dimensions[RenderConstants.WIDTH] / 2);
                bounds[RenderConstants.Y] =
                        position[RenderConstants.Y] -
                                (dimensions[RenderConstants.HEIGHT] / 2);
            }
            case RIGHT_CENTRAL -> {
                bounds[RenderConstants.X] =
                        position[RenderConstants.X] -
                                dimensions[RenderConstants.WIDTH];
                bounds[RenderConstants.Y] =
                        position[RenderConstants.Y] -
                                (dimensions[RenderConstants.HEIGHT] / 2);
            }
            case LEFT_BOTTOM -> {
                bounds[RenderConstants.X] =
                        position[RenderConstants.X];
                bounds[RenderConstants.Y] =
                        position[RenderConstants.Y] -
                                dimensions[RenderConstants.HEIGHT];
            }
            case CENTRAL_BOTTOM -> {
                bounds[RenderConstants.X] =
                        position[RenderConstants.X] -
                                (dimensions[RenderConstants.WIDTH] / 2);
                bounds[RenderConstants.Y] =
                        position[RenderConstants.Y] -
                                dimensions[RenderConstants.HEIGHT];
            }
            case RIGHT_BOTTOM -> {
                bounds[RenderConstants.X] =
                        position[RenderConstants.X] -
                                dimensions[RenderConstants.WIDTH];
                bounds[RenderConstants.Y] =
                        position[RenderConstants.Y] -
                                dimensions[RenderConstants.HEIGHT];
            }
        }

        bounds[RenderConstants.X] += offset[RenderConstants.X];
        bounds[RenderConstants.Y] += offset[RenderConstants.Y];

        return bounds;
    }

    public boolean mouseIsWithinBounds(final int[] mousePosition) {
        if (!isVisible)
            return false;

        final int[] min = getRenderPosition(new int[] { 0, 0 });
        final int[] max = new int[] {
                min[RenderConstants.X] + dimensions[RenderConstants.WIDTH],
                min[RenderConstants.Y] + dimensions[RenderConstants.HEIGHT]
        };
        return
                mousePosition[RenderConstants.X] >= min[RenderConstants.X] &&
                mousePosition[RenderConstants.X] < max[RenderConstants.X] &&
                mousePosition[RenderConstants.Y] >= min[RenderConstants.Y] &&
                mousePosition[RenderConstants.Y] < max[RenderConstants.Y];
    }

    public void renderBoundingBox(final Graphics g, final JBJGLGameDebugger debugger) {
        if (debugger == null || !debugger.isShowingBoundingBoxes() || !isVisible)
            return;

        Graphics2D g2D = (Graphics2D) g;
        final int[] renderPosition = getRenderPosition(new int[] { 0, 0 });
        g2D.setColor(new Color(0, 255, 0, 255));
        g2D.setStroke(new BasicStroke(1));
        g.drawRect(
                renderPosition[RenderConstants.X], renderPosition[RenderConstants.Y],
                dimensions[RenderConstants.WIDTH] - 1, dimensions[RenderConstants.HEIGHT] - 1
        );
    }

    public void setX(final int x) {
        position[RenderConstants.X] = x;
    }

    public void setY(final int y) {
        position[RenderConstants.Y] = y;
    }

    public void incrementX(final int deltaX) {
        position[RenderConstants.X] += deltaX;
    }

    public void incrementY(final int deltaY) {
        position[RenderConstants.X] += deltaY;
    }

    public int getX() {
        return position[RenderConstants.X];
    }

    public int getY() {
        return position[RenderConstants.Y];
    }

    public int getWidth() {
        return dimensions[RenderConstants.WIDTH];
    }

    public int getHeight() {
        return dimensions[RenderConstants.HEIGHT];
    }

    public Anchor getAnchor() {
        return anchor;
    }

    public boolean isVisible() {
        return isVisible;
    }

    @Override
    public String toString() {
        return "at (" + position[RenderConstants.X] +
                ", " + position[RenderConstants.Y] +
                ") [ " + anchor.toString() + " ]";
    }
}
