package com.jordanbunke.jbjgl.menus.menu_elements;

import com.jordanbunke.jbjgl.debug.GameDebugger;
import com.jordanbunke.jbjgl.error.GameError;
import com.jordanbunke.jbjgl.events.GameEvent;
import com.jordanbunke.jbjgl.events.GameMouseEvent;
import com.jordanbunke.jbjgl.image.GameImage;
import com.jordanbunke.jbjgl.io.InputEventLogger;

import java.awt.*;
import java.util.List;
import java.util.concurrent.Callable;

public class ToggleClickableMenuElement extends MenuElement {
    private boolean isHighlighted;
    private final GameImage[] nonHighlightedImages, highlightedImages;
    private final Runnable[] onClickBehaviours;
    private final Runnable globalOnClickBehaviour;

    private final Callable<Integer> updateIndexLogic;

    private int index;
    private final int length;

    private ToggleClickableMenuElement(
            final int[] position, final int[] dimensions, final Anchor anchor,
            final GameImage[] nonHighlightedImages, final GameImage[] highlightedImages,
            final Runnable[] onClickBehaviours, final Callable<Integer> updateIndexLogic,
            final Runnable globalOnClickBehaviour
    ) {
        super(position, dimensions, anchor, true);

        this.index = 0;
        this.length = nonHighlightedImages.length;

        this.updateIndexLogic = updateIndexLogic;

        this.nonHighlightedImages = nonHighlightedImages;
        this.highlightedImages = highlightedImages;
        this.onClickBehaviours = onClickBehaviours;
        this.globalOnClickBehaviour = globalOnClickBehaviour;
    }

    public static ToggleClickableMenuElement generate(
            final int[] position, final int[] dimensions, final Anchor anchor,
            final GameImage[] nonHighlightedImages, final GameImage[] highlightedImages,
            final Runnable[] onClickBehaviours
    ) {
        return new ToggleClickableMenuElement(
                position, dimensions, anchor,
                nonHighlightedImages, highlightedImages,
                onClickBehaviours, null, () -> {});
    }

    public static ToggleClickableMenuElement generate(
            final int[] position, final int[] dimensions, final Anchor anchor,
            final GameImage[] nonHighlightedImages, final GameImage[] highlightedImages,
            final Runnable[] onClickBehaviours, final Callable<Integer> updateIndexLogic,
            final Runnable globalOnClickBehaviour
    ) {
        return new ToggleClickableMenuElement(
                position, dimensions, anchor,
                nonHighlightedImages, highlightedImages,
                onClickBehaviours, updateIndexLogic, globalOnClickBehaviour);
    }

    @Override
    public void update(final double deltaTime) {
        try {
            index = updateIndexLogic.call();
        } catch (Exception e) {
            GameError.send("Could not perform index update in " +
                    this + ".");
        }
    }

    @Override
    public void render(final Graphics2D g) {
        draw(isHighlighted ? highlightedImages[index] : nonHighlightedImages[index], g);
    }

    @Override
    public void debugRender(final Graphics2D g, final GameDebugger debugger) {
        renderBoundingBox(g, debugger);
    }

    @Override
    public void process(final InputEventLogger eventLogger) {
        isHighlighted = mouseIsWithinBounds(eventLogger.getMousePosition());

        if (!isHighlighted)
            return;

        final List<GameEvent> unprocessed = eventLogger.getUnprocessedEvents();
        for (GameEvent e : unprocessed) {
            if (e instanceof GameMouseEvent mouseEvent &&
                    mouseEvent.matchesAction(GameMouseEvent.Action.CLICK)) {
                mouseEvent.markAsProcessed();

                onClickBehaviours[index].run();
                globalOnClickBehaviour.run();

                index++;
                index %= length;

                isHighlighted = false;
            }
        }
    }
}
