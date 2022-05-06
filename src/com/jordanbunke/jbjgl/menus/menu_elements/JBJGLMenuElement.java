package com.jordanbunke.jbjgl.menus.menu_elements;

import com.jordanbunke.jbjgl.contexts.JBJGLMenuManager;
import com.jordanbunke.jbjgl.image.JBJGLImage;
import com.jordanbunke.jbjgl.io.JBJGLListener;
import com.jordanbunke.jbjgl.utility.RenderConstants;

import java.awt.*;

public abstract class JBJGLMenuElement {
    private final int[] position;
    private final int[] dimensions;
    private final Anchor anchor;

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

    public JBJGLMenuElement(final int[] position, final int[] dimensions, final Anchor anchor) {
        this.position = position;
        this.dimensions = dimensions;
        this.anchor = anchor;
    }

    public abstract void update();
    public abstract void render(final Graphics g);
    public abstract void process(final JBJGLListener listener, final JBJGLMenuManager menuManager);

    public void draw(final JBJGLImage image, final Graphics g) {
        final int[] renderPosition = this.getRenderPosition();
        g.drawImage(
                image, renderPosition[RenderConstants.X],
                renderPosition[RenderConstants.Y], null
        );
    }

    public int[] getRenderPosition() {
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

        return bounds;
    }

    public boolean mouseIsWithinBounds(final int[] mousePosition) {
        final int[] min = getRenderPosition();
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

    @Override
    public String toString() {
        return "at (" + position[RenderConstants.X] +
                ", " + position[RenderConstants.Y] +
                ") [ " + anchor.toString() + " ]";
    }
}
