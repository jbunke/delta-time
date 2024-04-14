package com.jordanbunke.delta_time.menu.menu_elements.ext.dropdown;

import com.jordanbunke.delta_time.menu.menu_elements.MenuElement;
import com.jordanbunke.delta_time.menu.menu_elements.invisible.PlaceholderMenuElement;
import com.jordanbunke.delta_time.utility.math.Coord2D;

public abstract class AbstractNavbar extends AbstractRootDropdownMenu {
    public AbstractNavbar(
            final Coord2D position, final Coord2D dimensions,
            final int renderOrder, final NestedItem[] submenus
    ) {
        super(position, dimensions, Anchor.LEFT_TOP, renderOrder, submenus);
    }

    @Override
    public boolean isDroppedDown() {
        return true;
    }

    public void closeRest(final AbstractDropdownMenu submenu) {
        final int size = getSize();

        for (int i = 0; i < size; i++)
            if (getElement(i) instanceof AbstractDropdownMenu d &&
                    !d.equals(submenu))
                d.close();
    }

    @Override
    protected Coord2D contentsDisplacement() {
        return new Coord2D();
    }

    @Override
    protected MenuElement makeDDButton() {
        return new PlaceholderMenuElement();
    }
}
