package com.jordanbunke.jbjgl.game;

import com.jordanbunke.jbjgl.debug.GameDebugger;

import java.awt.*;

public interface Renderer {
    void render(final Graphics2D g);
    void debugRender(final Graphics2D g, final GameDebugger debugger);
}
