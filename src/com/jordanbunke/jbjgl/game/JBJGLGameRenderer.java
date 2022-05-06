package com.jordanbunke.jbjgl.game;

import com.jordanbunke.jbjgl.debug.JBJGLGameDebugger;

import java.awt.*;

public interface JBJGLGameRenderer {
    void render(final Graphics g, final JBJGLGameDebugger debugger);
}
