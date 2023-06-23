package com.jordanbunke.jbjgl.menus.menu_elements.button;

import com.jordanbunke.jbjgl.debug.GameDebugger;
import com.jordanbunke.jbjgl.error.GameError;
import com.jordanbunke.jbjgl.image.GameImage;
import com.jordanbunke.jbjgl.utility.Coord2D;

import java.awt.*;
import java.util.concurrent.Callable;

public class SimpleToggleMenuButton extends MenuButtonStub {
    private final GameImage[] nonHighlightedImages, highlightedImages;
    private final Runnable[] chosenBehaviours;
    private final Runnable globalBehaviour;
    private final Callable<Integer> updateIndexLogic;

    private int index;
    private final int length;

    public SimpleToggleMenuButton(
            final Coord2D position, final Coord2D dimensions,
            final Anchor anchor, final boolean visible,
            final GameImage[] nonHighlightedImages, final GameImage[] highlightedImages,
            final Runnable[] chosenBehaviours, final Callable<Integer> updateIndexLogic,
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
    public void update(double deltaTime) {
        try {
            index = updateIndexLogic.call();
        } catch (Exception e) {
            GameError.send("Could not perform index update in " +
                    this + ".");
        }
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
}
