package com.jordanbunke.delta_time.scripting.util;

import com.jordanbunke.delta_time.scripting.ast.symbol_table.SymbolTable;

import java.io.File;
import java.nio.file.Path;

public final class PathHelper {
    // Relative path constants
    private static final String CURRENT_DIR = ".", PARENT_DIR = "..";

    public static Path process(
            final String pathAsString, final SymbolTable symbolTable,
            final TextPosition codePos
    ) {
        final String formatted = formatPathString(pathAsString);
        final Path workingDir = workingDirectory(symbolTable),
                specified = Path.of(formatted).normalize();

        if (formattedPathIsRelative(formatted)) {
            if (workingDir != null) {
                if (isParent(formatted))
                    return relativeParentPath(workingDir, formatted);

                return workingDir.resolve(specified);
            }

            ScriptErrorLog.fireError(ScriptErrorLog.Message.CUSTOM_RT, codePos,
                    "Unable to resolve the relative path \"" + pathAsString +
                            "\" because the working directory is unknown");
        }

        return specified;
    }

    private static Path workingDirectory(final SymbolTable symbolTable) {
        final Path scriptPath = symbolTable.getScriptPath();

        return scriptPath == null ? null : scriptPath.getParent();
    }

    private static Path relativeParentPath(Path workingDir, String formatted) {
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

    private static boolean formattedPathIsRelative(final String formatted) {
        return isCurrent(formatted) || isParent(formatted);
    }

    private static boolean isCurrent(final String formatted) {
        return formatted.startsWith(CURRENT_DIR + File.separator);
    }

    private static boolean isParent(final String formatted) {
        return formatted.startsWith(PARENT_DIR + File.separator);
    }
}
