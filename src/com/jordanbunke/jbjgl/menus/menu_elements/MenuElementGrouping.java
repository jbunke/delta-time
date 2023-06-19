package com.jordanbunke.jbjgl.menus.menu_elements;

import com.jordanbunke.jbjgl.debug.GameDebugger;
import com.jordanbunke.jbjgl.io.InputEventLogger;

import java.awt.*;

public class MenuElementGrouping extends MenuElementContainer {
    private final MenuElement[] menuElements;

    private MenuElementGrouping(
            final MenuElement[] menuElements
    ) {
        super(new int[] { 0, 0 }, new int[] { 1, 1 }, Anchor.LEFT_TOP, false);

        this.menuElements = menuElements;
    }

    public static MenuElementGrouping generateOf(final MenuElement... menuElements) {
        return new MenuElementGrouping(menuElements);
    }

    public static MenuElementGrouping generate(final MenuElement[] menuElements) {
        return new MenuElementGrouping(menuElements);
    }

    @Override
    public void update() {
        for (MenuElement menuElement : menuElements)
            menuElement.update();
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
