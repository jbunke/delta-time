package com.jordanbunke.jbjgl.contexts;

import com.jordanbunke.jbjgl.debug.JBJGLMessageLog;
import com.jordanbunke.jbjgl.events.JBJGLEventHandler;
import com.jordanbunke.jbjgl.game_manager.JBJGLGameLogicHandler;
import com.jordanbunke.jbjgl.game_manager.JBJGLGameRenderer;
import com.jordanbunke.jbjgl.io.JBJGLListener;

import java.awt.*;

public abstract class ProgramContext implements
        JBJGLGameLogicHandler, JBJGLGameRenderer, JBJGLEventHandler {

    @Override
    public abstract void update(final JBJGLMessageLog messageLog);

    @Override
    public abstract void render(Graphics g);

    @Override
    public abstract void process(final JBJGLListener listener);
}
