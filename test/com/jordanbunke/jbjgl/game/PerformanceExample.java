package com.jordanbunke.jbjgl.game;

import com.jordanbunke.jbjgl.contexts.ProgramContext;
import com.jordanbunke.jbjgl.debug.GameDebugger;
import com.jordanbunke.jbjgl.fonts.FontsForTests;
import com.jordanbunke.jbjgl.image.GameImage;
import com.jordanbunke.jbjgl.io.InputEventLogger;
import com.jordanbunke.jbjgl.text.Text;
import com.jordanbunke.jbjgl.text.TextBuilder;
import com.jordanbunke.jbjgl.window.GameWindow;

import java.awt.*;

public class PerformanceExample {
    private static final int ASPECT_W = 16, ASPECT_H = 9, CANVAS_SCALE_UP = 20,
            CANVAS_W = ASPECT_W * CANVAS_SCALE_UP, CANVAS_H = ASPECT_H * CANVAS_SCALE_UP,
            FULLSCREEN_H = 1080, WINDOWED_H = 720, WINDOW_SCALE_UP = WINDOWED_H / CANVAS_H;
    private static final double REFRESH_RATE_HZ = 30d, FPS = 60d;

    private static Game exampleGame;

    public static void main(String[] args) {
        launch();
    }

    private static void launch() {
        final GameManager manager = new GameManager(0, new GameContext());
        final GameWindow window = new GameWindow("Example",
                (int)((ASPECT_W / (double)(ASPECT_H)) * FULLSCREEN_H), FULLSCREEN_H,
                GameImage.dummy(), true);
        final GameEngine engine = new GameEngine(window, manager, REFRESH_RATE_HZ, FPS);

        exampleGame = new Game("Example", manager, engine);
        engine.setRenderDimension(CANVAS_W, CANVAS_H);
    }

    private static class GameContext extends ProgramContext {
        private final GameImage canvas = new GameImage(CANVAS_W, CANVAS_H);
        private static final int CUTOFF = 20;
        private int c = CUTOFF;

        GameContext() {

        }

        @Override
        public void update(final double deltaTime) {
            c++;

            if (c >= CUTOFF) {
                drawCanvas();
                c = 0;
            }
        }

        private void drawCanvas() {
            final Graphics g = canvas.getGraphics();

            g.setColor(new Color(100, 0, 0, 255));
            g.fillRect(0, 0, CANVAS_W, CANVAS_H);

            final GameDebugger debugger = exampleGame.getGameEngine().getDebugger();

            final GameImage u = generateText("update: " + debugger.getUpdateMillis() + " ms"),
                    eh = generateText("event handler: " + debugger.getEventHandlerMillis() + " ms"),
                    r = generateText("render: " + debugger.getRenderMillis() + " ms"),
                    d = generateText("draw: " + debugger.getDrawMillis() + " ms"),
                    c = generateText("cumulative: " + (debugger.getUpdateMillis() +
                            debugger.getEventHandlerMillis() + debugger.getRenderMillis() +
                            debugger.getDrawMillis()) + " ms"),
                    fps = generateText(debugger.getFPS() + " fps");

            g.drawImage(fps, CANVAS_W - (fps.getWidth() + 2), 2, null);
            g.drawImage(u, 2, 2 + (int)(CANVAS_H * 0.2), null);
            g.drawImage(eh, 2, 2 + (int)(CANVAS_H * 0.4), null);
            g.drawImage(r, 2, 2 + (int)(CANVAS_H * 0.6), null);
            g.drawImage(d, 2, 2 + (int)(CANVAS_H * 0.8), null);
            g.drawImage(c, 2, 2, null);

            g.dispose();
        }

        @Override
        public void render(final Graphics2D g) {
            g.drawImage(canvas, 0, 0, null);
        }

        @Override
        public void debugRender(Graphics2D g, GameDebugger debugger) {

        }

        @Override
        public void process(final InputEventLogger eventLogger) {

        }
    }

    private static GameImage generateText(final String string) {
        return new TextBuilder(
                CANVAS_SCALE_UP / 20., Text.Orientation.LEFT,
                new Color(0, 0, 0, 255),
                FontsForTests.CLASSIC.getStandard()
        ).addText(string).build().draw();
    }
}
