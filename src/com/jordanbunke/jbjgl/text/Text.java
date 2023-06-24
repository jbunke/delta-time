package com.jordanbunke.jbjgl.text;

import com.jordanbunke.jbjgl.image.GameImage;
import com.jordanbunke.jbjgl.image.ImageProcessing;

import java.util.ArrayList;
import java.util.List;

import static com.jordanbunke.jbjgl.fonts.FontConstants.LINE_HEIGHT;

public class Text {
    static final double DEFAULT_LINE_SPACING = 1d;

    private final TextComponent[][] lines;
    private final double textSize, lineSpacing;
    private final int widthAllowance, componentSize;
    private final boolean componentsSplittable;
    private final Orientation orientation;

    public enum Orientation {
        LEFT, CENTER, RIGHT
    }

    private Text(
            final TextConstituent[] blocks,
            final double textSize, final double lineSpacing,
            final int widthAllowance, boolean componentsSplittable,
            final Orientation orientation
    ) {
        this.textSize = textSize;
        this.lineSpacing = lineSpacing;
        this.widthAllowance = widthAllowance;
        this.componentsSplittable = componentsSplittable;
        this.orientation = orientation;

        this.lines = setLines(blocks);

        this.componentSize = calculateComponentSize();
    }

    public Text(
            final double textSize, final double lineSpacing, final Orientation orientation,
            final TextConstituent... blocks
    ) {
        this(blocks, textSize, lineSpacing, -1, false, orientation);
    }

    public Text(
            final double textSize, final Orientation orientation,
            final TextConstituent... blocks
    ) {
        this(blocks, textSize, DEFAULT_LINE_SPACING, -1, false, orientation);
    }

    private int calculateComponentSize() {
        int components = 0;

        for (TextComponent[] line : lines)
            components += line.length;

        return components;
    }

    private TextComponent[][] setLines(final TextConstituent[] blocks) {
        List<TextComponent[]> flexLines = new ArrayList<>();

        boolean widthMatters = widthAllowance > 0;

        if (widthMatters) {
            // TODO
        } else {
            int processed = 0;
            for (int i = 0; i <= blocks.length; i++) {
                if ((i == blocks.length && i - processed > 0) ||
                        (i < blocks.length && blocks[i] instanceof LineBreak)) {
                    TextComponent[] line = new TextComponent[i - processed];

                    for (int j = processed; j < i; j++)
                        line[j - processed] = (TextComponent) blocks[j];

                    flexLines.add(line);
                    processed = i + 1;
                }
            }
        }

        // Convert array list used for mutability and scalability to array
        TextComponent[][] lines = new TextComponent[flexLines.size()][];
        for (int i = 0; i < lines.length; i++) {
            lines[i] = flexLines.get(i);
        }

        return lines;
    }

    public GameImage draw() {
        return draw(componentSize);
    }

    public GameImage draw(final int componentLimit) {
        final GameImage[] drawnLines = new GameImage[lines.length];
        int maxWidth = 1, linesReached = 0, componentsDrawn = 0;

        for (int i = 0; i < lines.length && componentsDrawn < componentLimit; i++) {
            linesReached++;

            int width = 0, lastPixelSpacing = 0;
            for (int j = 0; j < lines[i].length; j++) {
                final double scaleFactor = textSize * (LINE_HEIGHT / (double)lines[i][j].getFont().getHeight());
                width += lines[i][j].calculateProspectiveWidth() * scaleFactor;
                lastPixelSpacing = (int)(lines[i][j].getFont().getPixelSpacing() * scaleFactor);
                width += lastPixelSpacing;
            }

            width -= lastPixelSpacing;
            width = Math.max(width, 1);
            maxWidth = Math.max(maxWidth, width);

            final GameImage drawnLine = new GameImage(width, (int)(textSize * LINE_HEIGHT));
            int processed = 0;

            for (int j = 0; j < lines[i].length && componentsDrawn < componentLimit; j++) {
                final double scaleFactor = textSize * (LINE_HEIGHT / (double)lines[i][j].getFont().getHeight());
                final GameImage drawnComponent = lines[i][j].draw(),
                        scaledComponent = scaleFactor == 1. ? drawnComponent
                                : ImageProcessing.scale(drawnComponent, scaleFactor,
                                lines[i][j].getFont().hasSmoothResizing());

                drawnLine.draw(scaledComponent, processed, 0);

                componentsDrawn++;
                processed += scaledComponent.getWidth();
                processed += lines[i][j].getFont().getPixelSpacing() * scaleFactor;
            }

            drawnLines[i] = drawnLine.submit();
        }

        final GameImage image = new GameImage(maxWidth,
                linesReached * (int)(textSize * LINE_HEIGHT * lineSpacing));

        int drawnHeight = (int)((lineSpacing - DEFAULT_LINE_SPACING) * 0.5 * textSize * LINE_HEIGHT);

        for (int i = 0; i < linesReached; i++) {
            final GameImage drawnLine = drawnLines[i];

            switch (orientation) {
                case LEFT -> image.draw(drawnLine, 0, drawnHeight);
                case RIGHT -> image.draw(drawnLine, maxWidth - drawnLine.getWidth(), drawnHeight);
                case CENTER -> image.draw(drawnLine, (maxWidth - drawnLine.getWidth()) / 2, drawnHeight);
            }

            drawnHeight += (int)(drawnLine.getHeight() * lineSpacing);
        }

        return image.submit();
    }

    public int getWidthAllowance() {
        return widthAllowance;
    }

    public boolean areComponentsSplittable() {
        return componentsSplittable;
    }

    public int getComponentSize() {
        return componentSize;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (TextComponent[] line : lines) {
            for (TextComponent component : line) {
                sb.append(component);
            }
            sb.append("\n");
        }

        return sb.toString();
    }
}
