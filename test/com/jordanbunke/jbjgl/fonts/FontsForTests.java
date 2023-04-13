package com.jordanbunke.jbjgl.fonts;

import java.nio.file.Path;
import java.nio.file.Paths;

public class FontsForTests {
    private static final Path FONT_PATH = Paths.get("test_resources", "font_files");

    public static final FontFamily
            BASIC = FontFamily.loadFromSources(
                    "Basic", FONT_PATH, "font-basic",
            "font-basic-bold", FontFamily.NOT_AVAILABLE,
            2, 2, 0,true),
            CLASSIC = FontFamily.loadFromSources(
                    "Classic", FONT_PATH, "font-classic",
                    FontFamily.NOT_AVAILABLE, "font-classic-italics",
                    2, 2, 2, true),
            VIGILANT = FontFamily.loadFromSources(
                    "Vigilant", FONT_PATH, "font-vigilant",
                    FontFamily.NOT_AVAILABLE, "font-vigilant-italics",
                    2, 2, 2, true);

    public static final Font HAND_DRAWN = Font.loadFromSource(
            FONT_PATH, "font-hand-drawn", false, 2);

    public static final Font ARIAL = Font.loadFromSource(
            FONT_PATH, "font-arial", false, 4);

    public static final Font HALF_SCALE_ARIAL = Font.loadFromSource(
            FONT_PATH, "font-half-scale-arial", false, 2);
}
