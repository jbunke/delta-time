package com.jordanbunke.delta_time.menu.menu_elements.ext.drawing_functions;

import com.jordanbunke.delta_time.image.GameImage;
import com.jordanbunke.delta_time.utility.math.Coord2D;

@FunctionalInterface
public interface TextboxDrawingFunction {
    GameImage draw(
            final Coord2D dimensions,
            final String prefix, final String text, final String suffix,
            final int cursorIndex, final int selectionIndex,
            final boolean valid, final boolean highlighted, final boolean typing
    );
}
