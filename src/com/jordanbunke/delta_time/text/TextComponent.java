package com.jordanbunke.delta_time.text;

import com.jordanbunke.delta_time.fonts.Font;
import com.jordanbunke.delta_time.image.GameImage;

import java.awt.*;

public class TextComponent extends TextConstituent {
    private final String contents;
    private final Font font;
    private final Color color;

    public TextComponent(final String contents, final Font font, final Color color) {
        this.contents = contents;
        this.font = font;
        this.color = color;
    }

    public int calculateProspectiveWidth() {
        return calculateProspectiveWidth(contents);
    }

    private int calculateProspectiveWidth(final String s) {
        int width = 0;

        if (font.hasCharSpecificSpacing()) {
            for (int i = 0; i < s.length(); i++) {
                if (i + 1 >= s.length())
                    width += font.getCharWidth(s.charAt(i));
                else
                    width += font.getCharWidthRespectiveToNext(s.charAt(i), s.charAt(i + 1)) +
                            font.getPixelSpacing();
            }
        } else {
            for (char c : s.toCharArray())
                width += font.getCharWidth(c) + font.getPixelSpacing();

            width -= font.getPixelSpacing();
        }

        return Math.max(1, width);
    }

    public GameImage draw() {
        final GameImage image = new GameImage(calculateProspectiveWidth(), font.getHeight());

        int processed = 0;

        if (font.hasCharSpecificSpacing()) {
            for (int i = 0; i < contents.length(); i++) {
                if (i + 1 >= contents.length()) {
                    image.draw(font.drawChar(contents.charAt(i), color), processed, 0);
                    processed += font.getCharWidth(contents.charAt(i));
                } else {
                    image.draw(font.drawChar(contents.charAt(i), color), processed, 0);
                    processed += font.getCharWidthRespectiveToNext(contents.charAt(i), contents.charAt(i + 1)) +
                            font.getPixelSpacing();
                }
            }
        } else {
            for (char c : contents.toCharArray()) {
                image.draw(font.drawChar(c, color), processed, 0);
                processed += font.getCharWidth(c) + font.getPixelSpacing();
            }
        }

        return image.submit();
    }

    public Font getFont() {
        return font;
    }

    @Override
    public String toString() {
        return contents;
    }
}
