package com.jordanbunke.delta_time.game;

import com.jordanbunke.delta_time.contexts.ProgramContext;
import com.jordanbunke.delta_time.debug.GameDebugger;
import com.jordanbunke.delta_time.error.GameError;
import com.jordanbunke.delta_time.image.GameImage;
import com.jordanbunke.delta_time.io.InputEventLogger;

public class GameManager implements ProgramContext {
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
            GameError.send("Invalid index for this " + this.getClass().getName() +
                    " -  index: " + index + ", length: " + gameStates.length);
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
    public void render(final GameImage canvas) {
        gameStates[activeStateIndex].render(canvas);
    }

    @Override
    public void debugRender(final GameImage canvas, final GameDebugger debugger) {
        gameStates[activeStateIndex].debugRender(canvas, debugger);
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
