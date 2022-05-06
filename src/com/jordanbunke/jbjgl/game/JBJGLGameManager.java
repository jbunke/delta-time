package com.jordanbunke.jbjgl.game;

import com.jordanbunke.jbjgl.contexts.ProgramContext;
import com.jordanbunke.jbjgl.debug.JBJGLGameDebugger;
import com.jordanbunke.jbjgl.events.JBJGLEventHandler;
import com.jordanbunke.jbjgl.io.JBJGLListener;

import java.awt.*;

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

    public static JBJGLGameManager createOf(
            final int initialStateIndex, final ProgramContext... gameStates
    ) {
        return new JBJGLGameManager(gameStates, initialStateIndex);
    }

    public void setActiveStateIndex(int activeStateIndex) {
        this.activeStateIndex = activeStateIndex;
    }

    public int getActiveStateIndex() {
        return activeStateIndex;
    }

    public ProgramContext getActiveGameState() {
        return gameStates[activeStateIndex];
    }

    @Override
    public void process(final JBJGLListener listener) {
        gameStates[activeStateIndex].process(listener);
    }

    @Override
    public void update() {
        gameStates[activeStateIndex].update();
    }

    @Override
    public void render(final Graphics g, final JBJGLGameDebugger debugger) {
        gameStates[activeStateIndex].render(g, debugger);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Game Manager: ").append("\n\n");

        for (int i = 0; i < gameStates.length; i++) {
            sb.append(i == activeStateIndex ? "> " : "  ");
            sb.append(i).append(" - ").append(gameStates[i]);
        }

        return sb.toString();
    }
}
