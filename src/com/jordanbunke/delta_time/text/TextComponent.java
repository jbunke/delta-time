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

        for (char c : s.toCharArray()) {
            width += font.getCharWidth(c);
            width += font.getPixelSpacing();
        }

        width -= font.getPixelSpacing();

        return Math.max(1, width);
    }

    public GameImage draw() {
        final GameImage image = new GameImage(calculateProspectiveWidth(), font.getHeight());

        int processed = 0;

        for (char c : contents.toCharArray()) {
            image.draw(font.drawChar(c, color), processed, 0);
            processed += font.getCharWidth(c) + font.getPixelSpacing();
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
