package com.jordanbunke.delta_time.fonts;

import com.jordanbunke.delta_time.io.GameImageIO;
import com.jordanbunke.delta_time.text.Text;
import com.jordanbunke.delta_time.text.TextBuilder;
import org.junit.Test;

import java.awt.*;
import java.nio.file.Path;
import java.nio.file.Paths;

import static com.jordanbunke.delta_time.fonts.FontConstants.FILE_SUFFIX;

public class FontAndTextTests {
    private static final Path FONT_TEST_OUTPUT_PATH = Paths.get("test_out", "fonts");
    private static final Font[] FONTS = new Font[] {
            FontsForTests.ARIAL,
            // FontsForTests.HALF_SCALE_ARIAL,
            FontsForTests.MY_HANDWRITING.getStandard(),
            FontsForTests.MY_HANDWRITING.getItalics(),
            FontsForTests.BASIC.getStandard(),
            FontsForTests.BASIC.getBold(),
            FontsForTests.CLASSIC.getStandard(),
            FontsForTests.CLASSIC.getItalics(),
            FontsForTests.VIGILANT.getStandard(),
            FontsForTests.VIGILANT.getItalics()
    };

    private void renderTextForInFonts(final String text, final String saveCode) {
        final Color textColor = new Color(0, 0, 0);
        final TextBuilder textBuilder = new TextBuilder(
                4.0, Text.Orientation.LEFT,
                textColor, FONTS[0]);

        for (final Font font : FONTS)
            textBuilder.setFont(font).addText(text).addLineBreak();

        final Path filepath = FONT_TEST_OUTPUT_PATH.resolve(saveCode + FILE_SUFFIX);

        GameImageIO.writeImage(filepath, textBuilder.build().draw());
    }

    @Test
    public void renderASCIITextForInFonts() {
        renderTextForInFonts(
                "Font test: XOWALY <- Check if letters wrap properly",
                "ascii-test");
    }

    @Test
    public void renderIntroductionForInFonts() {
        renderTextForInFonts(
                "My name is Jordan Bunke, and one of these is my handwriting...",
                "introduction");
    }

    @Test
    public void renderNonASCIITextForInFonts() {
        renderTextForInFonts(
                "Você tem que respeitar as leis do país, senão si mesmo!",
                "non-ascii-test");
    }
}
