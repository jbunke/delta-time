package com.jordanbunke.jbjgl.events;

import java.util.List;

public interface JBJGLEventHandler {
    void process(final List<JBJGLEvent> eventList);
}
