package com.jordanbunke.jbjgl.fonts;

import com.jordanbunke.jbjgl.image.ImageProcessing;
import com.jordanbunke.jbjgl.image.JBJGLImage;
import com.jordanbunke.jbjgl.io.JBJGLImageIO;

import java.awt.*;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

import static com.jordanbunke.jbjgl.fonts.FontConstants.LINE_HEIGHT;
import static com.jordanbunke.jbjgl.fonts.FontConstants.MATCH_COLOR;

public class FontLoader {
    private static final int X_INCREMENT = 20, Y_INCREMENT = 38;
    private static final int X_INDEX = 0, Y_INDEX = 1;

    private static final char STARTING_ASCII = 33, FINAL_ASCII = 126;
    private static final int NUM_LATIN_EXTENDED_CHARS = 59;
    private static final int CHARS_ON_ROW = 16;

    public static Map<Character, Grapheme> loadASCIIFromSource(final Path source) {
        JBJGLImage image = JBJGLImageIO.readImage(source);
        Map<Character, Grapheme> map = new HashMap<>();

        map.put(' ', whitespace());

        for (char c = STARTING_ASCII; c <= FINAL_ASCII; c++) {
            int[] coordinates = asciiToCoordinates(c);

            if (coordinates.length != 2) continue;

            Grapheme grapheme = graphemeFromCoordinates(image, c, coordinates);
            map.put(c, grapheme);
        }

        return map;
    }

    public static Map<Character, Grapheme> loadLatinExtendedFromSource(final Path source) {
        JBJGLImage image = JBJGLImageIO.readImage(source);
        Map<Character, Grapheme> map = new HashMap<>();

        for (int i = 0; i < NUM_LATIN_EXTENDED_CHARS; i++) {
            int[] coordinates = asciiToCoordinates((char) i);

            if (coordinates.length != 2) continue;

            char c = charFromIndexLatinExtended(i);

            Grapheme grapheme = graphemeFromCoordinates(image, c, coordinates);
            map.put(c, grapheme);
        }

        return map;
    }

    // HELPER FUNCTIONS:
    private static Grapheme graphemeFromCoordinates(final JBJGLImage image,
                                                    final char c, final int[] coordinates) {
        final int[] start = new int[]
                { coordinates[X_INDEX] * X_INCREMENT, coordinates[Y_INDEX] * Y_INCREMENT };
        final int[] size = new int[] { X_INCREMENT - 1, Y_INCREMENT - 1 };

        // BOUNDS DETERMINATION
        int firstXWith = firstXWith(image, start, size);
        int firstXWithout = firstXWithout(image, start, size);

        // COPY
        JBJGLImage grapheme = getGrapheme(image, start, size, firstXWith, firstXWithout);

        return Grapheme.create(grapheme, c, firstXWithout - firstXWith, size[Y_INDEX]);
    }

    private static JBJGLImage getGrapheme(final JBJGLImage image, final int[] start, final int[] size,
                                             final int firstXWith, final int firstXWithout) {
        JBJGLImage grapheme = JBJGLImage.create(firstXWithout - firstXWith, size[Y_INDEX]);
        Graphics g = grapheme.getGraphics();
        g.setColor(MATCH_COLOR);

        for (int x = firstXWith; x < firstXWithout; x++) {
            for (int y = start[Y_INDEX]; y < start[Y_INDEX] + size[Y_INDEX]; y++) {
                if (ImageProcessing.colorAtPixel(image, x, y).equals(MATCH_COLOR)) {
                    g.fillRect(x - firstXWith, y - start[Y_INDEX], 1, 1);
                }
            }
        }

        g.dispose();

        return grapheme;
    }

    private static Grapheme whitespace() {
        final int width = 8;
        return Grapheme.create(JBJGLImage.create(width, LINE_HEIGHT), ' ', width, LINE_HEIGHT);
    }

    private static int firstXWithout(final JBJGLImage image, final int[] start, final int[] size) {
        for (int x = (start[X_INDEX] + size[X_INDEX]) - 1; x >= start[X_INDEX]; x--) {
            for (int y = start[Y_INDEX]; y < start[Y_INDEX] + size[Y_INDEX]; y++) {
                if (ImageProcessing.colorAtPixel(image, x, y).equals(MATCH_COLOR)) {
                    return x + 1;
                }
            }
        }

        return start[X_INDEX] + size[X_INDEX];
    }

    private static int firstXWith(final JBJGLImage image, final int[] start, final int[] size) {
        for (int x = start[X_INDEX]; x < start[X_INDEX] + size[X_INDEX]; x++) {
            for (int y = start[Y_INDEX]; y < start[Y_INDEX] + size[Y_INDEX]; y++) {
                if (ImageProcessing.colorAtPixel(image, x, y).equals(MATCH_COLOR)) {
                    return x;
                }
            }
        }

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
            default -> '�';
        };
    }

    private static int[] asciiToCoordinates(final char c) {
        final int x = c % CHARS_ON_ROW, y = c / CHARS_ON_ROW;

        return new int[] { x, y };
    }
}
