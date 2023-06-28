package com.jordanbunke.delta_time.text;

import com.jordanbunke.delta_time.fonts.Font;

import java.awt.*;

public class DialogueUtils {
    /**
     * @param sections          Sections of text with unique {@link Color} and {@link  Font}
     *                          properties. Unrelated to lines and sections can include {@code '\n'}
     *                          as part of their strings.
     * @param colorsPerSection  Must be of same length as {@code sections}.
     * @param fontsPerSection   Must be of same length as {@code sections}.
     * @return  a {@link Text} object with single-letter {@link TextComponent}s,
     *          which can be drawn at every letter state by iterating from {@code 0} to
     *          {@link Text#getComponentSize()}.
     */
    public static Text generateSingleLetterDialogue(
            final double textSize, final double lineSpacing,
            final String[] sections, final Color[] colorsPerSection,
            final Font[] fontsPerSection
    ) {
        assert sections.length == colorsPerSection.length && sections.length > 0;
        assert sections.length == fontsPerSection.length;

        final TextBuilder tb = new TextBuilder(textSize, lineSpacing,
                Text.Orientation.LEFT, colorsPerSection[0], fontsPerSection[0]);

        for (int section = 0; section < sections.length; section++) {
            tb.setColor(colorsPerSection[section]);
            tb.setFont(fontsPerSection[section]);

            for (char c : sections[section].toCharArray()) {
                if (c == '\n')
                    tb.addLineBreak();
                else
                    tb.addText(String.valueOf(c));
            }
        }

        return tb.build();
    }
}
