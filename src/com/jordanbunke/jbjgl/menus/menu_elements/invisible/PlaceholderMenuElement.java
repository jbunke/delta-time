package com.jordanbunke.jbjgl.menus.menu_elements.invisible;

import com.jordanbunke.jbjgl.debug.GameDebugger;
import com.jordanbunke.jbjgl.io.InputEventLogger;

import java.awt.*;

public final class PlaceholderMenuElement extends InvisibleMenuElement {
    public PlaceholderMenuElement() {
        super();
    }

    @Override
    public void update(final double deltaTime) {

    }

    @Override
    public void render(final Graphics2D g) {

    }

    @Override
    public void debugRender(final Graphics2D g, final GameDebugger debugger) {

    }

    @Override
    public void process(final InputEventLogger eventLogger) {

    }
}
