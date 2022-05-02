package com.jordanbunke.jbjgl.io;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Path;

public class JBJGLImageIO {
    public static BufferedImage readImage(final Path filepath) {
        try {
            return javax.imageio.ImageIO.read(filepath.toFile());
        } catch (IOException e) {
            // TODO
            e.printStackTrace();
        }

        return new BufferedImage(0, 0, BufferedImage.TYPE_INT_ARGB);
    }

    public static void writeImage(final Path filepath, final BufferedImage image) {
        try {
            javax.imageio.ImageIO.write(image, "PNG", filepath.toFile());
        } catch (IOException e) {
            // TODO
            e.printStackTrace();
        }
    }
}
