package com.jordanbunke.delta_time.fonts;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class FontConstants {
    public static final Color
            FONT_PRIMARY_COLOR = new Color(255, 0, 0, 255),
            FONT_SPACING_BOUND_COLOR = new Color(0, 255, 0, 255);
    public static final int
            FONT_SOURCE_BASE_WIDTH = 319,
            FONT_SOURCE_BASE_HEIGHT = 303,
            LINE_HEIGHT = 37;
    public static final char REPLACEMENT = '�';

    // language supplement characters
    public static final char
            LSC_YO_UPPERCASE_OPEN_E_HIGH_TONE = '\ue000',
            LSC_YO_LOWERCASE_OPEN_E_HIGH_TONE = '\ue001',
            LSC_YO_UPPERCASE_OPEN_E_LOW_TONE = '\ue002',
            LSC_YO_LOWERCASE_OPEN_E_LOW_TONE = '\ue003',
            LSC_YO_UPPERCASE_OPEN_O_HIGH_TONE = '\ue004',
            LSC_YO_LOWERCASE_OPEN_O_HIGH_TONE = '\ue005',
            LSC_YO_UPPERCASE_OPEN_O_LOW_TONE = '\ue006',
            LSC_YO_LOWERCASE_OPEN_O_LOW_TONE = '\ue007';

    private static final Map<Character, String> composedEquivalencyMap =
            Map.ofEntries(
                    Map.entry(LSC_YO_UPPERCASE_OPEN_E_HIGH_TONE, "Ẹ́"),
                    Map.entry(LSC_YO_LOWERCASE_OPEN_E_HIGH_TONE, "ẹ́"),
                    Map.entry(LSC_YO_UPPERCASE_OPEN_E_LOW_TONE, "Ẹ̀"),
                    Map.entry(LSC_YO_LOWERCASE_OPEN_E_LOW_TONE, "ẹ̀"),
                    Map.entry(LSC_YO_UPPERCASE_OPEN_O_HIGH_TONE, "Ọ́"),
                    Map.entry(LSC_YO_LOWERCASE_OPEN_O_HIGH_TONE, "ọ́"),
                    Map.entry(LSC_YO_UPPERCASE_OPEN_O_LOW_TONE, "Ọ̀"),
                    Map.entry(LSC_YO_LOWERCASE_OPEN_O_LOW_TONE, "ọ̀"));

    private static final String
            COMPOSED_CHAR_PREFIX = "[++",
            COMPOSED_CHAR_SUFFIX = "++]";

    private static final Map<Character, String> compositionSequenceMap =
            Map.ofEntries(
                    Map.entry(LSC_YO_UPPERCASE_OPEN_E_HIGH_TONE,
                            makeCompositionSequence("E./")),
                    Map.entry(LSC_YO_LOWERCASE_OPEN_E_HIGH_TONE,
                            makeCompositionSequence("e./")),
                    Map.entry(LSC_YO_UPPERCASE_OPEN_E_LOW_TONE,
                            makeCompositionSequence("E.\\")),
                    Map.entry(LSC_YO_LOWERCASE_OPEN_E_LOW_TONE,
                            makeCompositionSequence("e.\\")),
                    Map.entry(LSC_YO_UPPERCASE_OPEN_O_HIGH_TONE,
                            makeCompositionSequence("O./")),
                    Map.entry(LSC_YO_LOWERCASE_OPEN_O_HIGH_TONE,
                            makeCompositionSequence("o./")),
                    Map.entry(LSC_YO_UPPERCASE_OPEN_O_LOW_TONE,
                            makeCompositionSequence("O.\\")),
                    Map.entry(LSC_YO_LOWERCASE_OPEN_O_LOW_TONE,
                            makeCompositionSequence("o.\\")),
                    Map.entry('Ẹ', "E."), Map.entry('ẹ', "e."),
                    Map.entry('Ọ', "O."), Map.entry('ọ', "o."),
                    Map.entry('Ṣ', "S."), Map.entry('ṣ', "s."),
                    Map.entry('À', "A\\"), Map.entry('à', "a\\"),
                    Map.entry('Á', "A/"), Map.entry('á', "a/"),
                    Map.entry('È', "E\\"), Map.entry('è', "e\\"),
                    Map.entry('É', "E/"), Map.entry('é', "e/"),
                    Map.entry('Ì', "I\\"), Map.entry('ì', "i\\"),
                    Map.entry('Í', "I/"), Map.entry('í', "i/"),
                    Map.entry('Ò', "O\\"), Map.entry('ò', "o\\"),
                    Map.entry('Ó', "O/"), Map.entry('ó', "o/"),
                    Map.entry('Ù', "U\\"), Map.entry('ù', "u\\"),
                    Map.entry('Ú', "U/"), Map.entry('ú', "u/"),
                    Map.entry('Ǹ', "N\\"), Map.entry('ǹ', "n\\"),
                    Map.entry('Ń', "N/"), Map.entry('ń', "n/")
            );

    // file name components
    public static final String
            FILE_SUFFIX = ".png",
            ASCII_SUFFIX = "-ascii" + FILE_SUFFIX,
            LATIN_EXTENDED_SUFFIX = "-latin-extended" + FILE_SUFFIX;

    public static Map<Character, String> getComposedEquivalencyMap() {
        return new HashMap<>(composedEquivalencyMap);
    }

    public static Map<Character, String> getCompositionSequenceMap() {
        return new HashMap<>(compositionSequenceMap);
    }

    private static String makeCompositionSequence(final String core) {
        return COMPOSED_CHAR_PREFIX + core + COMPOSED_CHAR_SUFFIX;
    }
}
