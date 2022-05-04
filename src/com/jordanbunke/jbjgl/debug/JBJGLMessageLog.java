package com.jordanbunke.jbjgl.debug;

import java.util.function.Consumer;

public class JBJGLMessageLog {
    // TODO: convert back to actual debugger instead of "message log"
    // - add mutable channels for different types of messages
    // - add toggle for showing bounding boxes of menu elements and entities

    private boolean active;
    private final String id;
    private Consumer<String> output;

    private JBJGLMessageLog(
            final String id, final Consumer<String> output, final boolean initiallyActive
    ) {
        this.id = id;
        this.output = output;
        active = initiallyActive;
    }

    public static JBJGLMessageLog initialize(
            final String id, final Consumer<String> output, final boolean initiallyActive
    ) {
        return new JBJGLMessageLog(id, output, initiallyActive);
    }

    public void deactivate() {
        active = false;
    }

    public void activate() {
        active = true;
    }

    public void setOutput(final Consumer<String> output) {
        this.output = output;
    }

    public void printMessage(final String message) {
        if (active)
            output.accept("[ " + id + ": " + message + " ]");
    }
}
