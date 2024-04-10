package com.jordanbunke.delta_time.fonts;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class FontConstants {
    public static final double DEF_WHITESPACE_MULT = 1d;
    public static final int DEF_PIXEL_SPACING = 1;
    public static final boolean DEF_SMOOTH_RESIZING = false,
            DEF_CHAR_SPECIFIC_SPACING = true;

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

    private static final String
            COMPOSED_CHAR_PREFIX = "[+",
            COMPOSED_CHAR_SUFFIX = "]";

    private static final Map<Character, String>
            composedEquivalencyMap = new HashMap<>(),
            compositionSequenceMap = new HashMap<>();

    // file name components
    public static final String
            FILE_SUFFIX = ".png",
            ASCII_SUFFIX = "-ascii" + FILE_SUFFIX,
            LATIN_EXTENDED_SUFFIX = "-latin-extended" + FILE_SUFFIX;

    static {
        populateCharacterMaps();
    }

    private static void populateCharacterMaps() {
        populateComposedEquivalencyMap();
        populateCompositionSequenceMap();
    }

    private static void populateComposedEquivalencyMap() {
        composedEquivalencyMap.clear();

        composedEquivalencyMap.put(LSC_YO_UPPERCASE_OPEN_E_HIGH_TONE, "Ẹ́");
        composedEquivalencyMap.put(LSC_YO_LOWERCASE_OPEN_E_HIGH_TONE, "ẹ́");
        composedEquivalencyMap.put(LSC_YO_UPPERCASE_OPEN_E_LOW_TONE, "Ẹ̀");
        composedEquivalencyMap.put(LSC_YO_LOWERCASE_OPEN_E_LOW_TONE, "ẹ̀");
        composedEquivalencyMap.put(LSC_YO_UPPERCASE_OPEN_O_HIGH_TONE, "Ọ́");
        composedEquivalencyMap.put(LSC_YO_LOWERCASE_OPEN_O_HIGH_TONE, "ọ́");
        composedEquivalencyMap.put(LSC_YO_UPPERCASE_OPEN_O_LOW_TONE, "Ọ̀");
        composedEquivalencyMap.put(LSC_YO_LOWERCASE_OPEN_O_LOW_TONE, "ọ̀");
    }

    private static void populateCompositionSequenceMap() {
        compositionSequenceMap.clear();

        // composed subdot + tone accent (Yoruba)
        compositionSequenceMap.put(LSC_YO_UPPERCASE_OPEN_E_HIGH_TONE,
                makeCompositionSequence(".E/"));
        compositionSequenceMap.put(LSC_YO_LOWERCASE_OPEN_E_HIGH_TONE,
                makeCompositionSequence(".e/"));
        compositionSequenceMap.put(LSC_YO_UPPERCASE_OPEN_E_LOW_TONE,
                makeCompositionSequence(".E\\"));
        compositionSequenceMap.put(LSC_YO_LOWERCASE_OPEN_E_LOW_TONE,
                makeCompositionSequence(".e\\"));
        compositionSequenceMap.put(LSC_YO_UPPERCASE_OPEN_O_HIGH_TONE,
                makeCompositionSequence(".O/"));
        compositionSequenceMap.put(LSC_YO_LOWERCASE_OPEN_O_HIGH_TONE,
                makeCompositionSequence(".o/"));
        compositionSequenceMap.put(LSC_YO_UPPERCASE_OPEN_O_LOW_TONE,
                makeCompositionSequence(".O\\"));
        compositionSequenceMap.put(LSC_YO_LOWERCASE_OPEN_O_LOW_TONE,
                makeCompositionSequence(".o\\"));

        // subdot
        compositionSequenceMap.put('Ẹ', makeCompositionSequence(".E"));
        compositionSequenceMap.put('ẹ', makeCompositionSequence(".e"));
        compositionSequenceMap.put('Ọ', makeCompositionSequence(".O"));
        compositionSequenceMap.put('ọ', makeCompositionSequence(".o"));
        compositionSequenceMap.put('Ṣ', makeCompositionSequence(".S"));
        compositionSequenceMap.put('ṣ', makeCompositionSequence(".s"));

        // acute
        compositionSequenceMap.put('Á', makeCompositionSequence("A/"));
        compositionSequenceMap.put('á', makeCompositionSequence("a/"));
        compositionSequenceMap.put('É', makeCompositionSequence("E/"));
        compositionSequenceMap.put('é', makeCompositionSequence("e/"));
        compositionSequenceMap.put('Í', makeCompositionSequence("I/"));
        compositionSequenceMap.put('í', makeCompositionSequence("i/"));
        compositionSequenceMap.put('Ó', makeCompositionSequence("O/"));
        compositionSequenceMap.put('ó', makeCompositionSequence("o/"));
        compositionSequenceMap.put('Ú', makeCompositionSequence("U/"));
        compositionSequenceMap.put('ú', makeCompositionSequence("u/"));
        compositionSequenceMap.put('Ń', makeCompositionSequence("N/"));
        compositionSequenceMap.put('ń', makeCompositionSequence("n/"));

        // grave
        compositionSequenceMap.put('À', makeCompositionSequence("A\\"));
        compositionSequenceMap.put('à', makeCompositionSequence("a\\"));
        compositionSequenceMap.put('È', makeCompositionSequence("E\\"));
        compositionSequenceMap.put('è', makeCompositionSequence("e\\"));
        compositionSequenceMap.put('Ì', makeCompositionSequence("I\\"));
        compositionSequenceMap.put('ì', makeCompositionSequence("i\\"));
        compositionSequenceMap.put('Ò', makeCompositionSequence("O\\"));
        compositionSequenceMap.put('ò', makeCompositionSequence("o\\"));
        compositionSequenceMap.put('Ù', makeCompositionSequence("U\\"));
        compositionSequenceMap.put('ù', makeCompositionSequence("u\\"));
        compositionSequenceMap.put('Ǹ', makeCompositionSequence("N\\"));
        compositionSequenceMap.put('ǹ', makeCompositionSequence("n\\"));

        // tilde
        compositionSequenceMap.put('Ã', makeCompositionSequence("A~"));
        compositionSequenceMap.put('ã', makeCompositionSequence("a~"));
        compositionSequenceMap.put('Õ', makeCompositionSequence("O~"));
        compositionSequenceMap.put('õ', makeCompositionSequence("o~"));
        compositionSequenceMap.put('Ñ', makeCompositionSequence("N~"));
        compositionSequenceMap.put('ñ', makeCompositionSequence("n~"));

        // circumflex
        compositionSequenceMap.put('Â', makeCompositionSequence("A^"));
        compositionSequenceMap.put('â', makeCompositionSequence("a^"));
        compositionSequenceMap.put('Ê', makeCompositionSequence("E^"));
        compositionSequenceMap.put('ê', makeCompositionSequence("e^"));
        compositionSequenceMap.put('Î', makeCompositionSequence("I^"));
        compositionSequenceMap.put('î', makeCompositionSequence("i^"));
        compositionSequenceMap.put('Ô', makeCompositionSequence("O^"));
        compositionSequenceMap.put('ô', makeCompositionSequence("o^"));
        compositionSequenceMap.put('Û', makeCompositionSequence("U^"));
        compositionSequenceMap.put('û', makeCompositionSequence("u^"));

        // umlaut
        compositionSequenceMap.put('Ä', makeCompositionSequence("A:"));
        compositionSequenceMap.put('ä', makeCompositionSequence("a:"));
        compositionSequenceMap.put('Ë', makeCompositionSequence("E:"));
        compositionSequenceMap.put('ë', makeCompositionSequence("e:"));
        compositionSequenceMap.put('Ï', makeCompositionSequence("I:"));
        compositionSequenceMap.put('ï', makeCompositionSequence("i:"));
        compositionSequenceMap.put('Ö', makeCompositionSequence("O:"));
        compositionSequenceMap.put('ö', makeCompositionSequence("o:"));
        compositionSequenceMap.put('Ü', makeCompositionSequence("U:"));
        compositionSequenceMap.put('ü', makeCompositionSequence("u:"));

        // cedille / cedilha
        compositionSequenceMap.put('Ç', makeCompositionSequence(",C"));
        compositionSequenceMap.put('ç', makeCompositionSequence(",c"));
        compositionSequenceMap.put('Ş', makeCompositionSequence(",S"));
        compositionSequenceMap.put('ş', makeCompositionSequence(",s"));

        // SPECIAL
        // eszett - (UPPERCASE ESZETT IS NOT A LETTER!)
        compositionSequenceMap.put('ß', makeCompositionSequence("ss"));
        // Turkish silent Gs
        compositionSequenceMap.put('Ğ', makeCompositionSequence("G("));
        compositionSequenceMap.put('ğ', makeCompositionSequence("g("));
        // Turkish special Is
        compositionSequenceMap.put('İ', makeCompositionSequence("I."));
        compositionSequenceMap.put('ı', makeCompositionSequence("i."));
        // ash
        compositionSequenceMap.put('Æ', makeCompositionSequence("AE"));
        compositionSequenceMap.put('æ', makeCompositionSequence("ae"));
        // ringed a
        compositionSequenceMap.put('Å', makeCompositionSequence("A0"));
        compositionSequenceMap.put('å', makeCompositionSequence("a0"));
        // o with slash
        compositionSequenceMap.put('Ø', makeCompositionSequence("O|"));
        compositionSequenceMap.put('ø', makeCompositionSequence("o|"));
    }

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
