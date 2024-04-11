package com.jordanbunke.delta_time.menu.menu_elements.ext;

import com.jordanbunke.delta_time.menu.menu_elements.ext.drawing_functions.LabelDrawingFunction;
import com.jordanbunke.delta_time.utility.math.Coord2D;
import com.jordanbunke.funke.core.Retriever;

import java.awt.*;

public abstract class AbstractStaticLabel extends AbstractDynamicLabel {
    public AbstractStaticLabel(
            final Coord2D position,
            final int height,
            final Anchor anchor,
            final Color textColor,
            final String text,
            final LabelDrawingFunction fDraw
    ) {
        super(position, text, height, anchor, textColor,
                Retriever.of(text), fDraw);
    }

    @Override
    public void update(final double deltaTime) {

    }
}
