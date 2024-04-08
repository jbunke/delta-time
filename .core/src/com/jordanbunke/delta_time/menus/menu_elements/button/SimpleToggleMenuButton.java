package com.jordanbunke.delta_time.menus.menu_elements.button;

import com.jordanbunke.delta_time.debug.GameDebugger;
import com.jordanbunke.delta_time.image.GameImage;
import com.jordanbunke.delta_time.utility.math.Coord2D;

import java.util.function.Supplier;

public class SimpleToggleMenuButton extends MenuButtonStub {
    private final GameImage[] nonHighlightedImages, highlightedImages;
    private final Runnable[] chosenBehaviours;
    private final Runnable globalBehaviour;
    private final Supplier<Integer> updateIndexLogic;

    private int index;
    private final int length;

    public SimpleToggleMenuButton(
            final Coord2D position, final Coord2D dimensions,
            final Anchor anchor, final boolean visible,
            final GameImage[] nonHighlightedImages, final GameImage[] highlightedImages,
            final Runnable[] chosenBehaviours, final Supplier<Integer> updateIndexLogic,
            final Runnable globalBehaviour
    ) {
        super(position, dimensions, anchor, visible);

        this.index = 0;
        this.length = nonHighlightedImages.length;

        this.updateIndexLogic = updateIndexLogic;

        this.nonHighlightedImages = nonHighlightedImages;
        this.highlightedImages = highlightedImages;
        this.chosenBehaviours = chosenBehaviours;
        this.globalBehaviour = globalBehaviour;
    }

    @Override
    public void update(final double deltaTime) {
        index = updateIndexLogic.get();
    }

    @Override
    public void render(final GameImage canvas) {
        draw(isHighlighted() ? highlightedImages[index] : nonHighlightedImages[index], canvas);
    }

    @Override
    public void debugRender(final GameImage canvas, GameDebugger debugger) {
        renderBoundingBox(canvas, debugger);
    }

    @Override
    public void execute() {
        chosenBehaviours[index].run();
        globalBehaviour.run();

        index++;
        index %= length;
    }

    public int getIndex() {
        return index;
    }
}
