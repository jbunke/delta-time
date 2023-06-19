package com.jordanbunke.jbjgl.io;

import com.jordanbunke.jbjgl.error.JBJGLError;
import com.jordanbunke.jbjgl.image.GameImage;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.nio.file.Path;

public class JBJGLImageIO {
    public static GameImage readImage(final Path filepath) {
        try {
            return new GameImage(ImageIO.read(filepath.toFile()));
        } catch (IOException e) {
            JBJGLError.send("Couldn't read image file: " + filepath);
        }

        return new GameImage(1, 1);
    }

    public static void writeImage(final Path filepath, final GameImage image) {
        try {
            ImageIO.write(image, "PNG", filepath.toFile());
        } catch (IOException e) {
            JBJGLError.send("Couldn't write to image file: " + filepath);
        }
    }
}
