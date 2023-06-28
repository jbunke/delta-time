package com.jordanbunke.delta_time.fonts;

import com.jordanbunke.delta_time.error.GameError;

import java.nio.file.Path;

public class FontFamily {
    public static final String NOT_AVAILABLE = "NOT AVAILABLE";
    public static final int STANDARD = 0, BOLD = 1, ITALICS = 2;
    private static final int LENGTH = 3;

    private final Font[] types = new Font[LENGTH];
    private final String name;

    public FontFamily(final String name, final Font standard, final Font bold, final Font italics) {
        this.name = name;

        types[STANDARD] = standard;
        types[BOLD] = bold;
        types[ITALICS] = italics;
    }

    public static FontFamily loadFromSources(
            final String name, final Path folder, final boolean isResource,
            final String standardBaseName,
            final String boldBaseName,
            final String italicsBaseName,
            final int standardSpacing,
            final int boldSpacing,
            final int italicsSpacing,
            final boolean hasLatinExtended
    ) {
        Font standard = standardBaseName.equals(NOT_AVAILABLE)
                ? null : Font.loadFromSource(folder, isResource,
                standardBaseName, hasLatinExtended, standardSpacing);
        Font bold = boldBaseName.equals(NOT_AVAILABLE)
                ? null : Font.loadFromSource(folder, isResource,
                boldBaseName, hasLatinExtended, boldSpacing);
        Font italics = italicsBaseName.equals(NOT_AVAILABLE)
                ? null : Font.loadFromSource(folder, isResource,
                italicsBaseName, hasLatinExtended, italicsSpacing);

        return new FontFamily(name, standard, bold, italics);
    }

    public boolean hasType(final int index) {
        return types[index] != null;
    }

    public Font getStandard() {
        if (!hasType(STANDARD)) {
            GameError.send(
                    "Font family \"" + name +
                            "\" does not have a standard font.", () -> {},
                    true, true);
        }

        return types[STANDARD];
    }

    public Font getBold() {
        if (!hasType(BOLD)) {
            GameError.send(
                    "Font family \"" + name +
                            "\" does not have a bold font... " +
                            "Attempting to retrieve standard font instead");

            return getStandard();
        }

        return types[BOLD];
    }

    public Font getItalics() {
        if (!hasType(ITALICS)) {
            GameError.send(
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
