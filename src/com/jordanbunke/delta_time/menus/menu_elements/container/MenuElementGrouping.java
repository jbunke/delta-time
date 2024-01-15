package com.jordanbunke.delta_time.menus.menu_elements.container;

import com.jordanbunke.delta_time.debug.GameDebugger;
import com.jordanbunke.delta_time.image.GameImage;
import com.jordanbunke.delta_time.io.InputEventLogger;
import com.jordanbunke.delta_time.menus.menu_elements.DeferredRenderMenuElement;
import com.jordanbunke.delta_time.menus.menu_elements.MenuElement;
import com.jordanbunke.delta_time.utility.Coord2D;

import java.util.ArrayList;
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
        final List<DeferredRenderMenuElement> deferred = new ArrayList<>();

        for (MenuElement menuElement : menuElements)
            if (menuElement instanceof DeferredRenderMenuElement d)
                deferred.add(d);
            else
                menuElement.render(canvas);

        for (DeferredRenderMenuElement d : deferred)
            d.render(canvas);
    }

    @Override
    public void debugRender(final GameImage canvas, final GameDebugger debugger) {
        final List<DeferredRenderMenuElement> deferred = new ArrayList<>();

        for (MenuElement menuElement : menuElements)
            if (menuElement instanceof DeferredRenderMenuElement d)
                deferred.add(d);
            else
                menuElement.debugRender(canvas, debugger);

        for (DeferredRenderMenuElement d : deferred)
            d.debugRender(canvas, debugger);
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
