package com.jordanbunke.jbjgl.io;

import com.jordanbunke.jbjgl.error.JBJGLError;
import com.jordanbunke.jbjgl.image.JBJGLImage;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;

public class JBJGLResourceLoader {
    public static String formatResourcePath(final Path path) {
        final char SEP = '/';
        return SEP + path.toString().replace('\\', SEP);
    }

    public static <T> InputStream loadResource(final Class<T> loaderClass, final Path resource) {
        return loaderClass.getResourceAsStream(formatResourcePath(resource));
    }

    public static <T> JBJGLImage loadImageResource(final Class<T> loaderClass, final Path resource) {
        try (final InputStream inputStream = loadResource(loaderClass, resource)) {
            if (inputStream != null)
                return JBJGLImage.create(ImageIO.read(inputStream));
        } catch (IOException e) {
            JBJGLError.send("Could not load image resource: " + resource);
        }

        return JBJGLImage.create(1, 1);
    }
}
