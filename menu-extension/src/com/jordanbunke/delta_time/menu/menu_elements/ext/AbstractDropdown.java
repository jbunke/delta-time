package com.jordanbunke.delta_time.menu.menu_elements.ext;

import com.jordanbunke.delta_time.debug.GameDebugger;
import com.jordanbunke.delta_time.image.GameImage;
import com.jordanbunke.delta_time.io.InputEventLogger;
import com.jordanbunke.delta_time.menu.menu_elements.MenuElement;
import com.jordanbunke.delta_time.menu.menu_elements.button.SimpleToggleMenuButton;
import com.jordanbunke.delta_time.menu.menu_elements.ext.scroll.AbstractVerticalScrollBox;
import com.jordanbunke.delta_time.utility.math.Coord2D;

import java.util.function.Supplier;

public abstract class AbstractDropdown extends MenuElement {
    private boolean droppedDown;

    private final int size, renderOrder;
    private int index;

    private final String[] labels;

    private SimpleToggleMenuButton ddButton;
    private final AbstractVerticalScrollBox ddContainer;

    public AbstractDropdown(
            final Coord2D position, final Coord2D dimensions,
            final Anchor anchor,
            final int dropdownAllowanceY, final int heightPerOption,
            final int renderOrder,
            final String[] labels, final Runnable[] behaviours,
            final Supplier<Integer> initialIndexFunction
    ) {
        super(position, dimensions, anchor, true);

        droppedDown = false;

        size = labels.length;
        index = initialIndexFunction.get();

        this.renderOrder = renderOrder;

        this.labels = labels;

        updateDDButton();

        ddContainer = makeDDContainer(
                position.displace(0, getHeight()), dropdownAllowanceY,
                heightPerOption, behaviours);
    }

    protected abstract AbstractVerticalScrollBox makeDDContainer(
            final Coord2D position, final int dropdownAllowanceY,
            final int heightPerOption, final Runnable[] behaviours);

    protected abstract SimpleToggleMenuButton makeDDButton();

    protected void onSelection(final int index, final Runnable behaviour) {
        behaviour.run();
        this.index = index;
        droppedDown = false;

        updateDDButton();
    }

    protected boolean isDroppedDown() {
        return droppedDown;
    }

    protected void toggleDropDown() {
        droppedDown = !droppedDown;
    }

    private void updateDDButton() {
        ddButton = makeDDButton();
    }

    protected String getCurrentLabelText() {
        return labels[index];
    }

    protected String getLabelTextFor(final int i) {
        return labels[i];
    }

    protected int getSize() {
        return size;
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
