package com.jordanbunke.delta_time.io;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;

@FunctionalInterface
public interface ZipWriter<T> {
    void write(T data, OutputStream outputStream) throws IOException;

    static void forString(
            final String string,
            final OutputStream outputStream
    ) throws IOException {
        final OutputStreamWriter writer =
                new OutputStreamWriter(outputStream, StandardCharsets.UTF_8);
        writer.write(string);
        writer.flush();
    }

    static void forImage(
            final BufferedImage image,
            final OutputStream outputStream
    ) throws IOException {
        ImageIO.write(image, "PNG", outputStream);
    }
}
