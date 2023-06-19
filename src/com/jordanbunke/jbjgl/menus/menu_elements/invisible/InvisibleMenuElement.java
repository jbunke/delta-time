package com.jordanbunke.jbjgl.menus.menu_elements.invisible;

import com.jordanbunke.jbjgl.menus.menu_elements.MenuElement;
import com.jordanbunke.jbjgl.utility.Coord2D;

public abstract class InvisibleMenuElement extends MenuElement {
    public InvisibleMenuElement() {
        super(new Coord2D(), new Coord2D(1, 1), Anchor.LEFT_TOP, false);
    }
}
