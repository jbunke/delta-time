package com.jordanbunke.delta_time.menus.menu_elements.container;

import com.jordanbunke.delta_time.menus.menu_elements.MenuElement;
import com.jordanbunke.delta_time.utility.Coord2D;

public class MenuElementGrouping extends MenuElementContainer {
    private final MenuElement[] menuElements;

    public MenuElementGrouping(final MenuElement... menuElements) {
        super(new Coord2D(), new Coord2D(), Anchor.LEFT_TOP, false);

        this.menuElements = menuElements;
    }

    @Override
    public MenuElement[] getMenuElements() {
        return menuElements;
    }

    @Override
    public boolean hasNonTrivialBehaviour() {
        return false;
    }
}
