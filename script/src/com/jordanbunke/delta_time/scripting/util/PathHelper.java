package com.jordanbunke.delta_time.scripting.util;

import com.jordanbunke.delta_time.scripting.ast.symbol_table.SymbolTable;

import java.nio.file.Path;

import static com.jordanbunke.delta_time.io.PathUtils.*;

public final class PathHelper {
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

            ScriptErrorLog.runtimeError(codePos,
                    "Unable to resolve the relative path \"" + pathAsString +
                            "\" because the working directory is unknown");
        }

        return specified;
    }

    private static Path workingDirectory(final SymbolTable symbolTable) {
        final Path scriptPath = symbolTable.getScriptPath();

        return scriptPath == null ? null : scriptPath.getParent();
    }
}
