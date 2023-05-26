package com.jordanbunke.jbjgl.menus;

import com.jordanbunke.jbjgl.error.JBJGLError;
import com.jordanbunke.jbjgl.events.JBJGLEvent;
import com.jordanbunke.jbjgl.events.JBJGLKey;
import com.jordanbunke.jbjgl.events.JBJGLKeyEvent;
import com.jordanbunke.jbjgl.events.JBJGLMoveEvent;
import com.jordanbunke.jbjgl.io.JBJGLListener;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.function.BiConsumer;

public class JBJGLMenuSelectionLogic {
    private final Callable<List<JBJGLKey>> fChoose, fLeft, fUp, fDown, fRight;

    private JBJGLMenuSelectionLogic() {
        fChoose = () -> List.of(JBJGLKey.ENTER, JBJGLKey.SPACE);
        fLeft = () -> List.of(JBJGLKey.A, JBJGLKey.LEFT_ARROW);
        fRight = () -> List.of(JBJGLKey.D, JBJGLKey.RIGHT_ARROW);
        fUp = () -> List.of(JBJGLKey.W, JBJGLKey.UP_ARROW);
        fDown = () -> List.of(JBJGLKey.S, JBJGLKey.DOWN_ARROW);
    }

    private JBJGLMenuSelectionLogic(
            final Callable<List<JBJGLKey>> fChoose,
            final Callable<List<JBJGLKey>> fLeft,
            final Callable<List<JBJGLKey>> fRight,
            final Callable<List<JBJGLKey>> fUp,
            final Callable<List<JBJGLKey>> fDown
    ) {
        this.fChoose = fChoose;
        this.fLeft = fLeft;
        this.fRight = fRight;
        this.fUp = fUp;
        this.fDown = fDown;
    }

    public static BiConsumer<JBJGLListener, JBJGLMenu> basic() {
        return new JBJGLMenuSelectionLogic().get();
    }

    public static BiConsumer<JBJGLListener, JBJGLMenu> generate(
            final Callable<List<JBJGLKey>> fChoose,
            final Callable<List<JBJGLKey>> fLeft,
            final Callable<List<JBJGLKey>> fRight,
            final Callable<List<JBJGLKey>> fUp,
            final Callable<List<JBJGLKey>> fDown
    ) {
        return new JBJGLMenuSelectionLogic(fChoose, fLeft, fRight, fUp, fDown).get();
    }

    private BiConsumer<JBJGLListener, JBJGLMenu> get() {
        return (listener, menu) -> {
            // mouse move deselection
            List<JBJGLEvent> unprocessed = listener.getUnprocessedEvents();
            for (JBJGLEvent e : unprocessed) {
                if (e instanceof JBJGLMoveEvent moveEvent &&
                        moveEvent.matchesAction(JBJGLMoveEvent.Action.MOVE)) {
                    menu.deselect();
                    moveEvent.markAsProcessed();
                }
            }

            // direction selection change & keyboard selection
            unprocessed = listener.getUnprocessedEvents();
            for (JBJGLEvent e : unprocessed) {
                if (e instanceof JBJGLKeyEvent keyEvent &&
                        keyEvent.matchesAction(JBJGLKeyEvent.Action.PRESS)) {
                    final JBJGLKey key = keyEvent.getKey();

                    try {
                        if (fLeft.call().contains(key)) {
                            menu.select(JBJGLMenu.Direction.LEFT);
                            keyEvent.markAsProcessed();
                            return;
                        }
                        else if (fRight.call().contains(key)) {
                            menu.select(JBJGLMenu.Direction.RIGHT);
                            keyEvent.markAsProcessed();
                            return;
                        }
                        else if (fUp.call().contains(key)) {
                            menu.select(JBJGLMenu.Direction.UP);
                            keyEvent.markAsProcessed();
                            return;
                        }
                        else if (fDown.call().contains(key)) {
                            menu.select(JBJGLMenu.Direction.DOWN);
                            keyEvent.markAsProcessed();
                            return;
                        }
                        else if (fChoose.call().contains(key)) {
                            menu.attemptChoose();
                            keyEvent.markAsProcessed();
                            return;
                        }
                    } catch (Exception ex) {
                        JBJGLError.send("Selection logic function call to resolve keystroke resulted in an error");
                    }
                }
            }
        };
    }
}
