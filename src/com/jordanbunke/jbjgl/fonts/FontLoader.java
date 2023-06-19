package com.jordanbunke.jbjgl.fonts;

import com.jordanbunke.jbjgl.error.GameError;
import com.jordanbunke.jbjgl.image.ImageProcessing;
import com.jordanbunke.jbjgl.image.GameImage;
import com.jordanbunke.jbjgl.io.GameImageIO;
import com.jordanbunke.jbjgl.io.ResourceLoader;

import java.awt.*;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

import static com.jordanbunke.jbjgl.fonts.FontConstants.*;

public class FontLoader {
    private static final int X_INCREMENT = 20, Y_INCREMENT = 38;
    private static final int X_INDEX = 0, Y_INDEX = 1;

    private static final char STARTING_ASCII = 33, FINAL_ASCII = 126;
    private static final int NUM_LATIN_EXTENDED_CHARS = 59;
    private static final int CHARS_ON_ROW = 16;
    private static final int BASE_WHITESPACE_BREADTH = 8;

    public static Map<Character, Grapheme> loadASCIIFromSource(
            final Path source, final boolean isResource,
            final double whitespaceBreadthMultiplier
    ) {
        GameImage image = isResource
                ? ResourceLoader.loadImageResource(source)
                : GameImageIO.readImage(source);
        Map<Character, Grapheme> map = new HashMap<>();

        final int scaleMultiplier = sourceToScaleMultiplier(image);

        // manual insertion of space character
        map.put(' ', whitespace(scaleMultiplier, whitespaceBreadthMultiplier));

        // manual insertion of replacement character
        final Grapheme replacementGrapheme = graphemeFromCoordinates(
                image, REPLACEMENT,
                asciiToCoordinates((char)127), scaleMultiplier);
        map.put(REPLACEMENT, replacementGrapheme);

        for (char c = STARTING_ASCII; c <= FINAL_ASCII; c++) {
            int[] coordinates = asciiToCoordinates(c);

            if (coordinates.length != 2) continue;

            final Grapheme grapheme = graphemeFromCoordinates(image, c, coordinates, scaleMultiplier);
            map.put(c, grapheme);
        }

        return map;
    }

    public static Map<Character, Grapheme> loadLatinExtendedFromSource(
            final Path source, final boolean isResource) {
        GameImage image = isResource
                ? ResourceLoader.loadImageResource(source)
                : GameImageIO.readImage(source);
        Map<Character, Grapheme> map = new HashMap<>();

        final int scaleMultiplier = sourceToScaleMultiplier(image);

        for (int i = 0; i < NUM_LATIN_EXTENDED_CHARS; i++) {
            int[] coordinates = asciiToCoordinates((char) i);
            char c = charFromIndexLatinExtended(i);

            if (coordinates.length != 2 || c == REPLACEMENT) continue;

            Grapheme grapheme = graphemeFromCoordinates(image, c, coordinates, scaleMultiplier);
            map.put(c, grapheme);
        }

        return map;
    }

    // HELPER FUNCTIONS:
    private static Grapheme graphemeFromCoordinates(
            final GameImage image, final char c, final int[] coordinates,
            final int scaleMultiplier
    ) {
        final int[] start = new int[] {
                coordinates[X_INDEX] * X_INCREMENT * scaleMultiplier,
                coordinates[Y_INDEX] * Y_INCREMENT * scaleMultiplier
        };
        final int[] size = new int[] {
                (X_INCREMENT - 1) * scaleMultiplier,
                (Y_INCREMENT - 1) * scaleMultiplier
        };

        // BOUNDS DETERMINATION
        int firstXWith = firstXWith(image, start, size);
        int firstXWithout = firstXWithout(image, start, size);

        // COPY
        GameImage grapheme = retrieveGrapheme(image, start, size, firstXWith, firstXWithout);

        return new Grapheme(grapheme, c, firstXWithout - firstXWith, size[Y_INDEX]);
    }

    private static GameImage retrieveGrapheme(
            final GameImage image, final int[] start, final int[] size,
            final int firstXWith, final int firstXWithout
    ) {
        GameImage grapheme = new GameImage(firstXWithout - firstXWith, size[Y_INDEX]);
        Graphics g = grapheme.getGraphics();
        g.setColor(MATCH_COLOR);

        for (int x = firstXWith; x < firstXWithout; x++)
            for (int y = start[Y_INDEX]; y < start[Y_INDEX] + size[Y_INDEX]; y++)
                if (ImageProcessing.colorAtPixel(image, x, y).equals(MATCH_COLOR))
                    g.fillRect(x - firstXWith, y - start[Y_INDEX], 1, 1);

        g.dispose();

        return grapheme;
    }

    private static Grapheme whitespace(
            final int scaleMultiplier, final double whitespaceBreadthMultiplier
    ) {
        final int width = (int)(BASE_WHITESPACE_BREADTH * whitespaceBreadthMultiplier * scaleMultiplier);
        return new Grapheme(new GameImage(width, LINE_HEIGHT * scaleMultiplier),
                ' ', width, LINE_HEIGHT * scaleMultiplier);
    }

    private static int firstXWithout(final GameImage image, final int[] start, final int[] size) {
        for (int x = (start[X_INDEX] + size[X_INDEX]) - 1; x >= start[X_INDEX]; x--)
            for (int y = start[Y_INDEX]; y < start[Y_INDEX] + size[Y_INDEX]; y++)
                if (ImageProcessing.colorAtPixel(image, x, y).equals(MATCH_COLOR))
                    return x + 1;

        return start[X_INDEX] + size[X_INDEX];
    }

    private static int firstXWith(final GameImage image, final int[] start, final int[] size) {
        for (int x = start[X_INDEX]; x < start[X_INDEX] + size[X_INDEX]; x++)
            for (int y = start[Y_INDEX]; y < start[Y_INDEX] + size[Y_INDEX]; y++)
                if (ImageProcessing.colorAtPixel(image, x, y).equals(MATCH_COLOR))
                    return x;

        return start[X_INDEX];
    }

    private static char charFromIndexLatinExtended(int i) {
        return switch (i) {
            case 0 -> 'Ä';
            case 1 -> 'Á';
            case 2 -> 'À';
            case 3 -> 'Â';
            case 4 -> 'Ã';
            case 5 -> 'Ç';
            case 6 -> 'Ë';
            case 7 -> 'É';
            case 8 -> 'È';
            case 9 -> 'Ê';
            case 10 -> 'Ğ';
            case 11 -> 'Ï';
            case 12 -> 'Í';
            case 13 -> 'Ì';
            case 14 -> 'Î';
            case 15 -> 'İ';
            case 16 -> 'Ñ';
            case 17 -> 'Ö';
            case 18 -> 'Ó';
            case 19 -> 'Ò';
            case 20 -> 'Ô';
            case 21 -> 'Õ';
            case 22 -> 'Ş';
            case 23 -> 'Ü';
            case 24 -> 'Ú';
            case 25 -> 'Ù';
            case 26 -> 'Û';
            // GAP
            case 32 -> 'ä';
            case 33 -> 'á';
            case 34 -> 'à';
            case 35 -> 'â';
            case 36 -> 'ã';
            case 37 -> 'ç';
            case 38 -> 'ë';
            case 39 -> 'é';
            case 40 -> 'è';
            case 41 -> 'ê';
            case 42 -> 'ğ';
            case 43 -> 'ï';
            case 44 -> 'í';
            case 45 -> 'ì';
            case 46 -> 'î';
            case 47 -> 'ı';
            case 48 -> 'ñ';
            case 49 -> 'ö';
            case 50 -> 'ó';
            case 51 -> 'ò';
            case 52 -> 'ô';
            case 53 -> 'õ';
            case 54 -> 'ş';
            case 55 -> 'ü';
            case 56 -> 'ú';
            case 57 -> 'ù';
            case 58 -> 'û';
            default -> REPLACEMENT;
        };
    }

    private static int[] asciiToCoordinates(final char c) {
        final int x = c % CHARS_ON_ROW, y = c / CHARS_ON_ROW;

        return new int[] { x, y };
    }

    private static int sourceToScaleMultiplier(final GameImage source) {
        final int width = source.getWidth(), height = source.getHeight();

        final boolean isMultiple = width % FONT_SOURCE_BASE_WIDTH == 0,
                isProportional = width / (double) FONT_SOURCE_BASE_WIDTH ==
                        height / (double) FONT_SOURCE_BASE_HEIGHT;

        if (!(isMultiple && isProportional))
            GameError.send(
                    "Source image file is not sized correctly, must be multiple of " +
                            FONT_SOURCE_BASE_WIDTH + "x" + FONT_SOURCE_BASE_HEIGHT);

        return width / FONT_SOURCE_BASE_WIDTH;
    }
}
