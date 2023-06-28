package com.jordanbunke.delta_time.io;

import com.jordanbunke.delta_time.error.GameError;
import com.jordanbunke.delta_time.events.GameEvent;
import com.jordanbunke.delta_time.events.GameKeyEvent;

import java.util.concurrent.Callable;

public class InputTask {
    private final Callable<GameKeyEvent> eventGetter;
    private final Runnable task;

    public InputTask(
            final Callable<GameKeyEvent> eventGetter, final Runnable task
    ) {
        this.eventGetter = eventGetter;
        this.task = task;
    }

    public InputTask(
            final Callable<GameKeyEvent> eventGetter,
            final Callable<Boolean> precondition, final Runnable task
    ) {
        this(eventGetter, () -> {
            try {
                if (precondition.call())
                    task.run();
            } catch (Exception e) {
                GameError.send(
                        "Input event task precondition contained an error: \n",
                        e::printStackTrace, true, false);
            }
        });
    }

    public InputTask(final GameKeyEvent event, final Runnable task) {
        this(() -> event, task);
    }

    public GameEvent getEvent() {
        try {
            return eventGetter.call();
        } catch (Exception e) {
            GameError.send(
                    "Input event task event getter contained an error:",
                    e::printStackTrace, true, false);
        }

        return null;
    }

    public void execute() {
        task.run();
    }
}
