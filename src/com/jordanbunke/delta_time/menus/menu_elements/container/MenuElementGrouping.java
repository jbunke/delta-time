package com.jordanbunke.delta_time.menus.menu_elements.container;

import com.jordanbunke.delta_time.debug.GameDebugger;
import com.jordanbunke.delta_time.image.GameImage;
import com.jordanbunke.delta_time.io.InputEventLogger;
import com.jordanbunke.delta_time.menus.menu_elements.MenuElement;
import com.jordanbunke.delta_time.utility.Coord2D;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class MenuElementGrouping extends MenuElementContainer {
    private final MenuElement[] menuElements;

    public MenuElementGrouping(final MenuElement... menuElements) {
        super(new Coord2D(), new Coord2D(), Anchor.LEFT_TOP, false);

        this.menuElements = menuElements;
    }

    @Override
    public void update(final double deltaTime) {
        for (MenuElement menuElement : menuElements)
            menuElement.update(deltaTime);
    }

    @Override
    public void render(final GameImage canvas) {
        final List<MenuElement> renderOrder =
                new ArrayList<>(List.of(menuElements));
        renderOrder.sort(Comparator.comparingInt(MenuElement::getRenderOrder));

        for (MenuElement element : menuElements)
            element.render(canvas);
    }

    @Override
    public void debugRender(final GameImage canvas, final GameDebugger debugger) {
        final List<MenuElement> renderOrder =
                new ArrayList<>(List.of(menuElements));
        renderOrder.sort(Comparator.comparingInt(MenuElement::getRenderOrder));

        for (MenuElement element : menuElements)
            element.debugRender(canvas, debugger);
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
