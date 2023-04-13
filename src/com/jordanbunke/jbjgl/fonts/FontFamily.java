package com.jordanbunke.jbjgl.fonts;

import com.jordanbunke.jbjgl.error.JBJGLError;

import java.nio.file.Path;

public class FontFamily {
    public static final String NOT_AVAILABLE = "NOT AVAILABLE";
    public static final int STANDARD = 0, BOLD = 1, ITALICS = 2;
    private static final int LENGTH = 3;

    private final Font[] types = new Font[LENGTH];
    private final String name;

    private FontFamily(final String name, final Font standard, final Font bold, final Font italics) {
        this.name = name;

        types[STANDARD] = standard;
        types[BOLD] = bold;
        types[ITALICS] = italics;
    }

    public static FontFamily fromPreLoaded(final String name, final Font standard,
                                           final Font bold, final Font italics) {
        return new FontFamily(name, standard, bold, italics);
    }

    public static FontFamily loadFromSources(
            final String name, final Path folder,
            final String standardBaseName,
            final String boldBaseName,
            final String italicsBaseName,
            final int standardSpacing,
            final int boldSpacing,
            final int italicsSpacing,
            final boolean hasLatinExtended
    ) {
        Font standard = standardBaseName.equals(NOT_AVAILABLE)
                ? null : Font.loadFromSource(
                folder, standardBaseName, hasLatinExtended, standardSpacing
        );
        Font bold = boldBaseName.equals(NOT_AVAILABLE)
                ? null : Font.loadFromSource(
                folder, boldBaseName, hasLatinExtended, boldSpacing
        );
        Font italics = italicsBaseName.equals(NOT_AVAILABLE)
                ? null : Font.loadFromSource(
                folder, italicsBaseName, hasLatinExtended, italicsSpacing
        );

        return new FontFamily(name, standard, bold, italics);
    }

    public boolean hasType(final int index) {
        return types[index] != null;
    }

    public Font getStandard() {
        if (!hasType(STANDARD)) {
            JBJGLError.send(
                    "Font family \"" + name +
                            "\" does not have a standard font.", () -> {},
                    true, true);
        }

        return types[STANDARD];
    }

    public Font getBold() {
        if (!hasType(BOLD)) {
            JBJGLError.send(
                    "Font family \"" + name +
                            "\" does not have a bold font... " +
                            "Attempting to retrieve standard font instead");

            return getStandard();
        }

        return types[BOLD];
    }

    public Font getItalics() {
        if (!hasType(ITALICS)) {
            JBJGLError.send(
                    "Font family \"" + name +
                            "\" does not have an italic font... " +
                            "Attempting to retrieve standard font instead");

            return getStandard();
        }

        return types[ITALICS];
    }

    public String getName() {
        return name;
    }
}
