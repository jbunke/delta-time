package com.jordanbunke.jbjgl.game;

import com.jordanbunke.jbjgl.image.GameImage;
import com.jordanbunke.jbjgl.window.GameWindow;

public class Game {
    private final GameEngine gameEngine;
    private final GameManager gameManager;

    private final String title;

    public Game(final String title, final GameManager gameManager,
                final GameEngine gameEngine) {
        this.title = title;
        this.gameManager = gameManager;
        this.gameEngine = gameEngine;
    }

    public static Game assemble(
            final String title, final GameManager gameManager,
            final int width, final int height,
            final GameImage icon,
            final boolean exitOnClose, final boolean maximized,
            final double UPDATE_HZ, final double TARGET_FPS
    ) {
        final GameWindow window = new GameWindow(title, width,
                height, icon, exitOnClose, false, maximized);
        final GameEngine gameEngine = new GameEngine(window,
                gameManager, UPDATE_HZ, TARGET_FPS);
        return new Game(title, gameManager, gameEngine);
    }

    public void replaceWindow(final GameWindow replacement) {
        gameEngine.replaceWindow(replacement);
    }

    public GameEngine getGameEngine() {
        return gameEngine;
    }

    public GameManager getGameManager() {
        return gameManager;
    }

    @Override
    public String toString() {
        return title;
    }
}
