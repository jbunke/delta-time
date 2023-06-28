package com.jordanbunke.delta_time.menus.menu_elements.container;

import com.jordanbunke.delta_time.menus.menu_elements.MenuElement;
import com.jordanbunke.delta_time.utility.Coord2D;

public abstract class MenuElementContainer extends MenuElement {
    public MenuElementContainer(
            final Coord2D position, final Coord2D dimensions,
            final Anchor anchor, final boolean visible
    ) {
        super(position, dimensions, anchor, visible);
    }

    public abstract MenuElement[] getMenuElements();

    public abstract boolean hasNonTrivialBehaviour();
}
