package com.jordanbunke.jbjgl.menus.menu_elements;

public abstract class MenuElementContainer extends MenuElement {
    public MenuElementContainer(
            final int[] position, final int[] dimensions,
            final Anchor anchor, final boolean isVisible
    ) {
        super(position, dimensions, anchor, isVisible);
    }

    public abstract MenuElement[] getMenuElements();

    public abstract boolean hasNonTrivialBehaviour();
}
