package com.jordanbunke.jbjgl.image;

import java.awt.*;

public class ImageProcessing {
    public static Color colorAtPixel(final JBJGLImage image, final int x, final int y) {
        return new Color(image.getRGB(x, y), true);
    }

    public static JBJGLImage replaceColor(final JBJGLImage original,
                                             final Color toReplace, final Color replaceWith) {
        JBJGLImage replacement = JBJGLImage.create(original.getWidth(), original.getHeight());
        Graphics g = replacement.getGraphics();
        g.setColor(replaceWith);

        g.drawImage(original, 0, 0, null);

        for (int x = 0; x < replacement.getWidth(); x++) {
            for (int y = 0; y < replacement.getHeight(); y++) {
                if (colorAtPixel(replacement, x, y).equals(toReplace))
                    g.fillRect(x, y, 1, 1);
            }
        }

        g.dispose();

        return replacement;
    }

    public static void drawOnto(
            final JBJGLImage drawnOn, final JBJGLImage toDraw, final int xOffset, final int yOffset
    ) {
        Graphics g = drawnOn.getGraphics();
        g.drawImage(toDraw, xOffset, yOffset, null);
    }

    public static JBJGLImage scaleUp(final JBJGLImage image, final int scaleFactor) {
        JBJGLImage scaledUp = JBJGLImage.create(image.getWidth() * scaleFactor,
                image.getHeight() * scaleFactor);
        Graphics g = scaledUp.getGraphics();

        for (int x = 0; x < image.getWidth(); x++) {
            for (int y = 0; y < image.getHeight(); y++) {
                g.setColor(colorAtPixel(image, x, y));
                g.fillRect(x * scaleFactor, y * scaleFactor, scaleFactor, scaleFactor);
            }
        }

        g.dispose();

        return scaledUp;
    }
}
