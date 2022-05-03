package com.jordanbunke.jbjgl.game_manager;

import com.jordanbunke.jbjgl.contexts.ProgramContext;
import com.jordanbunke.jbjgl.events.JBJGLEvent;
import com.jordanbunke.jbjgl.events.JBJGLEventHandler;

import java.awt.*;
import java.util.List;

public class JBJGLGameManager implements
        JBJGLGameLogicHandler, JBJGLGameRenderer, JBJGLEventHandler {
    // Useful preset constants for game programming
    public static final int PLAY = 0, PAUSE = 1, MENU = 2, SPLASH_SCREEN = 3;

    private final ProgramContext[] gameStates;
    private int activeStateIndex;

    private JBJGLGameManager(final ProgramContext[] gameStates, final int initialStateIndex) {
        this.gameStates = gameStates;
        activeStateIndex = initialStateIndex;
    }

    public static JBJGLGameManager create(final ProgramContext[] gameStates, final int initialStateIndex) {
        return new JBJGLGameManager(gameStates, initialStateIndex);
    }

    public void setActiveStateIndex(int activeStateIndex) {
        this.activeStateIndex = activeStateIndex;
    }

    public int getActiveStateIndex() {
        return activeStateIndex;
    }

    @Override
    public void process(List<JBJGLEvent> eventList) {
        gameStates[activeStateIndex].process(eventList);
    }

    @Override
    public void update() {
        gameStates[activeStateIndex].update();
    }

    @Override
    public void render(Graphics g) {
        gameStates[activeStateIndex].render(g);
    }
}
