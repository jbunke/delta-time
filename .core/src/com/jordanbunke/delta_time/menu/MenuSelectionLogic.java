package com.jordanbunke.delta_time.menu;

import com.jordanbunke.delta_time.events.GameEvent;
import com.jordanbunke.delta_time.events.GameKeyEvent;
import com.jordanbunke.delta_time.events.GameMouseMoveEvent;
import com.jordanbunke.delta_time.events.Key;
import com.jordanbunke.delta_time.io.InputEventLogger;

import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Supplier;

public class MenuSelectionLogic {
    private final Supplier<List<Key>> fChoose, fLeft, fUp, fDown, fRight;

    private MenuSelectionLogic() {
        fChoose = () -> List.of(Key.ENTER, Key.SPACE);
        fLeft = () -> List.of(Key.A, Key.LEFT_ARROW);
        fRight = () -> List.of(Key.D, Key.RIGHT_ARROW);
        fUp = () -> List.of(Key.W, Key.UP_ARROW);
        fDown = () -> List.of(Key.S, Key.DOWN_ARROW);
    }

    private MenuSelectionLogic(
            final Supplier<List<Key>> fChoose,
            final Supplier<List<Key>> fLeft,
            final Supplier<List<Key>> fRight,
            final Supplier<List<Key>> fUp,
            final Supplier<List<Key>> fDown
    ) {
        this.fChoose = fChoose;
        this.fLeft = fLeft;
        this.fRight = fRight;
        this.fUp = fUp;
        this.fDown = fDown;
    }

    public static BiConsumer<InputEventLogger, Menu> basic() {
        return new MenuSelectionLogic().get();
    }

    public static BiConsumer<InputEventLogger, Menu> generate(
            final Supplier<List<Key>> fChoose,
            final Supplier<List<Key>> fLeft,
            final Supplier<List<Key>> fRight,
            final Supplier<List<Key>> fUp,
            final Supplier<List<Key>> fDown
    ) {
        return new MenuSelectionLogic(fChoose, fLeft, fRight, fUp, fDown).get();
    }

    private BiConsumer<InputEventLogger, Menu> get() {
        return (listener, menu) -> {
            // mouse move deselection
            List<GameEvent> unprocessed = listener.getUnprocessedEvents();
            for (GameEvent e : unprocessed) {
                if (e instanceof GameMouseMoveEvent moveEvent &&
                        moveEvent.matchesAction(GameMouseMoveEvent.Action.MOVE)) {
                    menu.deselect();
                    moveEvent.markAsProcessed();
                }
            }

            // direction selection change & keyboard selection
            unprocessed = listener.getUnprocessedEvents();
            for (GameEvent e : unprocessed) {
                if (e instanceof GameKeyEvent keyEvent &&
                        keyEvent.matchesAction(GameKeyEvent.Action.PRESS)) {
                    final Key key = keyEvent.key;

                    if (fLeft.get().contains(key)) {
                        menu.select(Menu.Direction.LEFT);
                        keyEvent.markAsProcessed();
                        return;
                    }
                    else if (fRight.get().contains(key)) {
                        menu.select(Menu.Direction.RIGHT);
                        keyEvent.markAsProcessed();
                        return;
                    }
                    else if (fUp.get().contains(key)) {
                        menu.select(Menu.Direction.UP);
                        keyEvent.markAsProcessed();
                        return;
                    }
                    else if (fDown.get().contains(key)) {
                        menu.select(Menu.Direction.DOWN);
                        keyEvent.markAsProcessed();
                        return;
                    }
                    else if (fChoose.get().contains(key)) {
                        menu.attemptChoose();
                        keyEvent.markAsProcessed();
                        return;
                    }
                }
            }
        };
    }
}
