package com.jordanbunke.delta_time.io;

import com.jordanbunke.delta_time.events.GameEvent;
import com.jordanbunke.delta_time.events.GameKeyEvent;

import java.util.function.Supplier;

public class InputTask {
    private final Supplier<GameKeyEvent> eventGetter;
    private final Runnable task;

    public InputTask(
            final Supplier<GameKeyEvent> eventGetter, final Runnable task
    ) {
        this.eventGetter = eventGetter;
        this.task = task;
    }

    public InputTask(
            final Supplier<GameKeyEvent> eventGetter,
            final Supplier<Boolean> precondition, final Runnable task
    ) {
        this(eventGetter, () -> {
            if (precondition.get())
                task.run();
        });
    }

    public InputTask(final GameKeyEvent event, final Runnable task) {
        this(() -> event, task);
    }

    public GameEvent getEvent() {
        return eventGetter.get();
    }

    public void execute() {
        task.run();
    }
}
