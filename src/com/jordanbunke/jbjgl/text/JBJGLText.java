package com.jordanbunke.jbjgl.text;

import com.jordanbunke.jbjgl.image.ImageMath;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import static com.jordanbunke.jbjgl.fonts.FontConstants.LINE_HEIGHT;

public class JBJGLText {
    private final JBJGLTextComponent[][] lines;
    private final int textSize;
    private final int widthAllowance;
    private final boolean componentsSplittable;
    private final Orientation orientation;

    public enum Orientation {
        LEFT, CENTER, RIGHT
    }

    private JBJGLText(final JBJGLTextBlock[] blocks, final int textSize,
                      final int widthAllowance, boolean componentsSplittable,
                      Orientation orientation) {
        this.textSize = textSize;
        this.widthAllowance = widthAllowance;
        this.componentsSplittable = componentsSplittable;
        this.orientation = orientation;

        this.lines = setLines(blocks);
    }

    public static JBJGLText create(final JBJGLTextBlock[] blocks, final int textSize,
                                   final Orientation orientation) {
        return new JBJGLText(blocks, textSize, -1, false, orientation);
    }

    private JBJGLTextComponent[][] setLines(final JBJGLTextBlock[] blocks) {
        List<JBJGLTextComponent[]> flexLines = new ArrayList<>();

        boolean widthMatters = widthAllowance > 0;

        if (widthMatters) {
            // TODO
        } else {
            int processed = 0;
            for (int i = 0; i <= blocks.length; i++) {
                if (i == blocks.length || blocks[i] instanceof JBJGLLineBreak) {
                    JBJGLTextComponent[] line = new JBJGLTextComponent[i - processed];

                    for (int j = processed; j < i; j++)
                        line[j - processed] = (JBJGLTextComponent) blocks[j];

                    flexLines.add(line);
                    processed = i + 1;
                }
            }
        }

        // Convert array list used for mutability and scalability to array
        JBJGLTextComponent[][] lines = new JBJGLTextComponent[flexLines.size()][];
        for (int i = 0; i < lines.length; i++) {
            lines[i] = flexLines.get(i);
        }

        return lines;
    }

    public BufferedImage draw() {
        BufferedImage[] drawnLines = new BufferedImage[lines.length];
        int maxWidth = 0;

        for (int i = 0; i < lines.length; i++) {
            int width = 0;
            for (int j = 0; j < lines[i].length; j++)
                width += lines[i][j].calculateProspectiveWidth();

            BufferedImage drawnLine = new BufferedImage(width, LINE_HEIGHT, BufferedImage.TYPE_INT_ARGB);
            maxWidth = Math.max(maxWidth, width);
            Graphics g = drawnLine.getGraphics();
            int processed = 0;

            for (int j = 0; j < lines[i].length; j++) {
                g.drawImage(lines[i][j].draw(), processed, 0, null);
                processed += lines[i][j].calculateProspectiveWidth();
            }

            g.dispose();

            drawnLines[i] = drawnLine;
        }

        BufferedImage image = new BufferedImage(maxWidth,
                LINE_HEIGHT * lines.length, BufferedImage.TYPE_INT_ARGB);
        Graphics g = image.getGraphics();

        for (int i = 0; i < drawnLines.length; i++) {
            switch (orientation) {
                case LEFT ->
                        g.drawImage(drawnLines[i], 0, i * LINE_HEIGHT, null);
                case RIGHT ->
                        g.drawImage(drawnLines[i], maxWidth - drawnLines[i].getWidth(),
                                i * LINE_HEIGHT, null);
                case CENTER -> g.drawImage(drawnLines[i], (maxWidth - drawnLines[i].getWidth()) / 2,
                        i * LINE_HEIGHT, null);
            }
        }

        g.dispose();

        return ImageMath.scaleUp(image, textSize);
    }

    public int getWidthAllowance() {
        return widthAllowance;
    }

    public boolean areComponentsSplittable() {
        return componentsSplittable;
    }
}
