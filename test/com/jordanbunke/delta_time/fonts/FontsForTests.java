package com.jordanbunke.delta_time.fonts;

import java.nio.file.Path;

public class FontsForTests {
    private static final Path FONT_PATH = Path.of("font_files");
    private static final boolean CSS = true;

    public static final FontFamily
            BASIC = FontFamily.loadFromSources(
                    "Basic", FONT_PATH, true, "font-basic",
            "font-basic-bold", FontFamily.NOT_AVAILABLE,
            2, 2, 0,true, CSS),
            CLASSIC = FontFamily.loadFromSources(
                    "Classic", FONT_PATH, true, "font-classic",
                    FontFamily.NOT_AVAILABLE, "font-classic-italics",
                    2, 2, 0, true, CSS),
            VIGILANT = FontFamily.loadFromSources(
                    "Vigilant", FONT_PATH, true, "font-vigilant",
                    FontFamily.NOT_AVAILABLE, "font-vigilant-italics",
                    2, 2, 2, true, CSS),
            BILHETE = new FontFamily("Bilhete",
                    Font.loadFromSource(FONT_PATH, true,
                            "font-bilhete", false, 1.0, 6, true, CSS),
                    Font.loadFromSource(FONT_PATH, true,
                            "font-bilhete-bold", false, 1.0, 6, true, CSS),
                    null),
            MY_HANDWRITING = new FontFamily("My Handwriting",
                    Font.loadFromSource(FONT_PATH, true,
                            "font-my-handwriting", false, 1.0, 8, true, CSS),
                    null,
                    Font.loadFromSource(FONT_PATH, true,
                            "font-my-handwriting-italics", false, 1.0, 0, true, CSS));

    public static final Font ARIAL = Font.loadFromSource(
            FONT_PATH, true, "font-precise-arial", false, 0.75, 15, true, CSS);
}
