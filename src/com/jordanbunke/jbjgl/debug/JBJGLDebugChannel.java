package com.jordanbunke.jbjgl.debug;

import java.util.function.Consumer;

public class JBJGLDebugChannel {

    private boolean muted;
    private final String id;
    private Consumer<String> outputFunction;

    private JBJGLDebugChannel(
            final String id, final Consumer<String> outputFunction, final boolean initiallyMuted
    ) {
        this.id = id;
        this.outputFunction = outputFunction;
        muted = initiallyMuted;
    }

    public static JBJGLDebugChannel initialize(
            final String id, final Consumer<String> outputFunction, final boolean initiallyMuted
    ) {
        return new JBJGLDebugChannel(id, outputFunction, initiallyMuted);
    }

    public void unmute() {
        muted = false;
    }

    public void mute() {
        muted = true;
    }

    public void setOutputFunction(final Consumer<String> outputFunction) {
        this.outputFunction = outputFunction;
    }

    public String getID() {
        return id;
    }

    public void printMessage(final String message) {
        if (!muted)
            outputFunction.accept("[ \"" + id + "\": " + message + " ]");
    }
}
