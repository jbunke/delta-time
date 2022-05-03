package com.jordanbunke.jbjgl.io;

import com.jordanbunke.jbjgl.image.JBJGLImage;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.nio.file.Path;

public class JBJGLImageIO {
    public static JBJGLImage readImage(final Path filepath) {
        try {
            return JBJGLImage.create(ImageIO.read(filepath.toFile()));
        } catch (IOException e) {
            // TODO
            e.printStackTrace();
        }

        return JBJGLImage.create(0, 0);
    }

    public static void writeImage(final Path filepath, final JBJGLImage image) {
        try {
            ImageIO.write(image, "PNG", filepath.toFile());
        } catch (IOException e) {
            // TODO
            e.printStackTrace();
        }
    }
}
