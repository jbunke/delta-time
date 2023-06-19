package com.jordanbunke.jbjgl.game;

import com.jordanbunke.jbjgl.contexts.ProgramContext;
import com.jordanbunke.jbjgl.debug.GameDebugger;
import com.jordanbunke.jbjgl.error.GameError;
import com.jordanbunke.jbjgl.io.InputEventLogger;

import java.awt.*;

public class GameManager extends ProgramContext {
    // Useful preset constants for game programming
    public static final int PLAY = 0, PAUSE = 1, MENU = 2, SPLASH_SCREEN = 3;

    private final ProgramContext[] gameStates;
    private int activeStateIndex;

    public GameManager(final int initialStateIndex, final ProgramContext... gameStates) {
        this.gameStates = gameStates;
        activeStateIndex = initialStateIndex;
    }

    public void setGameStateAtIndex(final int index, final ProgramContext gameState) {
        if (index < 0 || index >= gameStates.length) {
            GameError.send("Invalid index for this JBJGLGameManager -  index: " +
                            index + ", length: " + gameStates.length);
        } else
            gameStates[index] = gameState;
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
    public void process(final InputEventLogger eventLogger) {
        gameStates[activeStateIndex].process(eventLogger);
    }

    @Override
    public void update(final double deltaTime) {
        gameStates[activeStateIndex].update(deltaTime);
    }

    @Override
    public void render(final Graphics2D g) {
        gameStates[activeStateIndex].render(g);
    }

    @Override
    public void debugRender(final Graphics2D g, final GameDebugger debugger) {
        gameStates[activeStateIndex].debugRender(g, debugger);
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
