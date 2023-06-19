package com.jordanbunke.jbjgl.menus.menu_elements;

import com.jordanbunke.jbjgl.debug.GameDebugger;
import com.jordanbunke.jbjgl.io.InputEventLogger;

import java.awt.*;

public class PlaceholderMenuElement extends MenuElement {
    private PlaceholderMenuElement() {
        super(new int[] { 0, 0 }, new int[] { 1, 1 }, Anchor.LEFT_TOP, false);
    }

    public static PlaceholderMenuElement generate() {
        return new PlaceholderMenuElement();
    }

    @Override
    public void update() {

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
