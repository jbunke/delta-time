package com.jordanbunke.delta_time.io;

import java.io.File;
import java.nio.file.Path;

public final class PathUtils {
    // Relative path constants
    private static final String CURRENT_DIR = ".", PARENT_DIR = "..";

    public static Path relativeParentPath(Path workingDir, String formatted) {
        final int CLIP_LENGTH = (PARENT_DIR + File.separator).length();

        while (isParent(formatted)) {
            formatted = formatted.substring(CLIP_LENGTH);
            workingDir = workingDir.getParent();
        }

        return workingDir.resolve(Path.of(formatted).normalize());
    }

    public static String formatPathString(final String pathAsString) {
        return pathAsString.replace("/", File.separator)
                .replace("\\\\", "\\").replace("\\", File.separator);
    }

    public static boolean formattedPathIsRelative(final String formatted) {
        return isCurrent(formatted) || isParent(formatted);
    }

    public static boolean isCurrent(final String formatted) {
        return formatted.startsWith(CURRENT_DIR + File.separator);
    }

    public static boolean isParent(final String formatted) {
        return formatted.startsWith(PARENT_DIR + File.separator);
    }
}
