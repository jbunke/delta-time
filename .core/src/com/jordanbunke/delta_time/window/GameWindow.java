package com.jordanbunke.delta_time.window;

import com.jordanbunke.delta_time.image.GameImage;
import com.jordanbunke.delta_time.io.InputEventLogger;

import javax.swing.*;
import java.awt.*;

public class GameWindow {
    private final JFrame frame;
    private final GameCanvas canvas;
    private final InputEventLogger eventLogger;

    private String title;
    private int width, height;

    private Runnable onCloseBehaviour;

    public GameWindow(final String title, final GameImage icon) {
        this(title, Toolkit.getDefaultToolkit().getScreenSize().width,
                Toolkit.getDefaultToolkit().getScreenSize().height,
                icon, true);
    }

    public GameWindow(final String title, final int width, final int height,
                      final GameImage icon, final boolean maximized) {
        this(title, width, height, icon, true, false, maximized);
    }

    public GameWindow(
            final String title, final int width, final int height, final GameImage icon,
            final boolean exitOnClose, final boolean resizable, final boolean maximized
    ) {
        this.title = title;
        this.width = width;
        this.height = height;

        frame = new JFrame(title);
        canvas = new GameCanvas(width, height);
        eventLogger = InputEventLogger.create(canvas);

        onCloseBehaviour = null;

        if (maximized) {
            frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
            frame.setUndecorated(true);
        }

        frame.setContentPane(canvas);

        // Prevents flickering upon render
        canvas.setDoubleBuffered(true);

        // Must be focusable in order to listen for key events
        canvas.setFocusable(true);
        canvas.requestFocus();

        frame.addWindowListener(eventLogger);
        frame.addComponentListener(eventLogger);

        // Sizes the window according to the size of the canvas
        frame.pack();

        frame.setIconImage(icon);

        frame.setResizable(resizable);
        frame.setDefaultCloseOperation(
                exitOnClose ? JFrame.EXIT_ON_CLOSE : JFrame.DISPOSE_ON_CLOSE
        );

        frame.setVisible(true);
        clearCanvas();
    }

    public void setSize(final int width, final int height) {
        this.width = width;
        this.height = height;

        canvas.setSizeFromWindow(width, height);
        frame.pack();
    }

    public void setPosition(final int x, final int y) {
        frame.setLocation(x, y);
    }

    public void hideCursor() {
        frame.setCursor(Toolkit.getDefaultToolkit().createCustomCursor(
                GameImage.dummy(), new Point(), "hidden"));
    }

    public void resetCursor() {
        frame.setCursor(Cursor.getDefaultCursor());
    }

    public void setTitle(final String title) {
        this.title = title;
        frame.setTitle(title);
    }

    public void disposeFrame() {
        frame.dispose();
    }

    public void closeInstance() {
        disposeFrame();
        executeOnClose();
    }

    public void executeOnClose() {
        if (onCloseBehaviour != null)
            onCloseBehaviour.run();
    }

    public void draw(final GameImage image) {
        canvas.draw(image);
    }

    public void clearCanvas() {
        canvas.clear();
    }

    public void setOnCloseBehaviour(final Runnable onCloseBehaviour) {
        this.onCloseBehaviour = onCloseBehaviour;
    }

    public InputEventLogger getEventLogger() {
        return eventLogger;
    }

    public String getTitle() {
        return title;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
