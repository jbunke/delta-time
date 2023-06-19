package com.jordanbunke.jbjgl.menus.menu_elements.container;

import com.jordanbunke.jbjgl.debug.GameDebugger;
import com.jordanbunke.jbjgl.io.InputEventLogger;
import com.jordanbunke.jbjgl.menus.menu_elements.MenuElement;
import com.jordanbunke.jbjgl.utility.Coord2D;

import java.awt.*;

public class MenuElementGrouping extends MenuElementContainer {
    private final MenuElement[] menuElements;

    public MenuElementGrouping(final MenuElement... menuElements) {
        super(new Coord2D(), new Coord2D(1, 1), Anchor.LEFT_TOP, false);

        this.menuElements = menuElements;
    }

    @Override
    public void update(final double deltaTime) {
        for (MenuElement menuElement : menuElements)
            menuElement.update(deltaTime);
    }

    @Override
    public void render(final Graphics2D g) {
        for (MenuElement menuElement : menuElements)
            menuElement.render(g);
    }

    @Override
    public void debugRender(final Graphics2D g, final GameDebugger debugger) {
        for (MenuElement menuElement : menuElements)
            menuElement.debugRender(g, debugger);
    }

    @Override
    public void process(final InputEventLogger eventLogger) {
        for (MenuElement menuElement : menuElements)
            menuElement.process(eventLogger);
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
