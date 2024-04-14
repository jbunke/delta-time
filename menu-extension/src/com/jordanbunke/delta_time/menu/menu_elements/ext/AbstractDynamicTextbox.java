package com.jordanbunke.delta_time.menu.menu_elements.ext;

import com.jordanbunke.delta_time.menu.menu_elements.ext.drawing_functions.TextboxDrawingFunction;
import com.jordanbunke.delta_time.utility.math.Bounds2D;
import com.jordanbunke.delta_time.utility.math.Coord2D;
import com.jordanbunke.funke.core.Property;

import java.util.function.Function;
import java.util.function.Supplier;

public abstract class AbstractDynamicTextbox extends AbstractTextbox {
    private final Property<String> property;

    public AbstractDynamicTextbox(
            final Coord2D position, final Bounds2D dimensions,
            final Anchor anchor,
            final Supplier<String> prefixGetter, final String initialText,
            final Supplier<String> suffixGetter,
            final Function<String, Boolean> textValidator,
            final Property<String> property,
            final TextboxDrawingFunction fDraw, final int maxLength
    ) {
        super(position, dimensions, anchor,
                prefixGetter, initialText, suffixGetter,
                textValidator, property, fDraw, maxLength);

        this.property = property;
    }

    @Override
    public void update(final double deltaTime) {
        if (!isTyping())
            setText(property.get());

        super.update(deltaTime);
    }
}
