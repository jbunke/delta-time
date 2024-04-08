package com.jordanbunke.delta_time.events;

public final class GameMouseScrollEvent extends GameEvent {
    public final int clicksScrolled;

    public GameMouseScrollEvent(final int clicksScrolled) {
        this.clicksScrolled = clicksScrolled;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof GameMouseScrollEvent that)
            return this.clicksScrolled == that.clicksScrolled;
        return false;
    }

    @Override
    public int hashCode() {
        return clicksScrolled;
    }
}
