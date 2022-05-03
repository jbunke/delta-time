package com.jordanbunke.jbjgl.game_manager;

import com.jordanbunke.jbjgl.events.JBJGLEvent;
import com.jordanbunke.jbjgl.events.JBJGLEventHandler;
import com.jordanbunke.jbjgl.image.JBJGLImage;
import com.jordanbunke.jbjgl.window.JBJGLWindow;

import java.awt.*;
import java.util.List;

public class JBJGLGameEngine implements Runnable {
    // Constants
    private final double UPDATE_HZ;
    private final int MUST_UPDATE_BEFORE_RENDER;
    private final double TARGET_FPS;

    // Fields
    private final JBJGLWindow window;
    private final Thread updateThread;

    private boolean running;

    private final JBJGLEventHandler eventHandler;
    private final JBJGLGameRenderer renderer;
    private final JBJGLGameLogicHandler logicHandler;

    private JBJGLGameEngine(
            final String title, final int width, final int height,
            final JBJGLImage icon,
            final boolean exitOnClose, final boolean resizable, final boolean maximized,
            final JBJGLEventHandler eventHandler,
            final JBJGLGameRenderer renderer, final JBJGLGameLogicHandler logicHandler,
            final double UPDATE_HZ, final double TARGET_FPS, final int MUST_UPDATE_BEFORE_RENDER
    ) {
        window = JBJGLWindow.create(
                title, width, height, icon, exitOnClose, resizable, maximized
        );
        updateThread = new Thread(this, "[" + title + " execution thread]");
        this.eventHandler = eventHandler;
        this.renderer = renderer;
        this.logicHandler = logicHandler;

        this.UPDATE_HZ = UPDATE_HZ;
        this.TARGET_FPS = TARGET_FPS;
        this.MUST_UPDATE_BEFORE_RENDER = MUST_UPDATE_BEFORE_RENDER;

        initialize();
    }

    private JBJGLGameEngine(
            final JBJGLWindow window, final JBJGLEventHandler eventHandler,
            final JBJGLGameRenderer renderer, final JBJGLGameLogicHandler logicHandler,
            final double UPDATE_HZ, final double TARGET_FPS, final int MUST_UPDATE_BEFORE_RENDER
    ) {
        this.window = window;
        updateThread = new Thread(this, "[" + window.getTitle() + " execution thread]");
        this.eventHandler = eventHandler;
        this.renderer = renderer;
        this.logicHandler = logicHandler;

        this.UPDATE_HZ = UPDATE_HZ;
        this.TARGET_FPS = TARGET_FPS;
        this.MUST_UPDATE_BEFORE_RENDER = MUST_UPDATE_BEFORE_RENDER;

        initialize();
    }

    public static JBJGLGameEngine newWindowed(
            final String title, final int width, final int height,
            final JBJGLImage icon,
            final boolean exitOnClose, final boolean resizable,
            final JBJGLEventHandler eventHandler,
            final JBJGLGameRenderer renderer, final JBJGLGameLogicHandler logicHandler,
            final double UPDATE_HZ, final double TARGET_FPS, final int MUST_UPDATE_BEFORE_RENDER
    ) {
        return new JBJGLGameEngine(
                title, width, height, icon,
                exitOnClose, resizable, false,
                eventHandler, renderer, logicHandler,
                UPDATE_HZ, TARGET_FPS, MUST_UPDATE_BEFORE_RENDER
        );
    }

    public static JBJGLGameEngine newMaximized(
            final String title, final JBJGLImage icon,
            final boolean exitOnClose,
            final JBJGLEventHandler eventHandler,
            final JBJGLGameRenderer renderer, final JBJGLGameLogicHandler logicHandler,
            final double UPDATE_HZ, final double TARGET_FPS, final int MUST_UPDATE_BEFORE_RENDER
    ) {
        final int width = Toolkit.getDefaultToolkit().getScreenSize().width;
        final int height = Toolkit.getDefaultToolkit().getScreenSize().height;
        return new JBJGLGameEngine(
                title, width, height, icon,
                exitOnClose, false, true,
                eventHandler, renderer, logicHandler,
                UPDATE_HZ, TARGET_FPS, MUST_UPDATE_BEFORE_RENDER
        );
    }

    public static JBJGLGameEngine fromExistingWindow(
            final JBJGLWindow window, final JBJGLEventHandler eventHandler,
            final JBJGLGameRenderer renderer, final JBJGLGameLogicHandler logicHandler,
            final double UPDATE_HZ, final double TARGET_FPS, final int MUST_UPDATE_BEFORE_RENDER
    ) {
        return new JBJGLGameEngine(
                window, eventHandler, renderer, logicHandler,
                UPDATE_HZ, TARGET_FPS, MUST_UPDATE_BEFORE_RENDER
        );
    }

    private void initialize() {
        updateThread.start();
        running = true;
    }

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

            // UPDATE BLOCK
            int updateCount = 0;
            while (((now - lastUpdateTime) > NANOSECONDS_PER_UPDATE) &&
                    (updateCount < MUST_UPDATE_BEFORE_RENDER)) {
                update();
                callEventHandler();
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
                    // TODO: FPS is frameCount here (DEBUGGER)
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
        List<JBJGLEvent> eventList = window.getEventList();
        if (!eventList.isEmpty())
            eventHandler.process(eventList);
    }

    private void update() {
        logicHandler.update();
    }

    private void render() {
        JBJGLImage toDraw = JBJGLImage.create(window.getWidth(), window.getHeight());
        Graphics g = toDraw.getGraphics();
        renderer.render(g);
        window.draw(toDraw);
    }
}
