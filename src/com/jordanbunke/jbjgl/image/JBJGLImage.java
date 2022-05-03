package com.jordanbunke.jbjgl.image;

import java.awt.*;
import java.awt.image.BufferedImage;

public class JBJGLImage extends BufferedImage {

    private JBJGLImage(final int width, final int height) {
        super(width, height, TYPE_INT_ARGB);
    }

    private JBJGLImage(final BufferedImage image) {
        super(image.getWidth(), image.getHeight(), TYPE_INT_ARGB);
        Graphics g = this.getGraphics();
        g.drawImage(image, 0, 0, null);
    }

    public static JBJGLImage create(final int width, final int height) {
        return new JBJGLImage(width, height);
    }

    public static JBJGLImage create(final BufferedImage image) {
        return new JBJGLImage(image);
    }
}
