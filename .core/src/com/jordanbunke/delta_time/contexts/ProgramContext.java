package com.jordanbunke.delta_time.contexts;

import com.jordanbunke.delta_time.events.EventHandler;
import com.jordanbunke.delta_time.game.LogicHandler;
import com.jordanbunke.delta_time.game.Renderer;

public interface ProgramContext extends
        LogicHandler, Renderer, EventHandler {
}
