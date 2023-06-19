package com.jordanbunke.jbjgl.game;

import com.jordanbunke.jbjgl.debug.GameDebugger;
import com.jordanbunke.jbjgl.events.EventHandler;
import com.jordanbunke.jbjgl.image.ImageProcessing;
import com.jordanbunke.jbjgl.image.GameImage;
import com.jordanbunke.jbjgl.window.GameWindow;

import java.awt.*;

public class GameEngine implements Runnable {
    // Constants
    private final double UPDATE_HZ;
    private final int MUST_UPDATE_BEFORE_RENDER;
    private final double TARGET_FPS;

    // Fields
    private GameWindow window;
    private final Thread updateThread;
    private int renderWidth, renderHeight;
    private boolean scaleUp;

    private boolean running;

    // 3 interfaces ideally implemented as a single game manager
    private final EventHandler eventHandler;
    private final Renderer renderer;
    private final LogicHandler logicHandler;

    private GameDebugger debugger;

    private GameEngine(
            final GameWindow window, final EventHandler eventHandler,
            final Renderer renderer, final LogicHandler logicHandler,
            final double UPDATE_HZ, final double TARGET_FPS, final int MUST_UPDATE_BEFORE_RENDER
    ) {
        this.window = window;
        updateThread = new Thread(this, "[ \"" + window.getTitle() + "\" game loop ]");

        renderWidth = window.getWidth();
        renderHeight = window.getHeight();
        scaleUp = false;

        this.eventHandler = eventHandler;
        this.renderer = renderer;
        this.logicHandler = logicHandler;

        debugger = GameDebugger.newDefault();

        this.UPDATE_HZ = UPDATE_HZ;
        this.TARGET_FPS = TARGET_FPS;
        this.MUST_UPDATE_BEFORE_RENDER = MUST_UPDATE_BEFORE_RENDER;

        initialize();
    }

    public static GameEngine newForGame(
            final GameWindow window, final GameManager gameManager
    ) {
        return new GameEngine(
                window, gameManager, gameManager, gameManager,
                60.0, 60.0, 5
        );
    }

    public static GameEngine newForGame(
            final GameWindow window, final GameManager gameManager,
            final double UPDATE_HZ, final double TARGET_FPS
    ) {
        return new GameEngine(
                window, gameManager, gameManager, gameManager,
                UPDATE_HZ, TARGET_FPS, 5
        );
    }

    public static GameEngine newWindowed(
            final String title, final int width, final int height,
            final GameImage icon,
            final boolean exitOnClose, final boolean resizable,
            final EventHandler eventHandler,
            final Renderer renderer, final LogicHandler logicHandler,
            final double UPDATE_HZ, final double TARGET_FPS, final int MUST_UPDATE_BEFORE_RENDER
    ) {
        final GameWindow window = new GameWindow(title, width, height,
                icon, exitOnClose, resizable, false);

        return new GameEngine(
                window, eventHandler, renderer, logicHandler,
                UPDATE_HZ, TARGET_FPS, MUST_UPDATE_BEFORE_RENDER
        );
    }

    public static GameEngine newMaximized(
            final String title, final GameImage icon,
            final boolean exitOnClose,
            final EventHandler eventHandler,
            final Renderer renderer, final LogicHandler logicHandler,
            final double UPDATE_HZ, final double TARGET_FPS, final int MUST_UPDATE_BEFORE_RENDER
    ) {
        final int width = Toolkit.getDefaultToolkit().getScreenSize().width;
        final int height = Toolkit.getDefaultToolkit().getScreenSize().height;

        final GameWindow window = new GameWindow(title, width, height,
                icon, exitOnClose, false, true);

        return new GameEngine(
                window, eventHandler, renderer, logicHandler,
                UPDATE_HZ, TARGET_FPS, MUST_UPDATE_BEFORE_RENDER
        );
    }

    public static GameEngine fromExistingWindow(
            final GameWindow window, final EventHandler eventHandler,
            final Renderer renderer, final LogicHandler logicHandler,
            final double UPDATE_HZ, final double TARGET_FPS, final int MUST_UPDATE_BEFORE_RENDER
    ) {
        return new GameEngine(
                window, eventHandler, renderer, logicHandler,
                UPDATE_HZ, TARGET_FPS, MUST_UPDATE_BEFORE_RENDER
        );
    }

    private void initialize() {
        running = true;
        updateThread.start();
    }

    public void replaceWindow(final GameWindow replacement) {
        final boolean adjustSize = window.getWidth() == renderWidth &&
                window.getHeight() == renderHeight;

        window.closeInstance();
        window = replacement;

        if (adjustSize)
            setRenderDimension(window.getWidth(), window.getHeight());
    }

    // GAME LOOP
    @Override
    public void run() {
        final double NANOSECONDS_IN_SECOND = 1e9;

        final double NANOSECONDS_PER_UPDATE = NANOSECONDS_IN_SECOND / UPDATE_HZ;
        final double NANOSECONDS_PER_RENDER = NANOSECONDS_IN_SECOND / TARGET_FPS;

        double lastUpdateTime = System.nanoTime();
        double lastRenderTime;

        int frameCount = 0;
        int lastSecondSlice = (int) (lastUpdateTime / NANOSECONDS_IN_SECOND);
        int oldFrameCount = 0;

        while (running) {
            double now = System.nanoTime();
            long refMillis;

            // UPDATE BLOCK
            int updateCount = 0;
            while (((now - lastUpdateTime) > NANOSECONDS_PER_UPDATE) &&
                    (updateCount < MUST_UPDATE_BEFORE_RENDER)) {
                // Update
                refMillis = System.currentTimeMillis();
                update();
                debugger.setUpdateMillis(System.currentTimeMillis() - refMillis);

                // Event Handler
                refMillis = System.currentTimeMillis();
                callEventHandler();
                debugger.setEventHandlerMillis(System.currentTimeMillis() - refMillis);

                lastUpdateTime += NANOSECONDS_PER_UPDATE;
                updateCount++;
            }

            if (now - lastUpdateTime > NANOSECONDS_PER_UPDATE)
                lastUpdateTime = now - NANOSECONDS_PER_UPDATE;

            // RENDER BLOCK
            render();

            lastRenderTime = now;
            frameCount++;

            // FRAME RATE CHECK BLOCK
            int thisSecondSlice = (int) (lastUpdateTime / NANOSECONDS_IN_SECOND);

            if (thisSecondSlice != lastSecondSlice) {
                if (frameCount != oldFrameCount) {
                    debugger.updateFPS(frameCount);
                    debugger.getChannel(GameDebugger.FRAME_RATE).
                            printMessage(frameCount + " FPS" + (Math.abs(frameCount - TARGET_FPS) > 1.0
                                    ? " (target: " + (int)TARGET_FPS + " FPS)" : ""));
                    oldFrameCount = frameCount;
                }
                frameCount = 0;
                lastSecondSlice = thisSecondSlice;
            }

            // BREATHE BLOCK
            while ((now - lastRenderTime < NANOSECONDS_PER_RENDER) &&
                    (now - lastUpdateTime) < NANOSECONDS_PER_UPDATE) {
                Thread.yield();

                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                now = System.nanoTime();
            }
        }
    }

    private void callEventHandler() {
        eventHandler.process(window.getEventLogger());
        window.getEventLogger().emptyEventList();
    }

    private void update() {
        logicHandler.update();
    }

    private void render() {
        long refMillis;

        // Render
        refMillis = System.currentTimeMillis();
        GameImage toDraw = new GameImage(renderWidth, renderHeight);
        Graphics2D g = toDraw.g();
        renderer.render(g);
        renderer.debugRender(g, debugger);
        debugger.setRenderMillis(System.currentTimeMillis() - refMillis);

        // Draw
        refMillis = System.currentTimeMillis();
        window.draw(scaleUp ? ImageProcessing.scaleUp(toDraw,
                window.getWidth() / renderWidth) : toDraw);
        debugger.setDrawMillis(System.currentTimeMillis() - refMillis);
    }

    // SETTERS
    public void overrideDebugger(final GameDebugger debugger) {
        this.debugger = debugger;
    }

    public void setRenderDimension(final int renderWidth, final int renderHeight) {
        this.renderWidth = renderWidth;
        this.renderHeight = renderHeight;

        this.scaleUp = window.getWidth() != renderWidth ||
                window.getHeight() != renderHeight;

        if (scaleUp)
            window.getEventLogger().setScaleUpRatio(
                    window.getWidth() / (double)renderWidth,
                    window.getHeight() / (double)renderHeight
            );
    }

    // GETTERS
    public GameDebugger getDebugger() {
        return debugger;
    }

    public GameWindow getWindow() {
        return window;
    }
}
