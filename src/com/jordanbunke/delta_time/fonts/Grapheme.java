package com.jordanbunke.delta_time.fonts;

import com.jordanbunke.delta_time.image.GameImage;
import com.jordanbunke.delta_time.image.ImageProcessing;

import java.awt.*;

import static com.jordanbunke.delta_time.fonts.FontConstants.MATCH_COLOR;

public class Grapheme {
    public static int SLICE_CONTAINS_NO_STROKE = -1;

    private final GameImage image;
    private final char c;
    public final int width, height;
    private final int[][] charWidthComponents;

    Grapheme(final GameImage image, final char c, final int width, final int height,
             final int[][] charWidthComponents) {
        this.c = c;
        this.image = image;
        this.width = width;
        this.height = height;
        this.charWidthComponents = charWidthComponents;
    }

    public boolean supportsCharSpecificSpacing() {
        return charWidthComponents != null;
    }

    public int[][] getCharWidthComponents() {
        return charWidthComponents;
    }

    public GameImage getImage(final Color color) {
        return ImageProcessing.replaceColor(image, MATCH_COLOR, color);
    }

    @Override
    public String toString() {
        return c + " ( " + width + "x" + height + " )";
    }
}
