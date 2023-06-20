package com.jordanbunke.jbjgl.window;

import com.jordanbunke.jbjgl.image.GameImage;
import com.jordanbunke.jbjgl.io.InputEventLogger;

import javax.swing.*;
import java.awt.*;

public class GameWindow {
    private final JFrame frame;
    private final GameCanvas canvas;
    private final InputEventLogger eventLogger;

    private final String title;
    private int width, height;

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

    public void closeInstance() {
        frame.dispose();
    }

    public void draw(final GameImage image) {
        canvas.draw(image);
    }

    public void clearCanvas() {
        canvas.clear();
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
