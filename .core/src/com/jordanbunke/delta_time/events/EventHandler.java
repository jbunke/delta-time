package com.jordanbunke.delta_time.events;

import com.jordanbunke.delta_time.io.InputEventLogger;

public interface EventHandler {
    void process(final InputEventLogger eventLogger);
}
