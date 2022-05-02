package com.jordanbunke.jbjgl.fonts;

import com.jordanbunke.jbjgl.image.ImageMath;

import java.awt.*;
import java.awt.image.BufferedImage;

import static com.jordanbunke.jbjgl.fonts.FontConstants.MATCH_COLOR;

public class Grapheme {
    private final BufferedImage image;
    private final char c;
    private final int width;
    private final int height;

    private Grapheme(final BufferedImage image, final char c, final int width, final int height) {
        this.c = c;
        this.image = image;
        this.width = width;
        this.height = height;
    }

    public static Grapheme create(final BufferedImage image, final char c,
                                  final int width, final int height) {
        return new Grapheme(image, c, width, height);
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public BufferedImage getImage(final Color color) {
        return ImageMath.replaceColor(image, MATCH_COLOR, color);
    }
}
