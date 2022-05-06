package com.jordanbunke.jbjgl.contexts;

import com.jordanbunke.jbjgl.debug.JBJGLGameDebugger;
import com.jordanbunke.jbjgl.events.JBJGLEventHandler;
import com.jordanbunke.jbjgl.game.JBJGLGameLogicHandler;
import com.jordanbunke.jbjgl.game.JBJGLGameRenderer;
import com.jordanbunke.jbjgl.io.JBJGLListener;

import java.awt.*;

public abstract class ProgramContext implements
        JBJGLGameLogicHandler, JBJGLGameRenderer, JBJGLEventHandler {

    @Override
    public abstract void update();

    @Override
    public abstract void render(final Graphics g, final JBJGLGameDebugger debugger);

    @Override
    public abstract void process(final JBJGLListener listener);
}
