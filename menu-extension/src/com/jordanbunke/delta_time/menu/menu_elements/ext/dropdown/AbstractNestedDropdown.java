package com.jordanbunke.delta_time.menu.menu_elements.ext.dropdown;

import com.jordanbunke.delta_time.utility.math.Coord2D;

public abstract non-sealed class AbstractNestedDropdown extends AbstractDropdown {
    private final AbstractDropdown parent;

    public AbstractNestedDropdown(
            final Coord2D position, final Coord2D dimensions,
            final Anchor anchor, final int renderOrder,
            final DropdownItem[] items, final AbstractDropdown parent
    ) {
        super(position, dimensions, anchor, renderOrder, items);

        this.parent = parent;
    }

    @Override
    protected Coord2D contentsDisplacement() {
        return new Coord2D(getWidth(), 0);
    }

    @Override
    protected void close() {
        super.close();

        if (parent.isDroppedDown())
            parent.close();
    }
}
