package com.jordanbunke.delta_time.menu.menu_elements.ext.drawing_functions;

import com.jordanbunke.delta_time.image.GameImage;

@FunctionalInterface
public interface CheckboxDrawingFunction {
    GameImage draw(final boolean highlighted, final boolean checked);
}
