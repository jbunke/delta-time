package com.jordanbunke.jbjgl.fonts;

import com.jordanbunke.jbjgl.image.ImageProcessing;
import com.jordanbunke.jbjgl.image.GameImage;

import java.awt.*;

import static com.jordanbunke.jbjgl.fonts.FontConstants.MATCH_COLOR;

public class Grapheme {
    private final GameImage image;
    private final char c;
    private final int width;
    private final int height;

    public Grapheme(final GameImage image, final char c, final int width, final int height) {
        this.c = c;
        this.image = image;
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public GameImage getImage(final Color color) {
        return ImageProcessing.replaceColor(image, MATCH_COLOR, color);
    }

    @Override
    public String toString() {
        return c + " ( " + width + "x" + height + " )";
    }
}
