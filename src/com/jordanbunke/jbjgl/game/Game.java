package com.jordanbunke.jbjgl.game;

import com.jordanbunke.jbjgl.image.GameImage;
import com.jordanbunke.jbjgl.window.GameWindow;

public class Game {
    private final GameLoop gameLoop;
    private final GameManager gameManager;

    private final String title;

    public Game(final String title, final GameManager gameManager,
                final GameLoop gameLoop) {
        this.title = title;
        this.gameManager = gameManager;
        this.gameLoop = gameLoop;
    }

    public static void launch(final GameManager gameManager, final GameLoop gameLoop) {
        new Game("", gameManager, gameLoop);
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
        final GameLoop gameLoop = new GameLoop(window,
                gameManager, UPDATE_HZ, TARGET_FPS);
        return new Game(title, gameManager, gameLoop);
    }

    public void replaceWindow(final GameWindow replacement) {
        gameLoop.replaceWindow(replacement);
    }

    public GameLoop getGameEngine() {
        return gameLoop;
    }

    public GameManager getGameManager() {
        return gameManager;
    }

    @Override
    public String toString() {
        return title;
    }
}
