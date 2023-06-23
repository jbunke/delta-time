package com.jordanbunke.jbjgl.contexts;

import com.jordanbunke.jbjgl.events.EventHandler;
import com.jordanbunke.jbjgl.game.LogicHandler;
import com.jordanbunke.jbjgl.game.Renderer;

public interface ProgramContext extends
        LogicHandler, Renderer, EventHandler {
}
