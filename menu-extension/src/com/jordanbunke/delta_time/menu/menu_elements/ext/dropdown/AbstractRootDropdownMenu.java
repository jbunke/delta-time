package com.jordanbunke.delta_time.menu.menu_elements.ext.dropdown;

import com.jordanbunke.delta_time.utility.math.Bounds2D;
import com.jordanbunke.delta_time.utility.math.Coord2D;

public abstract non-sealed class AbstractRootDropdownMenu extends AbstractDropdownMenu {
    public AbstractRootDropdownMenu(
            final Coord2D position, final Bounds2D dimensions,
            final Anchor anchor, final int renderOrder,
            final DropdownItem[] items
    ) {
        super(position, dimensions, anchor, renderOrder, items);
    }

    @Override
    protected Coord2D contentsDisplacement() {
        return new Coord2D(0, getHeight());
    }
}
