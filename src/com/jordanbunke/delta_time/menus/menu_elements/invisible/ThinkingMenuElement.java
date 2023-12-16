package com.jordanbunke.delta_time.menus.menu_elements.invisible;

import com.jordanbunke.delta_time.io.InputEventLogger;
import com.jordanbunke.delta_time.menus.menu_elements.MenuElement;

import java.util.function.Supplier;

public final class ThinkingMenuElement extends InvisibleMenuElement {
    private final Supplier<MenuElement> thoughtProcess;

    private MenuElement decision;

    public ThinkingMenuElement(final Supplier<MenuElement> thoughtProcess) {
        this.thoughtProcess = thoughtProcess;

        think();
    }

    @Override
    public void process(final InputEventLogger eventLogger) {
        if (decision != null)
            decision.process(eventLogger);
    }

    @Override
    public void update(final double deltaTime) {
        think();

        if (decision != null)
            decision.update(deltaTime);
    }

    private void think() {
        decision = thoughtProcess.get();
    }
}
