package com.jordanbunke.delta_time.menus.menu_elements.invisible;

import com.jordanbunke.delta_time.debug.GameDebugger;
import com.jordanbunke.delta_time.image.GameImage;
import com.jordanbunke.delta_time.io.InputEventLogger;
import com.jordanbunke.delta_time.menus.menu_elements.MenuElement;
import com.jordanbunke.delta_time.utility.Coord2D;

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

    @Override
    public Coord2D getRenderPosition() {
        return condition.get() ? content.getRenderPosition() : super.getRenderPosition();
    }

    @Override
    public Anchor getAnchor() {
        return condition.get() ? content.getAnchor() : super.getAnchor();
    }

    @Override
    public int getX() {
        return condition.get() ? content.getX() : super.getX();
    }

    @Override
    public int getY() {
        return condition.get() ? content.getY() : super.getY();
    }

    @Override
    public int getWidth() {
        return condition.get() ? content.getWidth() : super.getWidth();
    }

    @Override
    public int getHeight() {
        return condition.get() ? content.getHeight() : super.getHeight();
    }
}
