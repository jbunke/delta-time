package com.jordanbunke.delta_time.io;

import com.jordanbunke.delta_time.error.GameError;
import com.jordanbunke.delta_time.image.GameImage;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Path;

public class ResourceLoader {
    private static String formatResourcePath(final Path path) {
        final char SEP = '/';
        return SEP + path.toString().replace('\\', SEP);
    }

    public static InputStream loadResource(final Path resource) {
        final String resourcePath = formatResourcePath(resource);
        return ResourceLoader.class.getResourceAsStream(resourcePath);
    }

    public static URL loadResourceAsURL(final Path resource) {
        final String resourcePath = formatResourcePath(resource);
        return ResourceLoader.class.getResource(resourcePath);
    }

    public static GameImage loadImageResource(final Path resource) {
        try (final InputStream inputStream = loadResource(resource)) {
            if (inputStream != null)
                return new GameImage(ImageIO.read(inputStream));
        } catch (IOException e) {
            GameError.send("Could not load image resource: " + resource);
        }

        return GameImage.dummy();
    }
}
