package com.jordanbunke.jbjgl.text;

import com.jordanbunke.jbjgl.image.ImageProcessing;
import com.jordanbunke.jbjgl.image.JBJGLImage;

import java.awt.*;
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

    public static JBJGLText createOf(
            final int textSize, final Orientation orientation,
            final JBJGLTextBlock... blocks
    ) {
        return new JBJGLText(blocks, textSize, -1, false, orientation);
    }

    public static JBJGLText create(
            final JBJGLTextBlock[] blocks,
            final int textSize, final Orientation orientation
    ) {
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

    public JBJGLImage draw() {
        JBJGLImage[] drawnLines = new JBJGLImage[lines.length];
        int maxWidth = 0;

        for (int i = 0; i < lines.length; i++) {
            int width = 0;
            for (int j = 0; j < lines[i].length; j++)
                width += lines[i][j].calculateProspectiveWidth();

            if (width == 0)
                continue;

            JBJGLImage drawnLine = JBJGLImage.create(width, LINE_HEIGHT);
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

        JBJGLImage image = JBJGLImage.create(maxWidth, LINE_HEIGHT * lines.length);
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

        return ImageProcessing.scaleUp(image, textSize);
    }

    public int getWidthAllowance() {
        return widthAllowance;
    }

    public boolean areComponentsSplittable() {
        return componentsSplittable;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (JBJGLTextComponent[] line : lines) {
            for (JBJGLTextComponent component : line) {
                sb.append(component);
            }
            sb.append("\n");
        }

        return sb.toString();
    }
}
