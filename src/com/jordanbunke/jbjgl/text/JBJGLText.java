package com.jordanbunke.jbjgl.text;

import com.jordanbunke.jbjgl.image.ImageProcessing;
import com.jordanbunke.jbjgl.image.JBJGLImage;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static com.jordanbunke.jbjgl.fonts.FontConstants.LINE_HEIGHT;

public class JBJGLText {
    private final JBJGLTextComponent[][] lines;
    private final double textSize;
    private final int widthAllowance;
    private final boolean componentsSplittable;
    private final Orientation orientation;

    public enum Orientation {
        LEFT, CENTER, RIGHT
    }

    private JBJGLText(final JBJGLTextBlock[] blocks, final double textSize,
                      final int widthAllowance, boolean componentsSplittable,
                      Orientation orientation) {
        this.textSize = textSize;
        this.widthAllowance = widthAllowance;
        this.componentsSplittable = componentsSplittable;
        this.orientation = orientation;

        this.lines = setLines(blocks);
    }

    public static JBJGLText createOf(
            final double textSize, final Orientation orientation,
            final JBJGLTextBlock... blocks
    ) {
        return new JBJGLText(blocks, textSize, -1, false, orientation);
    }

    public static JBJGLText create(
            final JBJGLTextBlock[] blocks,
            final double textSize, final Orientation orientation
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
                if ((i == blocks.length && i - processed > 0) ||
                        (i < blocks.length && blocks[i] instanceof JBJGLLineBreak)) {
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
        int maxWidth = 1;

        for (int i = 0; i < lines.length; i++) {
            int width = 0;
            for (int j = 0; j < lines[i].length; j++) {
                final double scaleFactor = textSize * (LINE_HEIGHT / (double)lines[i][j].getFont().getHeight());
                width += lines[i][j].calculateProspectiveWidth() * scaleFactor;
            }

            width = Math.max(width, 1);

            JBJGLImage drawnLine = JBJGLImage.create(width, (int)(textSize * LINE_HEIGHT));

            maxWidth = Math.max(maxWidth, width);

            Graphics g = drawnLine.getGraphics();
            int processed = 0;

            for (int j = 0; j < lines[i].length; j++) {
                final double scaleFactor = textSize * (LINE_HEIGHT / (double)lines[i][j].getFont().getHeight());
                final JBJGLImage drawnComponent = lines[i][j].draw();

                g.drawImage(scaleFactor == 1.
                                ? drawnComponent
                                : ImageProcessing.scaleUp(
                                    drawnComponent, scaleFactor,
                                    lines[i][j].getFont().hasSmoothResizing()),
                        processed, 0, null);
                processed += lines[i][j].calculateProspectiveWidth() * scaleFactor;
            }

            g.dispose();

            drawnLines[i] = drawnLine;
        }

        JBJGLImage image = JBJGLImage.create(maxWidth, lines.length * (int)(textSize * LINE_HEIGHT));
        Graphics g = image.getGraphics();

        int drawnHeight = 0;

        for (JBJGLImage drawnLine : drawnLines) {
            switch (orientation) {
                case LEFT -> g.drawImage(drawnLine, 0, drawnHeight, null);
                case RIGHT -> g.drawImage(drawnLine, maxWidth - drawnLine.getWidth(),
                        drawnHeight, null);
                case CENTER -> g.drawImage(drawnLine, (maxWidth - drawnLine.getWidth()) / 2,
                        drawnHeight, null);
            }

            drawnHeight += drawnLine.getHeight();
        }

        g.dispose();

        return image;
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
