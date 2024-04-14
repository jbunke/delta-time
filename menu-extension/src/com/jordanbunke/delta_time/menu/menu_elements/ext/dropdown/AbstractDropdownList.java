package com.jordanbunke.delta_time.menu.menu_elements.ext.dropdown;

import com.jordanbunke.delta_time.utility.math.Bounds2D;
import com.jordanbunke.delta_time.utility.math.Coord2D;

import java.util.function.Supplier;

public abstract non-sealed class AbstractDropdownList extends AbstractDropdown {
    private int index;

    public AbstractDropdownList(
            final Coord2D position, final Bounds2D dimensions,
            final Anchor anchor, final int renderOrder,
            final SimpleItem[] items,
            final Supplier<Integer> initialIndexFunction
    ) {
        super(position, dimensions, anchor, renderOrder, items);

        this.index = initialIndexFunction.get();
    }

    @Override
    protected void select(final int i) {
        if (getItem(i) instanceof SimpleItem behaviour) {
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
