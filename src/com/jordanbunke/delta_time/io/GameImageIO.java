package com.jordanbunke.delta_time.io;

import com.jordanbunke.delta_time.error.GameError;
import com.jordanbunke.delta_time.image.GameImage;
import com.squareup.gifencoder.FloydSteinbergDitherer;
import com.squareup.gifencoder.GifEncoder;
import com.squareup.gifencoder.ImageOptions;

import javax.imageio.ImageIO;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.util.concurrent.TimeUnit;

public class GameImageIO {
    public static GameImage readImage(final Path filepath) {
        try {
            return new GameImage(ImageIO.read(filepath.toFile()));
        } catch (IOException e) {
            GameError.send("Couldn't read image file: " + filepath);
        }

        return GameImage.dummy();
    }

    public static void writeImage(final Path filepath, final GameImage image) {
        try {
            ImageIO.write(image, "PNG", filepath.toFile());
        } catch (IOException e) {
            GameError.send("Couldn't write to image file: " + filepath);
        }
    }

    public static void writeGif(
            final Path filepath, final GameImage[] images,
            final int intervalMillis, final int repetitions
    ) {
        try (final FileOutputStream outputStream = new FileOutputStream(filepath.toFile())) {
            if (images.length == 0)
                return;

            final int width = images[0].getWidth(), height = images[0].getHeight();

            final GifEncoder gifEncoder = new GifEncoder(outputStream,
                    width, height, repetitions);
            final ImageOptions options = new ImageOptions()
                    .setDelay(intervalMillis, TimeUnit.MILLISECONDS)
                    .setDitherer(FloydSteinbergDitherer.INSTANCE);

            for (GameImage image : images)
                gifEncoder.addImage(convertImageToArray(image), options);

            gifEncoder.finishEncoding();
        } catch (IOException e) {
            GameError.send("Couldn't write to GIF image file: " + filepath);
        }
    }

    public static void writeGif(
            final Path filepath, final GameImage[] images,
            final int intervalMillis
    ) {
        writeGif(filepath, images, intervalMillis, 0);
    }

    private static int[][] convertImageToArray(final GameImage image) {
        final int[][] colors = new int[image.getHeight()][image.getWidth()];

        for (int y = 0; y < colors.length; y++) {
            for (int x = 0; x < colors[y].length; x++) {
                colors[y][x] = image.getRGB(x, y);
            }
        }

        return colors;
    }
}
