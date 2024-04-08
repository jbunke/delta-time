package com.jordanbunke.delta_time.menu.menu_elements.invisible;

import com.jordanbunke.delta_time.io.InputEventLogger;

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
