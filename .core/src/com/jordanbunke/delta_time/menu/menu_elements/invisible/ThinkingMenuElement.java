package com.jordanbunke.delta_time.menu.menu_elements.invisible;

import com.jordanbunke.delta_time.debug.GameDebugger;
import com.jordanbunke.delta_time.image.GameImage;
import com.jordanbunke.delta_time.io.InputEventLogger;
import com.jordanbunke.delta_time.menu.menu_elements.MenuElement;
import com.jordanbunke.delta_time.utility.math.Bounds2D;
import com.jordanbunke.delta_time.utility.math.Coord2D;

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
    public void render(final GameImage canvas) {
        if (decision != null)
            decision.render(canvas);
    }

    @Override
    public void debugRender(final GameImage canvas, final GameDebugger debugger) {
        if (decision != null)
            decision.debugRender(canvas, debugger);
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

    @Override
    public void setX(final int x) {
        if (decision != null)
            decision.setX(x);
    }

    @Override
    public void setY(final int y) {
        if (decision != null)
            decision.setY(y);
    }

    @Override
    public void setPosition(final Coord2D position) {
        if (decision != null)
            decision.setPosition(position);
    }

    @Override
    public Coord2D getRenderPosition() {
        return decision != null
                ? decision.getRenderPosition()
                : super.getRenderPosition();
    }

    @Override
    public int getRenderOrder() {
        return decision != null
                ? decision.getRenderOrder()
                : super.getRenderOrder();
    }

    @Override
    public Anchor getAnchor() {
        return decision != null
                ? decision.getAnchor()
                : super.getAnchor();
    }

    @Override
    public int getX() {
        return decision != null
                ? decision.getX()
                : super.getX();
    }

    @Override
    public int getY() {
        return decision != null
                ? decision.getY()
                : super.getY();
    }

    @Override
    public Coord2D getPosition() {
        return decision != null
                ? decision.getPosition()
                : super.getPosition();
    }

    @Override
    public int getWidth() {
        return decision != null
                ? decision.getWidth()
                : super.getWidth();
    }

    @Override
    public int getHeight() {
        return decision != null
                ? decision.getHeight()
                : super.getHeight();
    }

    @Override
    public Bounds2D getDimensions() {
        return decision != null
                ? decision.getDimensions()
                : super.getDimensions();
    }

    @Override
    public String toString() {
        return super.toString() + (decision != null
                ? "\n\tCurrently: " + decision : "");
    }
}
