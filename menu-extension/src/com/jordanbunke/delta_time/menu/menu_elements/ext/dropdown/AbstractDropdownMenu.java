package com.jordanbunke.delta_time.menu.menu_elements.ext.dropdown;

import com.jordanbunke.delta_time.utility.math.Coord2D;

public abstract sealed class AbstractDropdownMenu extends AbstractDropdown
        permits AbstractNestedDropdownMenu, AbstractRootDropdownMenu {
    public AbstractDropdownMenu(
            final Coord2D position, final Coord2D dimensions,
            final Anchor anchor, final int renderOrder,
            final DropdownItem[] items
    ) {
        super(position, dimensions, anchor, renderOrder, items);
    }

    @Override
    protected void close() {
        super.close();

        final int size = getSize();

        for (int i = 0; i < size; i++)
            if (getElement(i) instanceof AbstractDropdownMenu d &&
                    d.isDroppedDown())
                d.close();
    }

    protected void pinged(final AbstractNestedDropdownMenu child) {
        final int size = getSize();

        for (int i = 0; i < size; i++)
            if (getElement(i) instanceof AbstractDropdownMenu d &&
                    !d.equals(child))
                d.close();
    }
}
