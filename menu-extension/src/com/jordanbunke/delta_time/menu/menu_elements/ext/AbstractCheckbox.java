package com.jordanbunke.delta_time.menu.menu_elements.ext;

import com.jordanbunke.delta_time.debug.GameDebugger;
import com.jordanbunke.delta_time.image.GameImage;
import com.jordanbunke.delta_time.menu.menu_elements.button.MenuButtonStub;
import com.jordanbunke.delta_time.menu.menu_elements.ext.drawing_functions.CheckboxDrawingFunction;
import com.jordanbunke.delta_time.utility.math.Bounds2D;
import com.jordanbunke.delta_time.utility.math.Coord2D;
import com.jordanbunke.funke.core.Property;

public abstract class AbstractCheckbox extends MenuButtonStub {
    private final Property<Boolean> property;

    private boolean isChecked;

    private final GameImage checked, checkedH, unchecked, uncheckedH;

    public AbstractCheckbox(
            final Coord2D position,
            final Bounds2D dimensions,
            final Anchor anchor,
            final Property<Boolean> property,
            final CheckboxDrawingFunction fDraw
    ) {
        super(position, dimensions, anchor, true);

        this.property = property;

        isChecked = this.property.get();

        checked = fDraw.draw(false, true);
        unchecked = fDraw.draw(false, false);
        checkedH = fDraw.draw(true, true);
        uncheckedH = fDraw.draw(true, false);
    }

    @Override
    public void execute() {
        property.accept(!isChecked);
    }

    @Override
    public void update(final double deltaTime) {
        isChecked = property.get();
    }

    @Override
    public void render(final GameImage canvas) {
        draw(isHighlighted()
                ? (isChecked ? checkedH : uncheckedH)
                : (isChecked ? checked : unchecked), canvas);
    }

    @Override
    public void debugRender(final GameImage canvas, final GameDebugger debugger) {

    }
}
