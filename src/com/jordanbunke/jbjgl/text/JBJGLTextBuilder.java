package com.jordanbunke.jbjgl.text;

import com.jordanbunke.jbjgl.fonts.Font;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class JBJGLTextBuilder {
    private final List<JBJGLTextBlock> textBlocks;
    private final int textSize;
    private final JBJGLText.Orientation orientation;
    private Color currentColor;
    private Font currentFont;

    private JBJGLTextBuilder(
            final int textSize, final JBJGLText.Orientation orientation,
            final Color initialColor, final Font initialFont
    ) {
        this.textSize = textSize;
        this.orientation = orientation;
        currentColor = initialColor;
        currentFont = initialFont;

        this.textBlocks = new ArrayList<>();
    }

    public static JBJGLTextBuilder initialize(
            final int textSize, final JBJGLText.Orientation orientation,
            final Color initialColor, final Font initialFont
    ) {
        return new JBJGLTextBuilder(textSize, orientation, initialColor, initialFont);
    }

    public JBJGLText build() {
        JBJGLTextBlock[] textBlockArray = new JBJGLTextBlock[textBlocks.size()];
        return JBJGLText.create(textBlocks.toArray(textBlockArray), textSize, orientation);
    }

    public JBJGLTextBuilder setColor(final Color currentColor) {
        this.currentColor = currentColor;
        return this;
    }

    public JBJGLTextBuilder setFont(final Font currentFont) {
        this.currentFont = currentFont;
        return this;
    }

    public JBJGLTextBuilder addLineBreak() {
        textBlocks.add(JBJGLLineBreak.add());
        return this;
    }

    public JBJGLTextBuilder addText(final String s) {
        textBlocks.add(JBJGLTextComponent.add(s, currentFont, currentColor));
        return this;
    }
}
