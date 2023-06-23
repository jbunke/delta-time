package com.jordanbunke.jbjgl.game;

import com.jordanbunke.jbjgl.debug.GameDebugger;
import com.jordanbunke.jbjgl.image.GameImage;

public interface Renderer {
    void render(final GameImage canvas);
    void debugRender(final GameImage canvas, final GameDebugger debugger);
}
