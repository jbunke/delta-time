package com.jordanbunke.jbjgl.text;

import com.jordanbunke.jbjgl.fonts.Font;
import com.jordanbunke.jbjgl.fonts.FontConstants;

import java.awt.*;
import java.awt.image.BufferedImage;

public class JBJGLTextComponent extends JBJGLTextBlock {
    private final String contents;
    private final Font font;
    private final Color color;

    private JBJGLTextComponent(final String contents, final Font font, final Color color) {
        this.contents = contents;
        this.font = font;
        this.color = color;
    }

    public static JBJGLTextComponent add(final String contents, final Font font, final Color color) {
        return new JBJGLTextComponent(contents, font, color);
    }

    public int calculateProspectiveWidth() {
        return calculateProspectiveWidth(contents);
    }

    private int calculateProspectiveWidth(final String s) {
        int width = 0;

        for (char c : s.toCharArray()) {
            width += font.getCharWidth(c);
            width++; // 1 PIXEL IN BETWEEN
        }

        return width;
    }

    public BufferedImage draw() {
        BufferedImage image = new BufferedImage(calculateProspectiveWidth(),
                FontConstants.LINE_HEIGHT, BufferedImage.TYPE_INT_ARGB);
        Graphics g = image.getGraphics();

        int processed = 0;

        for (char c : contents.toCharArray()) {
            g.drawImage(font.drawChar(c, color), processed, 0, null);
            processed += font.getCharWidth(c);
            processed++; // 1 PIXEL IN BETWEEN
        }

        g.dispose();

        return image;
    }

    @Override
    public String toString() {
        return contents;
    }
}
