package com.jordanbunke.jbjgl.fonts;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.nio.file.Path;
import java.util.Map;

public class Font {
    private static final String FILE_SUFFIX = ".png";
    private static final String ASCII_SUFFIX = "-ascii" + FILE_SUFFIX;
    private static final String LATIN_EXTENDED_SUFFIX = "-latin-extended" + FILE_SUFFIX;

    private final Map<Character, Grapheme> CHARACTER_MAP;

    private Font(final Map<Character, Grapheme> characterMap) {
        CHARACTER_MAP = characterMap;
    }

    public static Font loadFromSource(final Path folder, final String baseName,
                                      boolean hasLatinExtended) {
        final Path asciiFilepath = folder.resolve(baseName + ASCII_SUFFIX);

        Map<Character, Grapheme> characterMap =
                FontLoader.loadASCIIFromSource(asciiFilepath);

        // Each additional charset can be introduced with an analogous code block and font loader
        if (hasLatinExtended) {
            final Path latinExtendedFilepath = folder.resolve(baseName + LATIN_EXTENDED_SUFFIX);
            Map<Character, Grapheme> latinExtendedMap =
                    FontLoader.loadLatinExtendedFromSource(latinExtendedFilepath);

            for (Character c : latinExtendedMap.keySet())
                characterMap.put(c, latinExtendedMap.get(c));
        }

        return new Font(characterMap);
    }

    public BufferedImage drawChar(final char c, final Color color) {
        if (CHARACTER_MAP.containsKey(c))
            return CHARACTER_MAP.get(c).getImage(color);
        else {
            // TODO: report unsupported character call
            return CHARACTER_MAP.get(' ').getImage(color);
        }
    }

    public int getCharWidth(final char c) {
        if (CHARACTER_MAP.containsKey(c))
            return CHARACTER_MAP.get(c).getWidth();
        else {
            // TODO: report unsupported character call
            return 0;
        }
    }
}
