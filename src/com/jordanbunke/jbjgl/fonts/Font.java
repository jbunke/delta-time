package com.jordanbunke.jbjgl.fonts;

import com.jordanbunke.jbjgl.image.JBJGLImage;
import com.jordanbunke.jbjgl.utility.JBJGLGlobal;

import java.awt.*;
import java.nio.file.Path;
import java.util.Map;

import static com.jordanbunke.jbjgl.fonts.FontConstants.ASCII_SUFFIX;
import static com.jordanbunke.jbjgl.fonts.FontConstants.LATIN_EXTENDED_SUFFIX;

public class Font {


    private final Map<Character, Grapheme> CHARACTER_MAP;
    private final int pixelSpacing, height;

    private Font(final Map<Character, Grapheme> characterMap, final int pixelSpacing, final int height) {
        CHARACTER_MAP = characterMap;
        this.pixelSpacing = pixelSpacing;
        this.height = height;
    }

    public static Font loadFromSource(
            final Path folder, final String baseName,
            final boolean hasLatinExtended,
            final int pixelSpacing
    ) {
        final Path asciiFilepath = folder.resolve(baseName + ASCII_SUFFIX);

        Map<Character, Grapheme> characterMap =
                FontLoader.loadASCIIFromSource(asciiFilepath);

        // TODO: Each additional charset can be introduced with an analogous code block and font loader
        if (hasLatinExtended) {
            final Path latinExtendedFilepath = folder.resolve(baseName + LATIN_EXTENDED_SUFFIX);
            Map<Character, Grapheme> latinExtendedMap =
                    FontLoader.loadLatinExtendedFromSource(latinExtendedFilepath);

            for (Character c : latinExtendedMap.keySet())
                characterMap.put(c, latinExtendedMap.get(c));
        }

        final int height = characterMap.get(' ').getHeight();

        return new Font(characterMap, pixelSpacing, height);
    }

    public JBJGLImage drawChar(final char c, final Color color) {
        return getGrapheme(c).getImage(color);
    }

    public int getCharWidth(final char c) {
        return getGrapheme(c).getWidth();
    }

    public int getPixelSpacing() {
        return pixelSpacing;
    }

    public int getHeight() {
        return height;
    }

    private Grapheme getGrapheme(final char c) {
        if (CHARACTER_MAP.containsKey(c))
            return CHARACTER_MAP.get(c);
        else {
            JBJGLGlobal.printErrorToJBJGLChannel(
                    "Attempted to draw the character \"" + c +
                            "\", which is unsupported by this font."
            );

            return CHARACTER_MAP.get(FontConstants.REPLACEMENT);
        }
    }
}
