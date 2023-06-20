package com.jordanbunke.jbjgl.image;

import com.jordanbunke.jbjgl.utility.Coord2D;

import java.awt.*;

public class ImageProcessing {
    public static Color colorAtPixel(final GameImage image, final int x, final int y) {
        return new Color(image.getRGB(x, y), true);
    }

    public static GameImage replaceColor(final GameImage original,
                                         final Color toReplace, final Color replaceWith) {
        final GameImage replacement = new GameImage(original.getWidth(), original.getHeight());
        replacement.setColor(replaceWith);

        for (int x = 0; x < replacement.getWidth(); x++)
            for (int y = 0; y < replacement.getHeight(); y++)
                if (colorAtPixel(original, x, y).equals(toReplace))
                    replacement.dot(x, y);

        return replacement.submit();
    }

    public static GameImage scale(final GameImage image, final int scaleFactor) {
        final int width = image.getWidth() * scaleFactor, height = image.getHeight() * scaleFactor;
        return scale(image, width, height);
    }

    public static GameImage scale(final GameImage image, final int width, final int height) {
        final GameImage scaledUp = new GameImage(width, height);
        scaledUp.draw(image, 0, 0, scaledUp.getWidth(), scaledUp.getHeight());
        return scaledUp.submit();
    }

    public static GameImage scale(
            final GameImage image, final double scaleFactor,
            final boolean smooth
    ) {
        final Coord2D dims = new Coord2D(
                Math.max(1, (int)(image.getWidth() * scaleFactor)),
                Math.max(1, (int)(image.getHeight() * scaleFactor))
        );

        final GameImage scaledUp = new GameImage(dims.x, dims.y);

        if (smooth) {
            for (int x = 0; x < dims.x; x++) {
                for (int y = 0; y < dims.y; y++) {
                    final int initialOriginX = (int)((x / (double) dims.x) * image.getWidth());
                    final int finalOriginX = Math.min(
                            (int)(((x + 1) / (double) dims.x) * image.getWidth()),
                            image.getWidth() - 1);
                    final int initialOriginY = (int)((y / (double) dims.y) * image.getHeight());
                    final int finalOriginY = Math.min(
                            (int)(((y + 1) / (double) dims.y) * image.getHeight()),
                            image.getHeight() - 1);

                    int cumulativeR = 0, cumulativeG = 0, cumulativeB = 0, cumulativeA = 0;
                    int totalColors = 0, totalOpacity = 0;

                    for (int originX = initialOriginX; originX <= finalOriginX; originX++) {
                        for (int originY = initialOriginY; originY <= finalOriginY; originY++) {
                            final Color originColor = colorAtPixel(image, originX, originY);

                            totalOpacity++;
                            cumulativeA += originColor.getAlpha();

                            if (originColor.getAlpha() > 0) {
                                totalColors++;

                                cumulativeR += originColor.getRed();
                                cumulativeG += originColor.getGreen();
                                cumulativeB += originColor.getBlue();
                            }
                        }
                    }

                    if (totalColors == 0) continue;

                    final Color pixel = new Color(
                            cumulativeR / totalColors,
                            cumulativeG / totalColors,
                            cumulativeB / totalColors,
                            totalOpacity == 0 ? 0 : cumulativeA / totalOpacity);

                    scaledUp.setColor(pixel);
                    scaledUp.dot(x, y);
                }
            }
        } else {
            for (int x = 0; x < image.getWidth(); x++) {
                for (int y = 0; y < image.getHeight(); y++) {
                    final int FULL_OPACITY = 255;
                    final Color color = colorAtPixel(image, x, y);
                    scaledUp.setColor(color);

                    if (color.getAlpha() < FULL_OPACITY) {
                        final int xInit = (int)(x * scaleFactor),
                                yInit = (int)(y * scaleFactor),
                                xBound = Math.min(dims.x, xInit + (int)Math.ceil(scaleFactor)),
                                yBound = Math.min(dims.y, yInit + (int)Math.ceil(scaleFactor));
                        for (int xp = xInit; xp < xBound; xp++)
                            for (int yp = yInit; yp < yBound; yp++)
                                if (!colorAtPixel(scaledUp, xp, yp).equals(color))
                                    scaledUp.dot(xp, yp);

                    } else
                        scaledUp.fillRectangle((int)(x * scaleFactor), (int)(y * scaleFactor),
                                (int)Math.ceil(scaleFactor), (int)Math.ceil(scaleFactor));
                }
            }
        }

        return scaledUp.submit();
    }
}
