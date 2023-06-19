package com.jordanbunke.jbjgl.menus;

import com.jordanbunke.jbjgl.error.GameError;
import com.jordanbunke.jbjgl.events.GameEvent;
import com.jordanbunke.jbjgl.events.Key;
import com.jordanbunke.jbjgl.events.GameKeyEvent;
import com.jordanbunke.jbjgl.events.GameMouseMoveEvent;
import com.jordanbunke.jbjgl.io.InputEventLogger;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.function.BiConsumer;

public class MenuSelectionLogic {
    private final Callable<List<Key>> fChoose, fLeft, fUp, fDown, fRight;

    private MenuSelectionLogic() {
        fChoose = () -> List.of(Key.ENTER, Key.SPACE);
        fLeft = () -> List.of(Key.A, Key.LEFT_ARROW);
        fRight = () -> List.of(Key.D, Key.RIGHT_ARROW);
        fUp = () -> List.of(Key.W, Key.UP_ARROW);
        fDown = () -> List.of(Key.S, Key.DOWN_ARROW);
    }

    private MenuSelectionLogic(
            final Callable<List<Key>> fChoose,
            final Callable<List<Key>> fLeft,
            final Callable<List<Key>> fRight,
            final Callable<List<Key>> fUp,
            final Callable<List<Key>> fDown
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
            final Callable<List<Key>> fChoose,
            final Callable<List<Key>> fLeft,
            final Callable<List<Key>> fRight,
            final Callable<List<Key>> fUp,
            final Callable<List<Key>> fDown
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
                    final Key key = keyEvent.getKey();

                    try {
                        if (fLeft.call().contains(key)) {
                            menu.select(Menu.Direction.LEFT);
                            keyEvent.markAsProcessed();
                            return;
                        }
                        else if (fRight.call().contains(key)) {
                            menu.select(Menu.Direction.RIGHT);
                            keyEvent.markAsProcessed();
                            return;
                        }
                        else if (fUp.call().contains(key)) {
                            menu.select(Menu.Direction.UP);
                            keyEvent.markAsProcessed();
                            return;
                        }
                        else if (fDown.call().contains(key)) {
                            menu.select(Menu.Direction.DOWN);
                            keyEvent.markAsProcessed();
                            return;
                        }
                        else if (fChoose.call().contains(key)) {
                            menu.attemptChoose();
                            keyEvent.markAsProcessed();
                            return;
                        }
                    } catch (Exception ex) {
                        GameError.send("Selection logic function call to resolve keystroke resulted in an error");
                    }
                }
            }
        };
    }
}
