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
                    Map.entry('Ẹ', makeCompositionSequence("E.")),
                    Map.entry('ẹ', makeCompositionSequence("e.")),
                    Map.entry('Ọ', makeCompositionSequence("O.")),
                    Map.entry('ọ', makeCompositionSequence("o.")),
                    Map.entry('Ṣ', makeCompositionSequence("S.")),
                    Map.entry('ṣ', makeCompositionSequence("s.")),
                    Map.entry('À', makeCompositionSequence("A\\")),
                    Map.entry('à', makeCompositionSequence("a\\")),
                    Map.entry('Á', makeCompositionSequence("A/")),
                    Map.entry('á', makeCompositionSequence("a/")),
                    Map.entry('È', makeCompositionSequence("E\\")),
                    Map.entry('è', makeCompositionSequence("e\\")),
                    Map.entry('É', makeCompositionSequence("E/")),
                    Map.entry('é', makeCompositionSequence("e/")),
                    Map.entry('Ì', makeCompositionSequence("I\\")),
                    Map.entry('ì', makeCompositionSequence("i\\")),
                    Map.entry('Í', makeCompositionSequence("I/")),
                    Map.entry('í', makeCompositionSequence("i/")),
                    Map.entry('Ò', makeCompositionSequence("O\\")),
                    Map.entry('ò', makeCompositionSequence("o\\")),
                    Map.entry('Ó', makeCompositionSequence("O/")),
                    Map.entry('ó', makeCompositionSequence("o/")),
                    Map.entry('Ù', makeCompositionSequence("U\\")),
                    Map.entry('ù', makeCompositionSequence("u\\")),
                    Map.entry('Ú', makeCompositionSequence("U/")),
                    Map.entry('ú', makeCompositionSequence("u/")),
                    Map.entry('Ǹ', makeCompositionSequence("N\\")),
                    Map.entry('ǹ', makeCompositionSequence("n\\")),
                    Map.entry('Ń', makeCompositionSequence("N/")),
                    Map.entry('ń', makeCompositionSequence("n/"))
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
