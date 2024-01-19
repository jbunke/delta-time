package com.jordanbunke.delta_time.game;

import com.jordanbunke.delta_time.debug.GameDebugger;
import com.jordanbunke.delta_time.events.EventHandler;
import com.jordanbunke.delta_time.image.GameImage;
import com.jordanbunke.delta_time.image.ImageProcessing;
import com.jordanbunke.delta_time.io.InputEventLogger;
import com.jordanbunke.delta_time.window.GameWindow;

public class Game implements Runnable {
    private static final double STANDARD_UPDATE_HZ = 100d, STANDARD_FPS = 60d;

    // Constants
    private final double updateHz, targetFps;

    // Fields
    private GameWindow window;
    private final Thread updateThread;
    private int canvasWidth, canvasHeight;
    private boolean scaleUp, running, scheduleUpdates;

    // 3 interfaces ideally implemented as a single game manager
    private final EventHandler eventHandler;
    private final Renderer renderer;
    private final LogicHandler logicHandler;

    private GameDebugger debugger;

    private Game(
            final GameWindow window, final EventHandler eventHandler,
            final Renderer renderer, final LogicHandler logicHandler,
            final double updateHz, final double targetFps
    ) {
        this.window = window;
        updateThread = new Thread(this, "[ \"" + window.getTitle() + "\" game loop ]");

        canvasWidth = window.getWidth();
        canvasHeight = window.getHeight();
        scaleUp = false;

        this.eventHandler = eventHandler;
        this.renderer = renderer;
        this.logicHandler = logicHandler;

        debugger = GameDebugger.newDefault();

        this.updateHz = updateHz;
        this.targetFps = targetFps;
        scheduleUpdates = true;

        initialize();
    }

    public Game(final GameWindow window, final GameManager gameManager) {
        this(window, gameManager, gameManager, gameManager, STANDARD_UPDATE_HZ, STANDARD_FPS);
    }

    public Game(final GameWindow window, final GameManager gameManager,
                final double updateHz, final double targetFps) {
        this(window, gameManager, gameManager, gameManager, updateHz, targetFps);
    }

    private void initialize() {
        running = true;
        updateThread.start();
    }

    public void replaceWindow(final GameWindow replacement) {
        window.disposeFrame();
        window = replacement;

        updateScaleUp();
    }

    @Override
    public void run() {
        final double NANOSECONDS_IN_SECOND = 1e9;

        final double NANOSECONDS_PER_UPDATE = NANOSECONDS_IN_SECOND / updateHz;
        final double NANOSECONDS_PER_RENDER = NANOSECONDS_IN_SECOND / targetFps;

        int frameCount = 0;
        int oldFrameCount = 0;

        double now = System.nanoTime();
        double lastUpdateTime = now;
        double lastRenderTime = now;

        int lastSecondSlice = (int) (lastUpdateTime / NANOSECONDS_IN_SECOND);

        while (running) {
            now = System.nanoTime();

            // UPDATE BLOCK
            final double nanosSinceUpdate = now - lastUpdateTime;

            if (scheduleUpdates) {
                final int updatesScheduled = (int)(nanosSinceUpdate / NANOSECONDS_PER_UPDATE);

                for (int i = 0; i < updatesScheduled; i++) {
                    updateAndProcess(NANOSECONDS_PER_UPDATE);

                    lastUpdateTime += NANOSECONDS_PER_UPDATE;
                }

                if (now - lastUpdateTime > NANOSECONDS_PER_UPDATE)
                    lastUpdateTime = now - NANOSECONDS_PER_UPDATE;
            } else {
                if (nanosSinceUpdate > NANOSECONDS_PER_UPDATE) {
                    updateAndProcess(nanosSinceUpdate);

                    lastUpdateTime = now;
                }
            }

            now = System.nanoTime();

            // RENDER BLOCK
            final double nanosSinceRender = now - lastRenderTime;

            if (nanosSinceRender > NANOSECONDS_PER_RENDER) {
                render();

                frameCount++;
                lastRenderTime = now;
            }

            // FRAME RATE CHECK BLOCK
            int thisSecondSlice = (int) (lastUpdateTime / NANOSECONDS_IN_SECOND);

            if (thisSecondSlice != lastSecondSlice) {
                if (frameCount != oldFrameCount) {
                    debugger.updateFPS(frameCount);
                    debugger.getChannel(GameDebugger.FRAME_RATE).
                            printMessage(frameCount + " FPS" + (Math.abs(frameCount - targetFps) > 1.0
                                    ? " (target: " + (int) targetFps + " FPS)" : ""));
                    oldFrameCount = frameCount;
                }
                frameCount = 0;
                lastSecondSlice = thisSecondSlice;
            }
        }
    }

    public void terminateExecution() {
        running = false;
    }

    private void updateAndProcess(final double deltaTime) {
        // Update
        debugger.startTimer();
        update(deltaTime);
        debugger.setUpdateMillis();

        // Event Handler
        debugger.startTimer();
        callEventHandler();
        debugger.setEventHandlerMillis();
    }

    private void update(final double deltaTime) {
        logicHandler.update(deltaTime);
    }

    private void callEventHandler() {
        eventHandler.process(window.getEventLogger());
        window.getEventLogger().emptyEventList();
    }

    private void render() {
        // Render
        debugger.startTimer();
        final GameImage canvas = new GameImage(canvasWidth, canvasHeight);
        renderer.render(canvas);
        renderer.debugRender(canvas, debugger);
        debugger.setRenderMillis();

        // Draw
        debugger.startTimer();
        window.draw(scaleUp
                ? ImageProcessing.scale(canvas, window.getWidth(), window.getHeight())
                : canvas.submit());
        debugger.setDrawMillis();
    }

    // SETTERS
    public void overrideDebugger(final GameDebugger debugger) {
        this.debugger = debugger;
    }

    public void setScheduleUpdates(final boolean scheduleUpdates) {
        this.scheduleUpdates = scheduleUpdates;
    }

    public void setCanvasSize(final int canvasWidth, final int canvasHeight) {
        this.canvasWidth = canvasWidth;
        this.canvasHeight = canvasHeight;

        updateScaleUp();
    }

    private void updateScaleUp() {
        this.scaleUp = window.getWidth() != canvasWidth ||
                window.getHeight() != canvasHeight;

        if (scaleUp) {
            final InputEventLogger eventLogger = window.getEventLogger();
            eventLogger.setScaleUpRatioX(window.getWidth() / (double)canvasWidth);
            eventLogger.setScaleUpRatioY(window.getHeight() / (double)canvasHeight);
        }
    }

    // GETTERS
    public GameDebugger getDebugger() {
        return debugger;
    }

    public GameWindow getWindow() {
        return window;
    }
}
