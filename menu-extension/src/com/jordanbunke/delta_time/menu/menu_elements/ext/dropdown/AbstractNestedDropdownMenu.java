package com.jordanbunke.delta_time.menu.menu_elements.ext.dropdown;

import com.jordanbunke.delta_time.utility.math.Bounds2D;
import com.jordanbunke.delta_time.utility.math.Coord2D;

public abstract non-sealed class AbstractNestedDropdownMenu extends AbstractDropdownMenu {
    private final AbstractDropdownMenu parent;

    public AbstractNestedDropdownMenu(
            final Coord2D position, final Bounds2D dimensions,
            final Anchor anchor, final int renderOrder,
            final AbstractDropdownMenu parent,
            final DropdownItem[] items
    ) {
        super(position, dimensions, anchor, renderOrder, items);

        this.parent = parent;
    }

    @Override
    protected Coord2D contentsDisplacement() {
        return new Coord2D(getWidth(), 0);
    }

    protected final void transitiveClose() {
        super.close();

        if (parent.isDroppedDown()) {
            if (parent instanceof AbstractNestedDropdownMenu nested)
                nested.transitiveClose();
            else
                parent.close();
        }
    }

    @Override
    protected final void open() {
        super.open();
        ping();
    }

    private void ping() {
        parent.pinged(this);
    }
}
