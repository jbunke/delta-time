package com.jordanbunke.delta_time.menu.menu_elements.invisible;

import com.jordanbunke.delta_time.debug.GameDebugger;
import com.jordanbunke.delta_time.image.GameImage;
import com.jordanbunke.delta_time.io.InputEventLogger;
import com.jordanbunke.delta_time.menu.menu_elements.MenuElement;
import com.jordanbunke.delta_time.utility.math.Coord2D;

import java.util.function.Supplier;

public final class GatewayMenuElement extends InvisibleMenuElement {
    private final MenuElement content;
    private final Supplier<Boolean> condition;

    private boolean passing;

    public GatewayMenuElement(
            final MenuElement content, final Supplier<Boolean> condition
    ) {
        super();

        this.content = content;
        this.condition = condition;

        passing = this.condition.get();
    }

    @Override
    public void process(final InputEventLogger eventLogger) {
        if (passing)
            content.process(eventLogger);
    }

    @Override
    public void render(final GameImage canvas) {
        if (passing)
            content.render(canvas);
    }

    @Override
    public void debugRender(final GameImage canvas, final GameDebugger debugger) {
        if (passing)
            content.debugRender(canvas, debugger);
    }

    @Override
    public void update(final double deltaTime) {
        passing = condition.get();

        if (passing)
            content.update(deltaTime);
    }

    @Override
    public void setX(int x) {
        if (passing)
            content.setX(x);
    }

    @Override
    public void setY(int y) {
        if (passing)
            content.setY(y);
    }

    @Override
    public Coord2D getRenderPosition() {
        return passing
                ? content.getRenderPosition()
                : super.getRenderPosition();
    }

    @Override
    public int getRenderOrder() {
        return passing
                ? content.getRenderOrder()
                : super.getRenderOrder();
    }

    @Override
    public Anchor getAnchor() {
        return condition.get()
                ? content.getAnchor()
                : super.getAnchor();
    }

    @Override
    public int getX() {
        return condition.get()
                ? content.getX()
                : super.getX();
    }

    @Override
    public int getY() {
        return condition.get()
                ? content.getY()
                : super.getY();
    }

    @Override
    public int getWidth() {
        return condition.get()
                ? content.getWidth()
                : super.getWidth();
    }

    @Override
    public int getHeight() {
        return condition.get()
                ? content.getHeight()
                : super.getHeight();
    }
}
