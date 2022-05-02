package com.jordanbunke.jbjgl.fonts;

import java.nio.file.Path;
import java.nio.file.Paths;

public class JBJGLFonts {
    private static final Path FONT_PATH = Paths.get("resources", "font_files");

    private static final FontFamily BASIC = FontFamily.loadFromSources(
            "Basic", FONT_PATH,
            "font-basic", "font-basic-bold", FontFamily.NOT_AVAILABLE,
            true
    );

    public static Font BASIC() {
        return BASIC.getStandard();
    }

    public static Font BASIC_BOLD() {
        return BASIC.getBold();
    }

    /*
    public static Font BASIC_ITALICS() {
        return BASIC.getItalics();
    }
     */
}
