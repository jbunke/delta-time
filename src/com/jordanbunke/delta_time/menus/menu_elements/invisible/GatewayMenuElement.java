package com.jordanbunke.delta_time.menus.menu_elements.invisible;

import com.jordanbunke.delta_time.debug.GameDebugger;
import com.jordanbunke.delta_time.image.GameImage;
import com.jordanbunke.delta_time.io.InputEventLogger;
import com.jordanbunke.delta_time.menus.menu_elements.MenuElement;

import java.util.function.Supplier;

public final class GatewayMenuElement extends InvisibleMenuElement {
    private final MenuElement content;
    private final Supplier<Boolean> condition;

    public GatewayMenuElement(
            final MenuElement content, final Supplier<Boolean> condition
    ) {
        super();

        this.content = content;
        this.condition = condition;
    }

    @Override
    public void process(final InputEventLogger eventLogger) {
        if (condition.get())
            content.process(eventLogger);
    }

    @Override
    public void render(final GameImage canvas) {
        if (condition.get())
            content.render(canvas);
    }

    @Override
    public void debugRender(final GameImage canvas, final GameDebugger debugger) {
        if (condition.get())
            content.debugRender(canvas, debugger);
    }

    @Override
    public void update(final double deltaTime) {
        if (condition.get())
            content.update(deltaTime);
    }
}
