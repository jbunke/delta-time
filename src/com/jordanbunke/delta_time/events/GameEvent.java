package com.jordanbunke.delta_time.events;

public abstract class GameEvent {
    private boolean processed;

    GameEvent() {
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
