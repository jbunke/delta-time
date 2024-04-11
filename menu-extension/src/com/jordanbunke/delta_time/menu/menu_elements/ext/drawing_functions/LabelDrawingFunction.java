package com.jordanbunke.delta_time.menu.menu_elements.ext.drawing_functions;

import com.jordanbunke.delta_time.image.GameImage;

import java.awt.*;

@FunctionalInterface
public interface LabelDrawingFunction {
    GameImage draw(final String text, final Color c);
}
