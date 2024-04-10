package com.jordanbunke.delta_time.fonts;

import com.jordanbunke.delta_time.About;
import com.jordanbunke.delta_time.error.GameError;
import com.jordanbunke.delta_time.image.GameImage;
import com.jordanbunke.delta_time.io.ResourceLoader;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public final class DeltaTimeFonts {
    private static final Path SOURCES_FOLDER = Path.of("font_sources");

    public static final String DELTAN = "deltan", SKINNY = "skinny";
    private static final Map<String, FontSources> SOURCES_MAP = new HashMap<>();

    static {
        final String[] fontCodes = new String[] {
                DELTAN, SKINNY
        };

        for (String fontCode : fontCodes)
            SOURCES_MAP.put(fontCode, fromCode(fontCode));
    }

    public static Font buildFontFromCode(final String fontCode) {
        return buildFontFromCode(fontCode,
                FontConstants.DEF_WHITESPACE_MULT,
                FontConstants.DEF_PIXEL_SPACING,
                FontConstants.DEF_SMOOTH_RESIZING,
                FontConstants.DEF_CHAR_SPECIFIC_SPACING);
    }

    public static Font buildFontFromCode(final String fontCode,
            final double whitespaceBreadthMultiplier, final int pixelSpacing,
            final boolean smoothResizing, final boolean charSpecificSpacing
    ) {
        final String validatedCode;

        if (SOURCES_MAP.containsKey(fontCode))
            validatedCode = fontCode;
        else {
            validatedCode = DELTAN;
            GameError.send("The ont code \"" + fontCode + "\" is invalid. " +
                    About.TITLE + " has defaulted to the font code \"" +
                    validatedCode + "\".");
        }

        final FontSources fontSources = SOURCES_MAP.get(validatedCode);

        return Font.loadFromImages(
                fontSources.ascii(), fontSources.latinExtended(),
                whitespaceBreadthMultiplier, pixelSpacing,
                smoothResizing, charSpecificSpacing);
    }

    public static Font getDefault() {
        return buildFontFromCode(DELTAN);
    }

    private static FontSources fromCode(final String fontCode) {
        final GameImage ascii = ResourceLoader.loadImageResource(
                SOURCES_FOLDER.resolve(fontCode +
                        FontConstants.ASCII_SUFFIX)),
                latinExtended = ResourceLoader.loadImageResource(
                        SOURCES_FOLDER.resolve(fontCode +
                                FontConstants.LATIN_EXTENDED_SUFFIX));

        return new FontSources(ascii, latinExtended);
    }
}
