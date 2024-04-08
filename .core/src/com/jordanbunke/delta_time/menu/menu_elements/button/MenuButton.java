package com.jordanbunke.delta_time.menu.menu_elements.button;

import com.jordanbunke.delta_time.utility.math.Coord2D;

public abstract class MenuButton extends MenuButtonStub {
    private final Runnable chosenBehaviour;

    public MenuButton(Coord2D position, Coord2D dimensions, Anchor anchor, boolean visible, Runnable chosenBehaviour) {
        super(position, dimensions, anchor, visible);

        this.chosenBehaviour = chosenBehaviour;
    }

    @Override
    public void execute() {
        chosenBehaviour.run();
    }
}
