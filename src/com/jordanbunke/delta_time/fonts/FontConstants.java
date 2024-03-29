package com.jordanbunke.delta_time.fonts;

import java.awt.*;

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
            LSC_YO_UPPERCASE_OPEN_E_LOW_TONE = '\ue001',
            LSC_YO_UPPERCASE_N_HIGH_TONE = '\ue002',
            LSC_YO_UPPERCASE_N_LOW_TONE = '\ue003',
            LSC_YO_UPPERCASE_OPEN_O_HIGH_TONE = '\ue004',
            LSC_YO_UPPERCASE_OPEN_O_LOW_TONE = '\ue005',

            LSC_YO_LOWERCASE_OPEN_E_HIGH_TONE = '\ue010',
            LSC_YO_LOWERCASE_OPEN_E_LOW_TONE = '\ue011',
            LSC_YO_LOWERCASE_N_HIGH_TONE = '\ue012',
            LSC_YO_LOWERCASE_N_LOW_TONE = '\ue013',
            LSC_YO_LOWERCASE_OPEN_O_HIGH_TONE = '\ue014',
            LSC_YO_LOWERCASE_OPEN_O_LOW_TONE = '\ue015';

    // file name components
    public static final String
            FILE_SUFFIX = ".png",
            ASCII_SUFFIX = "-ascii" + FILE_SUFFIX,
            LATIN_EXTENDED_SUFFIX = "-latin-extended" + FILE_SUFFIX;
}
