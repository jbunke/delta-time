package com.jordanbunke.jbjgl.game;

import com.jordanbunke.jbjgl.contexts.ProgramContext;
import com.jordanbunke.jbjgl.debug.JBJGLGameDebugger;
import com.jordanbunke.jbjgl.fonts.FontsForTests;
import com.jordanbunke.jbjgl.image.JBJGLImage;
import com.jordanbunke.jbjgl.io.JBJGLListener;
import com.jordanbunke.jbjgl.text.JBJGLText;
import com.jordanbunke.jbjgl.text.JBJGLTextBuilder;

import java.awt.*;

public class PerformanceExample {
    private static final int ASPECT_W = 16, ASPECT_H = 9, CANVAS_SCALE_UP = 20,
            CANVAS_W = ASPECT_W * CANVAS_SCALE_UP, CANVAS_H = ASPECT_H * CANVAS_SCALE_UP,
            FULLSCREEN_H = 1080, WINDOWED_H = 720, WINDOW_SCALE_UP = WINDOWED_H / CANVAS_H;
    private static final double REFRESH_RATE_HZ = 60.;

    private static JBJGLGame exampleGame;

    public static void main(String[] args) {
        launch();
    }

    private static void launch() {
        final JBJGLGameManager gameManager = JBJGLGameManager.createOf(0, new GameContext());
        exampleGame = JBJGLGame.create("Example", gameManager,
                CANVAS_W * WINDOW_SCALE_UP, CANVAS_H * WINDOW_SCALE_UP,
                JBJGLImage.create(1, 1), true, false,
                REFRESH_RATE_HZ, REFRESH_RATE_HZ);
        exampleGame.getGameEngine().setRenderDimension(CANVAS_W, CANVAS_H);
        // exampleGame.getGameEngine().getDebugger().hideBoundingBoxes();
    }

    private static class GameContext extends ProgramContext {
        private final JBJGLImage canvas = JBJGLImage.create(CANVAS_W, CANVAS_H);
        private static final int CUTOFF = 20;
        private int c = CUTOFF;

        GameContext() {

        }

        @Override
        public void update() {
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

            final JBJGLGameDebugger debugger = exampleGame.getGameEngine().getDebugger();

            final JBJGLImage u = generateText("update: " + debugger.getUpdateMillis() + " ms"),
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
        public void render(final Graphics g, final JBJGLGameDebugger debugger) {
            g.drawImage(canvas, 0, 0, null);
        }

        @Override
        public void process(final JBJGLListener listener) {

        }
    }

    private static JBJGLImage generateText(final String string) {
        return JBJGLTextBuilder.initialize(
                CANVAS_SCALE_UP / 20., JBJGLText.Orientation.LEFT,
                new Color(0, 0, 0, 255),
                FontsForTests.CLASSIC.getStandard()
        ).addText(string).build().draw();
    }
}
