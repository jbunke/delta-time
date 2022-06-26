package com.jordanbunke.jbjgl.io;

import com.jordanbunke.jbjgl.events.JBJGLEvent;
import com.jordanbunke.jbjgl.utility.JBJGLGlobal;

import java.util.concurrent.Callable;

public class JBJGLInputTask {
    private final Callable<JBJGLEvent> eventGetter;
    private final Runnable task;

    private JBJGLInputTask(
            final Callable<JBJGLEvent> eventGetter, final Runnable task
    ) {
        this.eventGetter = eventGetter;
        this.task = task;
    }

    public static JBJGLInputTask create(
            final Callable<JBJGLEvent> eventGetter, final Runnable task
    ) {
        return new JBJGLInputTask(eventGetter, task);
    }

    public static JBJGLInputTask create(
            final Callable<JBJGLEvent> eventGetter,
            final Callable<Boolean> precondition, final Runnable task
    ) {
        final Runnable conditionalTask = () -> {
            try {
                if (precondition.call())
                    task.run();
            } catch (Exception e) {
                JBJGLGlobal.printErrorToJBJGLChannel(
                        "Input event task precondition contained an error:\n"
                );
                e.printStackTrace();
            }
        };

        return new JBJGLInputTask(eventGetter, conditionalTask);
    }

    public static JBJGLInputTask create(
            final JBJGLEvent event, final Runnable task
    ) {
        final Callable<JBJGLEvent> eventGetter = () -> event;
        return new JBJGLInputTask(eventGetter, task);
    }

    public JBJGLEvent getEvent() {
        try {
            return eventGetter.call();
        } catch (Exception e) {
            JBJGLGlobal.printErrorToJBJGLChannel(
                    "Input event task event getter contained an error:\n"
            );
            e.printStackTrace();
        }

        return null;
    }

    public void execute() {
        task.run();
    }
}
