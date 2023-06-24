package com.jordanbunke.jbjgl.menus.menu_elements.invisible;

import com.jordanbunke.jbjgl.debug.GameDebugger;
import com.jordanbunke.jbjgl.image.GameImage;
import com.jordanbunke.jbjgl.io.InputEventLogger;

public final class TimedMenuElement extends InvisibleMenuElement {
    private final int timeOut;
    private final Runnable behaviour;

    private int count;

    public TimedMenuElement(
            final int timeOut, final Runnable behaviour
    ) {
        super();

        this.timeOut = timeOut;
        this.behaviour = behaviour;

        count = 0;
    }

    @Override
    public void update(final double deltaTime) {
        count++;

        if (count >= timeOut) {
            behaviour.run();
            count = 0;
        }
    }

    @Override
    public void process(final InputEventLogger eventLogger) {

    }
}