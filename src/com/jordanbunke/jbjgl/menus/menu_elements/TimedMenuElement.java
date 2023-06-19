package com.jordanbunke.jbjgl.menus.menu_elements;

import com.jordanbunke.jbjgl.debug.GameDebugger;
import com.jordanbunke.jbjgl.io.InputEventLogger;

import java.awt.*;

public class TimedMenuElement extends MenuElement {
    private final int timeOut;
    private final Runnable behaviour;

    private int count;

    private TimedMenuElement(
            final int timeOut, final Runnable behaviour
    ) {
        super(new int[] { 0, 0 }, new int[] { 1, 1 }, Anchor.CENTRAL, false);

        this.timeOut = timeOut;
        this.behaviour = behaviour;

        count = 0;
    }

    public static TimedMenuElement generate(
            final int timeOut, final Runnable behaviour
    ) {
        return new TimedMenuElement(timeOut, behaviour);
    }

    @Override
    public void update() {
        count++;

        if (count >= timeOut) {
            behaviour.run();
            count = 0;
        }
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
