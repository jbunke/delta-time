package com.jordanbunke.jbjgl.fonts;

import java.nio.file.Path;

public class FontFamily {
    static final String NOT_AVAILABLE = "NOT AVAILABLE";

    private final int STANDARD = 0, BOLD = 1, ITALICS = 2, LENGTH = 3;
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

    public static FontFamily loadFromSources(final String name, final Path folder,
                                             final String standardBaseName,
                                             final String boldBaseName,
                                             final String italicsBaseName,
                                             final boolean hasLatinExtended) {
        Font standard = standardBaseName.equals(NOT_AVAILABLE)
                ? null : Font.loadFromSource(folder, standardBaseName, hasLatinExtended);
        Font bold = boldBaseName.equals(NOT_AVAILABLE)
                ? null : Font.loadFromSource(folder, boldBaseName, hasLatinExtended);
        Font italics = italicsBaseName.equals(NOT_AVAILABLE)
                ? null : Font.loadFromSource(folder, italicsBaseName, hasLatinExtended);

        return new FontFamily(name, standard, bold, italics);
    }

    public boolean doesNotHaveStandard() {
        return types[STANDARD] == null;
    }

    public boolean doesNotHaveBold() {
        return types[BOLD] == null;
    }

    public boolean doesNotHaveItalics() {
        return types[ITALICS] == null;
    }

    public Font getStandard() {
        if (doesNotHaveStandard()) {
            // TODO
        }
        return types[STANDARD];
    }

    public Font getBold() {
        if (doesNotHaveBold()) {
            // TODO
        }
        return types[BOLD];
    }

    public Font getItalics() {
        if (doesNotHaveItalics()) {
            // TODO
        }
        return types[ITALICS];
    }

    public String getName() {
        return name;
    }
}
