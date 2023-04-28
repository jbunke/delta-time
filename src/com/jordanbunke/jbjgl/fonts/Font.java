package com.jordanbunke.jbjgl.fonts;

import com.jordanbunke.jbjgl.error.JBJGLError;
import com.jordanbunke.jbjgl.image.JBJGLImage;

import java.awt.*;
import java.nio.file.Path;
import java.util.Map;

import static com.jordanbunke.jbjgl.fonts.FontConstants.ASCII_SUFFIX;
import static com.jordanbunke.jbjgl.fonts.FontConstants.LATIN_EXTENDED_SUFFIX;

public class Font {

    private final Map<Character, Grapheme> CHARACTER_MAP;
    private final int pixelSpacing, height;
    private final boolean smoothResizing;

    private Font(
            final Map<Character, Grapheme> characterMap,
            final int pixelSpacing, final int height,
            final boolean smoothResizing
    ) {
        CHARACTER_MAP = characterMap;
        this.pixelSpacing = pixelSpacing;
        this.height = height;
        this.smoothResizing = smoothResizing;
    }

    public static <T> Font loadFromSource(
            final Path folder, final Class<T> loaderClass,
            final String baseName, final boolean hasLatinExtended,
            final int pixelSpacing
    ) {
        return Font.loadFromSource(folder, loaderClass, baseName, hasLatinExtended,
                1.0, pixelSpacing, false);
    }

    public static <T> Font loadFromSource(
            final Path folder, final Class<T> loaderClass,
            final String baseName, final boolean hasLatinExtended,
            final double whitespaceBreadthMultiplier,
            final int pixelSpacing,
            final boolean smoothResizing
    ) {
        final Path asciiFilepath = folder.resolve(baseName + ASCII_SUFFIX);

        Map<Character, Grapheme> characterMap =
                FontLoader.loadASCIIFromSource(asciiFilepath, loaderClass, whitespaceBreadthMultiplier);

        // TODO: Each additional charset can be introduced with an analogous code block and font loader
        if (hasLatinExtended) {
            final Path latinExtendedFilepath = folder.resolve(baseName + LATIN_EXTENDED_SUFFIX);
            Map<Character, Grapheme> latinExtendedMap =
                    FontLoader.loadLatinExtendedFromSource(latinExtendedFilepath, loaderClass);

            for (Character c : latinExtendedMap.keySet())
                characterMap.put(c, latinExtendedMap.get(c));
        }

        final int height = characterMap.get(' ').getHeight();

        return new Font(characterMap, pixelSpacing, height, smoothResizing);
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

    public boolean hasSmoothResizing() {
        return smoothResizing;
    }

    private Grapheme getGrapheme(final char c) {
        if (CHARACTER_MAP.containsKey(c))
            return CHARACTER_MAP.get(c);
        else {
            JBJGLError.send("Attempted to draw the character \"" + c +
                            "\", which is unsupported by this font.");

            return CHARACTER_MAP.get(FontConstants.REPLACEMENT);
        }
    }
}
