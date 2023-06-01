package com.jordanbunke.jbjgl.image;

import com.jordanbunke.jbjgl.utility.RenderConstants;

import java.awt.*;

public class ImageProcessing {
    public static Color colorAtPixel(final JBJGLImage image, final int x, final int y) {
        return new Color(image.getRGB(x, y), true);
    }

    public static JBJGLImage replaceColor(final JBJGLImage original,
                                             final Color toReplace, final Color replaceWith) {
        final JBJGLImage replacement = JBJGLImage.create(original.getWidth(), original.getHeight());
        final Graphics g = replacement.getGraphics();
        g.setColor(replaceWith);

        // g.drawImage(original, 0, 0, null);

        for (int x = 0; x < replacement.getWidth(); x++) {
            for (int y = 0; y < replacement.getHeight(); y++) {
                if (colorAtPixel(original, x, y).equals(toReplace))
                    g.fillRect(x, y, 1, 1);
            }
        }

        g.dispose();

        return replacement;
    }

    public static void drawOnto(
            final JBJGLImage canvas, final JBJGLImage toDraw, final int xOffset, final int yOffset
    ) {
        Graphics g = canvas.getGraphics();
        g.drawImage(toDraw, xOffset, yOffset, null);
        g.dispose();
    }

    public static JBJGLImage scaleUp(final JBJGLImage image, final int scaleFactor) {
        JBJGLImage scaledUp = JBJGLImage.create(image.getWidth() * scaleFactor,
                image.getHeight() * scaleFactor);
        Graphics g = scaledUp.getGraphics();

        g.drawImage(image, 0, 0, scaledUp.getWidth(), scaledUp.getHeight(), null);

//        for (int x = 0; x < image.getWidth(); x++) {
//            for (int y = 0; y < image.getHeight(); y++) {
//                g.setColor(colorAtPixel(image, x, y));
//                g.fillRect(x * scaleFactor, y * scaleFactor, scaleFactor, scaleFactor);
//            }
//        }

        g.dispose();

        return scaledUp;
    }

    public static JBJGLImage scaleUp(
            final JBJGLImage image, final double scaleFactor,
            final boolean smooth
    ) {
        final int[] dims = new int[] {
                Math.max(1, (int)(image.getWidth() * scaleFactor)),
                Math.max(1, (int)(image.getHeight() * scaleFactor))
        };

        final JBJGLImage scaledUp = JBJGLImage.create(dims[RenderConstants.X], dims[RenderConstants.Y]);
        Graphics g = scaledUp.getGraphics();

        if (smooth) {
            for (int x = 0; x < scaledUp.getWidth(); x++) {
                for (int y = 0; y < scaledUp.getHeight(); y++) {
                    final int initialOriginX = (int)((x / (double) scaledUp.getWidth()) * image.getWidth());
                    final int finalOriginX = Math.min(
                            (int)(((x + 1) / (double) scaledUp.getWidth()) * image.getWidth()),
                            image.getWidth() - 1);
                    final int initialOriginY = (int)((y / (double) scaledUp.getHeight()) * image.getHeight());
                    final int finalOriginY = Math.min(
                            (int)(((y + 1) / (double) scaledUp.getHeight()) * image.getHeight()),
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

                    g.setColor(pixel);
                    g.fillRect(x, y, 1, 1);
                }
            }
        } else {
            for (int x = 0; x < image.getWidth(); x++) {
                for (int y = 0; y < image.getHeight(); y++) {
                    final Color color = colorAtPixel(image, x, y);
                    g.setColor(color);

                    if (color.getAlpha() < 255) {
                        final int xInit = (int)(x * scaleFactor),
                                yInit = (int)(y * scaleFactor),
                                xBound = Math.min(scaledUp.getWidth(), xInit + (int)Math.ceil(scaleFactor)),
                                yBound = Math.min(scaledUp.getHeight(), yInit + (int)Math.ceil(scaleFactor));
                        for (int xp = xInit; xp < xBound; xp++)
                            for (int yp = yInit; yp < yBound; yp++)
                                if (!colorAtPixel(scaledUp, xp, yp).equals(color))
                                    g.fillRect(xp, yp, 1, 1);

                    } else
                        g.fillRect((int)(x * scaleFactor), (int)(y * scaleFactor),
                                (int)Math.ceil(scaleFactor), (int)Math.ceil(scaleFactor));
                }
            }
        }

        g.dispose();

        return scaledUp;
    }
}
