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
                    2, 2, 2, true),
            MY_HANDWRITING = FontFamily.fromPreLoaded(
                    "My Handwriting", Font.loadFromSource(FONT_PATH,
                            "font-my-handwriting", false, 1.0, 0, true),
                    null, Font.loadFromSource(FONT_PATH,
                            "font-my-handwriting-italics", false, 1.0, -8, true)
            );

    public static final Font ARIAL = Font.loadFromSource(
            FONT_PATH, "font-precise-arial", false, 0.75, 15, true);
}
