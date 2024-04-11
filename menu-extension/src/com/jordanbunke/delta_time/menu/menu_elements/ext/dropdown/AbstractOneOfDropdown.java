package com.jordanbunke.delta_time.menu.menu_elements.ext.dropdown;

import com.jordanbunke.delta_time.utility.math.Coord2D;

import java.util.function.Supplier;

public abstract non-sealed class AbstractOneOfDropdown extends AbstractDropdown {
    private int index;

    public AbstractOneOfDropdown(
            final Coord2D position, final Coord2D dimensions,
            final Anchor anchor, final int renderOrder,
            final DropdownItem[] items,
            final Supplier<Integer> initialIndexFunction
    ) {
        super(position, dimensions, anchor, renderOrder, items);

        this.index = initialIndexFunction.get();
    }

    @Override
    protected void select(final int i) {
        if (getItem(i) instanceof DropdownBehaviour behaviour) {
            behaviour.run();

            index = i;
            close();

            updateDDButton();
        }
    }

    protected String getCurrentLabelText() {
        return getItem(index).label;
    }
}
