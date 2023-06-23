package com.jordanbunke.jbjgl.text;

import com.jordanbunke.jbjgl.fonts.Font;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class TextBuilder {
    private final List<TextConstituent> textBlocks;
    private final double textSize, lineSpacing;
    private final Text.Orientation orientation;
    private Color currentColor;
    private Font currentFont;

    public TextBuilder(
            final double textSize, final Text.Orientation orientation,
            final Color initialColor, final Font initialFont
    ) {
        this(textSize, Text.DEFAULT_LINE_SPACING, orientation,
                initialColor, initialFont);
    }

    public TextBuilder(
            final double textSize, final double lineSpacing,
            final Text.Orientation orientation,
            final Color initialColor, final Font initialFont
    ) {
        this.textSize = textSize;
        this.lineSpacing = lineSpacing;
        this.orientation = orientation;
        currentColor = initialColor;
        currentFont = initialFont;

        this.textBlocks = new ArrayList<>();
    }

    public Text build() {
        return new Text(textSize, lineSpacing, orientation, textBlocks.toArray(TextConstituent[]::new));
    }

    public TextBuilder setColor(final Color currentColor) {
        this.currentColor = currentColor;
        return this;
    }

    public TextBuilder setFont(final Font currentFont) {
        this.currentFont = currentFont;
        return this;
    }

    public TextBuilder addLineBreak() {
        textBlocks.add(new LineBreak());
        return this;
    }

    public TextBuilder addText(final String s) {
        textBlocks.add(new TextComponent(s, currentFont, currentColor));
        return this;
    }
}
