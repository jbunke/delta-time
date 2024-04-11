package com.jordanbunke.delta_time.menu.menu_elements.ext.dropdown;

import com.jordanbunke.delta_time.debug.GameDebugger;
import com.jordanbunke.delta_time.image.GameImage;
import com.jordanbunke.delta_time.io.InputEventLogger;
import com.jordanbunke.delta_time.menu.menu_elements.MenuElement;
import com.jordanbunke.delta_time.menu.menu_elements.ext.scroll.AbstractVerticalScrollBox;
import com.jordanbunke.delta_time.utility.math.Coord2D;

public sealed abstract class AbstractDropdown extends MenuElement
        permits AbstractNestedDropdown, AbstractOneOfDropdown, AbstractRootDropdown {
    private boolean droppedDown;

    private final int renderOrder;

    private final DropdownItem[] items;

    private MenuElement ddButton;
    private AbstractVerticalScrollBox ddContainer;

    public AbstractDropdown(
            final Coord2D position, final Coord2D dimensions,
            final Anchor anchor, final int renderOrder,
            final DropdownItem[] items
    ) {
        super(position, dimensions, anchor, true);

        droppedDown = false;

        this.renderOrder = renderOrder;

        this.items = items;
    }

    protected void make() {
        ddButton = makeDDButton();
        ddContainer = makeDDContainer(getPosition()
                .displace(contentsDisplacement()));
    }

    protected abstract AbstractVerticalScrollBox makeDDContainer(
            final Coord2D position);

    protected abstract MenuElement makeDDButton();

    protected abstract Coord2D contentsDisplacement();

    protected void select(final int i) {
        if (items[i] instanceof DropdownBehaviour behaviour) {
            behaviour.run();
            close();
        }
    }

    protected boolean isDroppedDown() {
        return droppedDown;
    }

    protected void toggleDropDown() {
        droppedDown = !droppedDown;
    }

    protected void close() {
        droppedDown = false;
    }

    protected void open() {
        droppedDown = true;
    }

    protected void updateDDButton() {
        ddButton = makeDDButton();
    }

    protected String getLabelTextFor(final int i) {
        return items[i].label;
    }

    protected DropdownItem getItem(final int i) {
        return items[i];
    }

    protected int getSize() {
        return items.length;
    }

    @Override
    public void process(final InputEventLogger eventLogger) {
        ddButton.process(eventLogger);

        if (droppedDown)
            ddContainer.process(eventLogger);
    }

    @Override
    public void update(final double deltaTime) {
        ddButton.update(deltaTime);

        if (droppedDown)
            ddContainer.update(deltaTime);
    }

    @Override
    public void render(final GameImage canvas) {
        ddButton.render(canvas);

        if (droppedDown)
            ddContainer.render(canvas);
    }

    @Override
    public void debugRender(final GameImage canvas, final GameDebugger debugger) {

    }

    @Override
    public int getRenderOrder() {
        return droppedDown ? renderOrder : super.getRenderOrder();
    }

    @Override
    public void setX(final int x) {
        super.setX(x);

        ddButton.setX(x);
        ddContainer.setX(x);
    }

    @Override
    public void setY(final int y) {
        super.setY(y);

        ddButton.setY(y);
        ddContainer.setY(y + getHeight());
    }
}
