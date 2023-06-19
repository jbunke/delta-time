package com.jordanbunke.jbjgl.image;

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

    public void draw(final GameImage toDraw, final int x, final int y) {
        initializeGraphics();
        g.drawImage(toDraw, x, y, null);
    }

    public void draw(final GameImage toDraw, final int x, final int y, final int width, final int height) {
        initializeGraphics();
        g.drawImage(toDraw, x, y, width, height, null);
    }

    public void setColor(final Color color) {
        initializeGraphics();
        g.setColor(color);
    }

    public void fillRectangle(final int x, final int y, final int width, final int height) {
        initializeGraphics();
        g.fillRect(x, y, width, height);
    }

    public void dot(final int x, final int y) {
        initializeGraphics();
        g.fillRect(x, y, 1, 1);
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
}
