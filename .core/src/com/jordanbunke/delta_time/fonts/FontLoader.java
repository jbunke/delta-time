package com.jordanbunke.delta_time.fonts;

import com.jordanbunke.delta_time.error.GameError;
import com.jordanbunke.delta_time.image.GameImage;
import com.jordanbunke.delta_time.image.ImageProcessing;
import com.jordanbunke.delta_time.io.GameImageIO;
import com.jordanbunke.delta_time.io.ResourceLoader;

import java.awt.*;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import static com.jordanbunke.delta_time.fonts.FontConstants.*;

public class FontLoader {
    private static final int X_INCREMENT = 20, Y_INCREMENT = 38;
    private static final int X_INDEX = 0, Y_INDEX = 1;

    private static final char STARTING_ASCII = 33, FINAL_ASCII = 126;
    private static final int
            LAST_GRID_SLOT = 127,
            CHARS_ON_ROW = 16;
    private static final int BASE_WHITESPACE_BREADTH = 8;

    public static Map<Character, Grapheme> loadASCIIFromSource(
            final Path source, final boolean isResource,
            final double whitespaceBreadthMultiplier,
            final boolean charSpecificSpacing
    ) {
        final GameImage image = isResource
                ? ResourceLoader.loadImageResource(source)
                : GameImageIO.readImage(source);

        return loadASCII(image, whitespaceBreadthMultiplier,
                charSpecificSpacing);
    }

    public static Map<Character, Grapheme> loadASCII(
            final GameImage image, final double whitespaceBreadthMultiplier,
            final boolean charSpecificSpacing
    ) {
        final Map<Character, Grapheme> map = new HashMap<>();
        final int scaleMultiplier = sourceToScaleMultiplier(image);

        // manual insertion of space character
        map.put(' ', whitespace(scaleMultiplier, whitespaceBreadthMultiplier));

        // manual insertion of replacement character
        final Grapheme replacementGrapheme = graphemeFromCoordinates(
                image, REPLACEMENT, asciiToCoordinates((char) LAST_GRID_SLOT),
                scaleMultiplier, charSpecificSpacing);
        map.put(REPLACEMENT, replacementGrapheme);

        mapLoader(map, image, charSpecificSpacing, STARTING_ASCII,
                FINAL_ASCII, i -> (char) i.intValue(), c -> false);

        return map;
    }

    public static Map<Character, Grapheme> loadLatinExtendedFromSource(
            final Path source, final boolean isResource,
            final boolean charSpecificSpacing) {
        final GameImage image = isResource
                ? ResourceLoader.loadImageResource(source)
                : GameImageIO.readImage(source);

        return loadLatinExtended(image, charSpecificSpacing);
    }

    public static Map<Character, Grapheme> loadLatinExtended(
            final GameImage image, final boolean charSpecificSpacing
    ) {
        final Map<Character, Grapheme> map = new HashMap<>();

        mapLoader(map, image, charSpecificSpacing, 0, LAST_GRID_SLOT,
                FontLoader::charFromIndexLatinExtended,
                c -> c == REPLACEMENT);

        return map;
    }

    // HELPER FUNCTIONS:
    private static void mapLoader(
            final Map<Character, Grapheme> map,
            final GameImage image, final boolean charSpecificSpacing,
            final int start, final int end,
            final Function<Integer, Character> characterRetriever,
            final Function<Character, Boolean> ignoreCondition
    ) {
        final int scaleMultiplier = sourceToScaleMultiplier(image);

        for (int i = start; i <= end; i++) {
            int[] coordinates = asciiToCoordinates((char) i);
            char c = characterRetriever.apply(i);

            if (coordinates.length != 2 || ignoreCondition.apply(c))
                continue;

            final Grapheme grapheme =
                    graphemeFromCoordinates(image, c, coordinates,
                            scaleMultiplier, charSpecificSpacing);
            map.put(c, grapheme);
        }
    }

    private static Grapheme graphemeFromCoordinates(
            final GameImage image, final char c, final int[] coordinates,
            final int scaleMultiplier, final boolean charSpecificSpacing
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

        // CHARACTER SPECIFIC SPACING
        final int[][] charWidthComponents = charSpecificSpacing
                ? determineCharWidthComponents(image, start, size, firstXWith, firstXWithout, scaleMultiplier)
                : null;

        return new Grapheme(grapheme, c, firstXWithout - firstXWith, size[Y_INDEX], charWidthComponents);
    }

    private static GameImage retrieveGrapheme(
            final GameImage image, final int[] start, final int[] size,
            final int firstXWith, final int firstXWithout
    ) {
        GameImage grapheme = new GameImage(firstXWithout - firstXWith, size[Y_INDEX]);
        grapheme.setColor(FONT_PRIMARY_COLOR);

        for (int x = firstXWith; x < firstXWithout; x++)
            for (int y = start[Y_INDEX]; y < start[Y_INDEX] + size[Y_INDEX]; y++)
                if (image.getColorAt(x, y).equals(FONT_PRIMARY_COLOR))
                    grapheme.fillRectangle(x - firstXWith, y - start[Y_INDEX], 1, 1);

        return grapheme.submit();
    }

    private static Grapheme whitespace(
            final int scaleMultiplier, final double whitespaceBreadthMultiplier
    ) {
        final int width = (int)(BASE_WHITESPACE_BREADTH * whitespaceBreadthMultiplier * scaleMultiplier);
        return new Grapheme(new GameImage(width, LINE_HEIGHT * scaleMultiplier),
                ' ', width, LINE_HEIGHT * scaleMultiplier, null);
    }

    private static int[][] determineCharWidthComponents(
            final GameImage image, final int[] start, final int[] size,
            final int firstXWith, final int firstXWithout, final int scaleMultiplier
    ) {
        final int LEFT = 0, RIGHT = 1, PAIR = 2;
        final int[][] charWidthComponents = new int[size[Y_INDEX] / scaleMultiplier][PAIR];

        for (int y = start[Y_INDEX]; y < start[Y_INDEX] + size[Y_INDEX]; y += scaleMultiplier) {
            final int yIndex = (y - start[Y_INDEX]) / scaleMultiplier;

            // LEFT
            for (int x = firstXWith; x < firstXWithout; x++) {
                if (isAFontColor(image.getColorAt(x, y))) {
                    charWidthComponents[yIndex][LEFT] = x - firstXWith;
                    break;
                }

                if (x + 1 >= firstXWithout)
                    charWidthComponents[yIndex][LEFT] = Grapheme.SLICE_CONTAINS_NO_STROKE;
            }

            // RIGHT
            for (int x = firstXWithout - 1; x >= firstXWith; x--) {
                if (isAFontColor(image.getColorAt(x, y))) {
                    charWidthComponents[yIndex][RIGHT] = (x - firstXWith) + 1; // potential bug (term + 1 vs term)
                    break;
                }

                if (x - 1 < firstXWith)
                    charWidthComponents[yIndex][RIGHT] = Grapheme.SLICE_CONTAINS_NO_STROKE;
            }
        }

        return charWidthComponents;
    }

    private static boolean isAFontColor(final Color toCheck) {
        return toCheck.equals(FONT_PRIMARY_COLOR) || toCheck.equals(FONT_SPACING_BOUND_COLOR);
    }

    private static int firstXWithout(final GameImage image, final int[] start, final int[] size) {
        for (int x = (start[X_INDEX] + size[X_INDEX]) - 1; x >= start[X_INDEX]; x--)
            for (int y = start[Y_INDEX]; y < start[Y_INDEX] + size[Y_INDEX]; y++)
                if (isAFontColor(image.getColorAt(x, y)))
                    return x + 1;

        return start[X_INDEX] + size[X_INDEX];
    }

    private static int firstXWith(final GameImage image, final int[] start, final int[] size) {
        for (int x = start[X_INDEX]; x < start[X_INDEX] + size[X_INDEX]; x++)
            for (int y = start[Y_INDEX]; y < start[Y_INDEX] + size[Y_INDEX]; y++)
                if (isAFontColor(image.getColorAt(x, y)))
                    return x;

        return start[X_INDEX];
    }

    private static char charFromIndexLatinExtended(int i) {
        return switch (i) {
            // row 1
            case 0 -> 'Ä';
            case 1 -> 'Á';
            case 2 -> 'À';
            case 3 -> 'Â';
            case 4 -> 'Ã';
            case 5 -> 'Å';
            case 6 -> 'Æ';

            case 9 -> 'Ë';
            case 10 -> 'É';
            case 11 -> 'È';
            case 12 -> 'Ê';
            case 13 -> 'Ẹ';

            // row 2
            case 16 -> 'Ï';
            case 17 -> 'Í';
            case 18 -> 'Ì';
            case 19 -> 'Î';
            case 20 -> 'İ';
            case 21 -> 'Ị';

            case 24 -> 'Ö';
            case 25 -> 'Ó';
            case 26 -> 'Ò';
            case 27 -> 'Ô';
            case 28 -> 'Õ';
            case 29 -> 'Ọ';
            case 30 -> 'Ø';

            // row 3
            case 32 -> 'Ü';
            case 33 -> 'Ú';
            case 34 -> 'Ù';
            case 35 -> 'Û';
            case 36 -> 'Ụ';

            case 39 -> 'Ç';

            case 43 -> 'Ğ';

            // row 4
            case 48 -> 'Ñ';

            case 51 -> 'Ş';
            case 52 -> 'Ṣ';

            // precomposed Yoruba capitals
            case 58 -> LSC_YO_UPPERCASE_OPEN_E_HIGH_TONE;
            case 59 -> LSC_YO_UPPERCASE_OPEN_E_LOW_TONE;
            case 60 -> 'Ń';
            case 61 -> 'Ǹ';
            case 62 -> LSC_YO_UPPERCASE_OPEN_O_HIGH_TONE;
            case 63 -> LSC_YO_UPPERCASE_OPEN_O_LOW_TONE;

            // row 5
            case 64 -> 'ä';
            case 65 -> 'á';
            case 66 -> 'à';
            case 67 -> 'â';
            case 68 -> 'ã';
            case 69 -> 'å';
            case 70 -> 'æ';

            case 73 -> 'ë';
            case 74 -> 'é';
            case 75 -> 'è';
            case 76 -> 'ê';
            case 77 -> 'ẹ';

            // row 6
            case 80 -> 'ï';
            case 81 -> 'í';
            case 82 -> 'ì';
            case 83 -> 'î';
            case 84 -> 'ı';
            case 85 -> 'ị';

            case 88 -> 'ö';
            case 89 -> 'ó';
            case 90 -> 'ò';
            case 91 -> 'ô';
            case 92 -> 'õ';
            case 93 -> 'ọ';
            case 94 -> 'ø';

            // row 7
            case 96 -> 'ü';
            case 97 -> 'ú';
            case 98 -> 'ù';
            case 99 -> 'û';
            case 100 -> 'ụ';

            case 103 -> 'ç';

            case 107 -> 'ğ';

            // row 8
            case 112 -> 'ñ';

            case 115 -> 'ş';
            case 116 -> 'ṣ';
            case 117 -> 'ß';

            // precomposed Yoruba lowercase
            case 122 -> LSC_YO_LOWERCASE_OPEN_E_HIGH_TONE;
            case 123 -> LSC_YO_LOWERCASE_OPEN_E_LOW_TONE;
            case 124 -> 'ń';
            case 125 -> 'ǹ';
            case 126 -> LSC_YO_LOWERCASE_OPEN_O_HIGH_TONE;
            case 127 -> LSC_YO_LOWERCASE_OPEN_O_LOW_TONE;

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
