package com.jordanbunke.delta_time.game;

import com.jordanbunke.delta_time.debug.GameDebugger;
import com.jordanbunke.delta_time.image.GameImage;

public interface Renderer {
    void render(final GameImage canvas);
    void debugRender(final GameImage canvas, final GameDebugger debugger);
}
