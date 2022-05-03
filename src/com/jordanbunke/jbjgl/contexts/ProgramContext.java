package com.jordanbunke.jbjgl.contexts;

import com.jordanbunke.jbjgl.events.JBJGLEvent;
import com.jordanbunke.jbjgl.events.JBJGLEventHandler;
import com.jordanbunke.jbjgl.game_manager.JBJGLGameLogicHandler;
import com.jordanbunke.jbjgl.game_manager.JBJGLGameRenderer;

import java.awt.*;
import java.util.List;

public abstract class ProgramContext implements
        JBJGLGameLogicHandler, JBJGLGameRenderer, JBJGLEventHandler {

    @Override
    public abstract void update();

    @Override
    public abstract void render(Graphics g);

    @Override
    public abstract void process(List<JBJGLEvent> eventList);
}
