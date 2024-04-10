package com.jordanbunke.delta_time.fonts;

import com.jordanbunke.delta_time.error.GameError;
import com.jordanbunke.delta_time.image.GameImage;

import java.awt.*;
import java.nio.file.Path;
import java.util.Map;

import static com.jordanbunke.delta_time.fonts.FontConstants.ASCII_SUFFIX;
import static com.jordanbunke.delta_time.fonts.FontConstants.LATIN_EXTENDED_SUFFIX;

public class Font {

    private final Map<Character, Grapheme> CHARACTER_MAP;
    private final int pixelSpacing, height;
    private final boolean smoothResizing, charSpecificSpacing;

    private Font(
            final Map<Character, Grapheme> characterMap,
            final int pixelSpacing, final int height,
            final boolean smoothResizing, final boolean charSpecificSpacing
    ) {
        CHARACTER_MAP = characterMap;
        this.pixelSpacing = pixelSpacing;
        this.height = height;
        this.smoothResizing = smoothResizing;
        this.charSpecificSpacing = charSpecificSpacing;
    }

    public static Font loadFromSource(
            final Path folder, final boolean isResource,
            final String baseName, final boolean hasLatinExtended,
            final double whitespaceBreadthMultiplier,
            final int pixelSpacing,
            final boolean smoothResizing,
            final boolean charSpecificSpacing
    ) {
        final Path asciiFilepath = folder.resolve(baseName + ASCII_SUFFIX);

        final Map<Character, Grapheme> characterMap =
                FontLoader.loadASCIIFromSource(asciiFilepath, isResource,
                        whitespaceBreadthMultiplier, charSpecificSpacing);

        // TODO: Each additional charset can be introduced with an analogous code block and font loader
        if (hasLatinExtended) {
            final Path latinExtendedFilepath = folder.resolve(baseName + LATIN_EXTENDED_SUFFIX);
            Map<Character, Grapheme> latinExtendedMap =
                    FontLoader.loadLatinExtendedFromSource(latinExtendedFilepath,
                            isResource, charSpecificSpacing);

            for (Character c : latinExtendedMap.keySet())
                characterMap.put(c, latinExtendedMap.get(c));
        }

        final int height = characterMap.get(' ').height;
        return new Font(characterMap, pixelSpacing, height,
                smoothResizing, charSpecificSpacing);
    }

    public static Font loadFromImages(
            final GameImage ascii, final GameImage latinExtended,
            final double whitespaceBreadthMultiplier,
            final int pixelSpacing,
            final boolean smoothResizing,
            final boolean charSpecificSpacing
    ) {
        final Map<Character, Grapheme> characterMap = FontLoader.loadASCII(
                ascii, whitespaceBreadthMultiplier, charSpecificSpacing);

        if (latinExtended != null && latinExtended.getWidth() >
                GameImage.dummy().getWidth()) {
            final Map<Character, Grapheme> latinExtendedMap = FontLoader
                    .loadLatinExtended(latinExtended, charSpecificSpacing);

            for (Character c : latinExtendedMap.keySet())
                characterMap.put(c, latinExtendedMap.get(c));
        }

        final int height = characterMap.get(' ').height;
        return new Font(characterMap, pixelSpacing, height,
                smoothResizing, charSpecificSpacing);
    }

    public GameImage drawChar(final char c, final Color color) {
        return getGrapheme(c).getImage(color);
    }

    public int getCharWidthRespectiveToNext(final char a, final char b) {
        final int DEFAULT_WIDTH_A = getCharWidth(a);

        if (!(getGrapheme(a).supportsCharSpecificSpacing() &&
                getGrapheme(b).supportsCharSpecificSpacing()))
            return DEFAULT_WIDTH_A;

        final int LEFT = 0, RIGHT = 1;
        int minimum = DEFAULT_WIDTH_A, width = DEFAULT_WIDTH_A;

        final int[][] aWidthComponents = getGrapheme(a).getCharWidthComponents(),
                bWidthComponents = getGrapheme(b).getCharWidthComponents();

        if (aWidthComponents.length != bWidthComponents.length)
            return DEFAULT_WIDTH_A;

        for (int y = 0; y < aWidthComponents.length; y++) {
            if (aWidthComponents[y][RIGHT] == Grapheme.SLICE_CONTAINS_NO_STROKE ||
                    bWidthComponents[y][LEFT] == Grapheme.SLICE_CONTAINS_NO_STROKE)
                continue;

            final int diff = (DEFAULT_WIDTH_A + bWidthComponents[y][LEFT]) - aWidthComponents[y][RIGHT];

            if (diff < minimum && diff >= 0) {
                minimum = diff;
                width = aWidthComponents[y][RIGHT] - bWidthComponents[y][LEFT];

                if (width < DEFAULT_WIDTH_A - (getCharWidth(b) + pixelSpacing))
                    width = DEFAULT_WIDTH_A - (getCharWidth(b) + pixelSpacing);
            }
        }

        return Math.min(width, DEFAULT_WIDTH_A);
    }

    public int getCharWidth(final char c) {
        return getGrapheme(c).width;
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

    public boolean hasCharSpecificSpacing() {
        return charSpecificSpacing;
    }

    private Grapheme getGrapheme(final char c) {
        if (CHARACTER_MAP.containsKey(c))
            return CHARACTER_MAP.get(c);
        else {
            GameError.send("Attempted to draw the character \"" + c +
                            "\", which is unsupported by this font.");

            return CHARACTER_MAP.get(FontConstants.REPLACEMENT);
        }
    }
}
