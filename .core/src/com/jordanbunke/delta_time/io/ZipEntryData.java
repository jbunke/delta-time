package com.jordanbunke.delta_time.io;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Path;

public class ZipEntryData<T> {
    public final String path;
    private final T object;
    private final ZipWriter<T> writer;

    public ZipEntryData(
            final T object, final Path path, final ZipWriter<T> writer
    ) {
        this(object, path.toString(), writer);
    }

    public ZipEntryData(
            final T object, final String path, final ZipWriter<T> writer
    ) {
        this.object = object;
        this.path = path.replace("\\", "/");
        this.writer = writer;
    }

    public void write(final OutputStream outputStream) throws IOException {
        writer.write(object, outputStream);
    }
}
