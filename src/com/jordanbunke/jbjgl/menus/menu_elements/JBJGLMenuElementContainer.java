package com.jordanbunke.jbjgl.menus.menu_elements;

public abstract class JBJGLMenuElementContainer extends JBJGLMenuElement {
    public JBJGLMenuElementContainer(
            final int[] position, final int[] dimensions,
            final Anchor anchor, final boolean isVisible
    ) {
        super(position, dimensions, anchor, isVisible);
    }

    public abstract JBJGLMenuElement[] getMenuElements();

    public abstract boolean hasNonTrivialBehaviour();
}
