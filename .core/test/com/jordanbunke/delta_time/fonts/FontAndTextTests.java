package com.jordanbunke.delta_time.fonts;

import com.jordanbunke.delta_time.io.FileIO;
import com.jordanbunke.delta_time.io.GameImageIO;
import com.jordanbunke.delta_time.text.Text;
import com.jordanbunke.delta_time.text.TextBuilder;
import org.junit.Test;

import java.awt.*;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FontAndTextTests {
    private static final Path FONT_TEST_OUTPUT_PATH =
            Paths.get(".core", "test_out", "fonts");
    private static final Font[] FONTS = new Font[] {
            FontsForTests.ARIAL,
            // FontsForTests.HALF_SCALE_ARIAL,
            FontsForTests.BILHETE.getStandard(),
            FontsForTests.BILHETE.getBold(),
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

        FileIO.safeMakeDirectory(FONT_TEST_OUTPUT_PATH);
        final Path filepath = FONT_TEST_OUTPUT_PATH.resolve(saveCode + FontConstants.FILE_SUFFIX);

        GameImageIO.writeImage(filepath, textBuilder.build().draw());
    }

    @Test
    public void renderASCIITextForInFonts() {
        renderTextForInFonts(
                "XOVAYJ_ P. X O V p. | rJ <- Folly? Jolly!",
                "ascii-test");
    }

    @Test
    public void renderIntroductionForInFonts() {
        renderTextForInFonts(
                "My name is Jordan Bunke.",
                "introduction");
    }

    @Test
    public void renderNonASCIITextForInFonts() {
        renderTextForInFonts(
                "Você tem que respeitar as leis do país, senão si mesmo!",
                "non-ascii-test");
    }
}
