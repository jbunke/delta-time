package com.jordanbunke.delta_time.menu.menu_elements.button;

import com.jordanbunke.delta_time.utility.math.Bounds2D;
import com.jordanbunke.delta_time.utility.math.Coord2D;

public abstract class MenuButton extends MenuButtonStub {
    private final Runnable chosenBehaviour;

    public MenuButton(
            final Coord2D position, final Bounds2D dimensions,
            final Anchor anchor, final boolean visible,
            final Runnable chosenBehaviour
    ) {
        super(position, dimensions, anchor, visible);

        this.chosenBehaviour = chosenBehaviour;
    }

    @Override
    public void execute() {
        chosenBehaviour.run();
    }
}
