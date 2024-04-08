package com.jordanbunke.delta_time.menu.menu_elements.invisible;

import com.jordanbunke.delta_time.debug.GameDebugger;
import com.jordanbunke.delta_time.image.GameImage;
import com.jordanbunke.delta_time.menu.menu_elements.MenuElement;
import com.jordanbunke.delta_time.utility.math.Coord2D;

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
