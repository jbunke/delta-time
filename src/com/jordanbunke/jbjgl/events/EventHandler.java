package com.jordanbunke.jbjgl.events;

import com.jordanbunke.jbjgl.io.InputEventLogger;

public interface EventHandler {
    void process(final InputEventLogger eventLogger);
}
