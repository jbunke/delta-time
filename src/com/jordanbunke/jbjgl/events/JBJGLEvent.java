package com.jordanbunke.jbjgl.events;

public abstract class JBJGLEvent {
    private boolean processed;

    JBJGLEvent() {
        processed = false;
    }

    public boolean isProcessed() {
        return processed;
    }

    public void markAsProcessed() {
        processed = true;
    }

    @Override
    public abstract boolean equals(Object o);

    @Override
    public abstract int hashCode();
}
