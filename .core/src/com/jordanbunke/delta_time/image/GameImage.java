package com.jordanbunke.delta_time.image;

import com.jordanbunke.delta_time.utility.math.Coord2D;

import java.awt.*;
import java.awt.image.BufferedImage;

public class GameImage extends BufferedImage {

    private Graphics2D g;

    public static GameImage dummy() {
        return new GameImage(1, 1);
    }

    public GameImage(final int width, final int height) {
        super(width, height, TYPE_INT_ARGB);

        g = null;
    }

    public GameImage(final BufferedImage image) {
        this(image.getWidth(), image.getHeight());

        final Graphics2D g = createGraphics();
        g.drawImage(image, 0, 0, null);
        g.dispose();
    }

    public void draw(final Image toDraw) {
        draw(toDraw, 0, 0);
    }

    public void draw(final Image toDraw, final int x, final int y) {
        initializeGraphics();
        g.drawImage(toDraw, x, y, null);
    }

    public void draw(final Image toDraw, final int x, final int y, final int width, final int height) {
        initializeGraphics();
        g.drawImage(toDraw, x, y, width, height, null);
    }

    public void setColor(final Color color) {
        initializeGraphics();
        g.setColor(color);
    }

    public void drawOval(
            final float strokeWidth, final int x, final int y,
            final int width, final int height
    ) {
        initializeGraphics();
        g.setStroke(new BasicStroke(strokeWidth));
        g.drawOval(x, y, width, height);
    }

    public void drawOval(
            final Color color, final float strokeWidth,
            final int x, final int y, final int width, final int height
    ) {
        setColor(color);
        drawOval(strokeWidth, x, y, width, height);
    }

    public void drawRectangle(
            final float strokeWidth, final int x, final int y,
            final int width, final int height
    ) {
        initializeGraphics();
        g.setStroke(new BasicStroke(strokeWidth));
        g.drawRect(x, y, width, height);
    }

    public void drawRectangle(
            final Color color, final float strokeWidth,
            final int x, final int y, final int width, final int height
    ) {
        setColor(color);
        drawRectangle(strokeWidth, x, y, width, height);
    }

    public void drawLine(
            final float strokeWidth,
            final int x1, final int y1, final int x2, final int y2
    ) {
        initializeGraphics();
        g.setStroke(new BasicStroke(strokeWidth));
        g.drawLine(x1, y1, x2, y2);
    }

    public void drawLine(
            final Color color, final float strokeWidth,
            final int x1, final int y1, final int x2, final int y2
    ) {
        setColor(color);
        drawLine(strokeWidth, x1, y1, x2, y2);
    }

    public void fillRectangle(final int x, final int y, final int width, final int height) {
        initializeGraphics();
        g.fillRect(x, y, width, height);
    }

    public void fillRectangle(final Color color, final int x, final int y, final int width, final int height) {
        setColor(color);
        fillRectangle(x, y, width, height);
    }

    public void fill(final Color color) {
        setColor(color);
        initializeGraphics();
        g.fillRect(0, 0, getWidth(), getHeight());
    }

    public void dot(final int x, final int y) {
        fillRectangle(x, y, 1, 1);
    }

    public void dot(final Color color, final int x, final int y) {
        setColor(color);
        dot(x, y);
    }

    private void initializeGraphics() {
        if (g == null)
            g = createGraphics();
    }

    public Graphics2D g() {
        initializeGraphics();
        return g;
    }

    public void free() {
        if (g != null) {
            g.dispose();
            g = null;
        }
    }

    public GameImage submit() {
        free();
        return this;
    }

    public GameImage section(final Coord2D topLeft, final Coord2D bottomRight) {
        return section(topLeft.x, topLeft.y, bottomRight.x, bottomRight.y);
    }

    public GameImage section(final int left, final int top, final int right, final int bottom) {
        return new GameImage(getSubimage(left, top, right - left, bottom - top));
    }

    public Color getColorAt(final int x, final int y) {
        return new Color(getRGB(x, y), true);
    }

    @Override
    public boolean equals(final Object o) {
        if (!(o instanceof GameImage img))
            return false;

        if (getWidth() != img.getWidth() || getHeight() != img.getHeight())
            return false;

        final int w = getWidth(), h = getHeight();

        for (int x = 0; x < w; x++)
            for (int y = 0; y < h; y++)
                if (!getColorAt(x, y).equals(img.getColorAt(x, y)))
                    return false;

        return true;
    }
}
