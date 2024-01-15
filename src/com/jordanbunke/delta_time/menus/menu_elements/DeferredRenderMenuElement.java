package com.jordanbunke.delta_time.menus.menu_elements;

import com.jordanbunke.delta_time.utility.Coord2D;

public abstract class DeferredRenderMenuElement extends MenuElement {
    public DeferredRenderMenuElement(
            final Coord2D position, final Coord2D dimensions,
            final Anchor anchor, final boolean visible
    ) {
        super(position, dimensions, anchor, visible);
    }
}
