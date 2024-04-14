package com.jordanbunke.delta_time.menu.menu_elements.container;

import com.jordanbunke.delta_time.debug.GameDebugger;
import com.jordanbunke.delta_time.image.GameImage;
import com.jordanbunke.delta_time.io.InputEventLogger;
import com.jordanbunke.delta_time.menu.menu_elements.MenuElement;
import com.jordanbunke.delta_time.utility.math.Bounds2D;
import com.jordanbunke.delta_time.utility.math.Coord2D;

import java.util.Arrays;

public abstract class MenuElementContainer extends MenuElement {
    public MenuElementContainer(
            final Coord2D position, final Bounds2D dimensions,
            final Anchor anchor, final boolean visible
    ) {
        super(position, dimensions, anchor, visible);
    }

    public abstract MenuElement[] getMenuElements();

    public abstract boolean hasNonTrivialBehaviour();

    @Override
    public void update(final double deltaTime) {
        for (MenuElement menuElement : getMenuElements())
            menuElement.update(deltaTime);
    }

    @Override
    public void render(final GameImage canvas) {
        final MenuElement[] renderOrder =
                MenuElement.sortForRender(getMenuElements());

        for (MenuElement element : renderOrder)
            element.render(canvas);
    }

    @Override
    public void debugRender(final GameImage canvas, final GameDebugger debugger) {
        final MenuElement[] renderOrder =
                MenuElement.sortForRender(getMenuElements());

        for (MenuElement element : renderOrder)
            element.debugRender(canvas, debugger);
    }

    @Override
    public void process(final InputEventLogger eventLogger) {
        for (MenuElement menuElement : getMenuElements())
            menuElement.process(eventLogger);
    }

    @Override
    public int getRenderOrder() {
        return Arrays.stream(getMenuElements())
                .map(MenuElement::getRenderOrder)
                .reduce(super.getRenderOrder(),
                        Integer::max);
    }

    @Override
    public void setX(int x) {
        final int was = getX();
        super.setX(x);

        for (MenuElement me : getMenuElements())
            me.setX(x + (me.getX() - was));
    }

    @Override
    public void setY(int y) {
        final int was = getY();
        super.setY(y);

        for (MenuElement me : getMenuElements())
            me.setY(y + (me.getY() - was));
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder(super.toString());

        for (final MenuElement me : getMenuElements())
            sb.append("\n\t- ").append(me.toString());

        return sb.toString();
    }
}
