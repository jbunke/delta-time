package com.jordanbunke.delta_time._core;

import com.jordanbunke.delta_time.debug.GameDebugger;
import com.jordanbunke.delta_time.fonts.DeltaTimeFonts;
import com.jordanbunke.delta_time.image.GameImage;
import com.jordanbunke.delta_time.io.InputEventLogger;
import com.jordanbunke.delta_time.text.Text;
import com.jordanbunke.delta_time.text.TextBuilder;
import com.jordanbunke.delta_time.window.GameWindow;

import java.awt.*;

public class PerformanceExample {
    private static final int ASPECT_W = 16, ASPECT_H = 9, CANVAS_SCALE_UP = 20,
            CANVAS_W = ASPECT_W * CANVAS_SCALE_UP, CANVAS_H = ASPECT_H * CANVAS_SCALE_UP,
            FULLSCREEN_H = 1080, WINDOWED_H = 720, WINDOW_SCALE_UP = WINDOWED_H / CANVAS_H;
    private static final double REFRESH_RATE_HZ = 30d, FPS = 60d;

    private static Program program;

    public static void main(String[] args) {
        launch();
    }

    private static void launch() {
        final GameManager manager = new GameManager(0, new GameContext());
        final GameWindow window = new GameWindow("Example",
                CANVAS_W * WINDOW_SCALE_UP, CANVAS_H * WINDOW_SCALE_UP,
                GameImage.dummy(), false);
        program = new Program(window, manager, REFRESH_RATE_HZ, FPS);
        program.setCanvasSize(CANVAS_W, CANVAS_H);
    }

    private static class GameContext implements ProgramContext {
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
            canvas.fillRectangle(new Color(100, 0, 0, 255), 0, 0, CANVAS_W, CANVAS_H);

            final GameDebugger debugger = program.getDebugger();

            final GameImage u = generateText("update: " + debugger.getUpdateMillis() + " ms"),
                    eh = generateText("event handler: " + debugger.getEventHandlerMillis() + " ms"),
                    r = generateText("render: " + debugger.getRenderMillis() + " ms"),
                    d = generateText("draw: " + debugger.getDrawMillis() + " ms"),
                    c = generateText("cumulative: " + (debugger.getUpdateMillis() +
                            debugger.getEventHandlerMillis() + debugger.getRenderMillis() +
                            debugger.getDrawMillis()) + " ms"),
                    fps = generateText(debugger.getFPS() + " fps");

            canvas.draw(fps, CANVAS_W - (fps.getWidth() + 2), 2);
            canvas.draw(u, 2, 2 + (int)(CANVAS_H * 0.2));
            canvas.draw(eh, 2, 2 + (int)(CANVAS_H * 0.4));
            canvas.draw(r, 2, 2 + (int)(CANVAS_H * 0.6));
            canvas.draw(d, 2, 2 + (int)(CANVAS_H * 0.8));
            canvas.draw(c, 2, 2);

            canvas.free();
        }

        @Override
        public void render(final GameImage canvas) {
            canvas.draw(this.canvas);
        }

        @Override
        public void debugRender(final GameImage canvas, final GameDebugger debugger) {

        }

        @Override
        public void process(final InputEventLogger eventLogger) {

        }
    }

    private static GameImage generateText(final String string) {
        return new TextBuilder(
                CANVAS_SCALE_UP / 20., Text.Orientation.LEFT,
                new Color(0, 0, 0, 255),
                DeltaTimeFonts.getDefault()).addText(string).build().draw();
    }
}
