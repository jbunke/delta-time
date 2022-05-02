package com.jordanbunke.jbjgl.image;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ImageMath {
    public static Color colorAtPixel(final BufferedImage image, final int x, final int y) {
        return new Color(image.getRGB(x, y), true);
    }

    public static BufferedImage replaceColor(final BufferedImage original,
                                             final Color toReplace, final Color replaceWith) {
        BufferedImage replacement = new BufferedImage(original.getWidth(), original.getHeight(),
                BufferedImage.TYPE_INT_ARGB);
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

    public static BufferedImage scaleUp(final BufferedImage image, final int scaleFactor) {
        BufferedImage scaledUp = new BufferedImage(image.getWidth() * scaleFactor,
                image.getHeight() * scaleFactor, BufferedImage.TYPE_INT_ARGB);
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
