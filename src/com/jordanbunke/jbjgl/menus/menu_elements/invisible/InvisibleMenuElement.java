package com.jordanbunke.jbjgl.menus.menu_elements.invisible;

import com.jordanbunke.jbjgl.debug.GameDebugger;
import com.jordanbunke.jbjgl.image.GameImage;
import com.jordanbunke.jbjgl.menus.menu_elements.MenuElement;
import com.jordanbunke.jbjgl.utility.Coord2D;

public abstract class InvisibleMenuElement extends MenuElement {
    public InvisibleMenuElement() {
        super(new Coord2D(), new Coord2D(), Anchor.LEFT_TOP, false);
    }

    @Override
    public void render(final GameImage canvas) {

    }

    @Override
    public void debugRender(final GameImage canvas, final GameDebugger debugger) {

    }
}
