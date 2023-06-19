package com.jordanbunke.jbjgl.debug;

import java.util.function.Consumer;

public class DebugChannel {

    private boolean muted;
    private final boolean printsChannelID;
    private final String id;
    private Consumer<String> outputFunction;

    public DebugChannel(
            final String id, final Consumer<String> outputFunction,
            final boolean initiallyMuted, final boolean printsChannelID
    ) {
        this.id = id;
        this.outputFunction = outputFunction;
        muted = initiallyMuted;
        this.printsChannelID = printsChannelID;
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
            outputFunction.accept("[ " + (printsChannelID ? id + " | " : "") + message + " ]");
    }
}
