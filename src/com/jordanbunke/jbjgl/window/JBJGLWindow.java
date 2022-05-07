package com.jordanbunke.jbjgl.window;

import com.jordanbunke.jbjgl.image.JBJGLImage;
import com.jordanbunke.jbjgl.io.JBJGLListener;

import javax.swing.*;

public class JBJGLWindow {
    private final JFrame frame;
    private final JBJGLCanvas canvas;
    private final JBJGLListener listener;

    private final String title;
    private int width, height;

    private JBJGLWindow(
            final String title, final int width, final int height, final JBJGLImage icon,
            final boolean exitOnClose, final boolean resizable, final boolean maximized
    ) {
        this.title = title;
        this.width = width;
        this.height = height;

        frame = new JFrame(title);
        canvas = JBJGLCanvas.create(width, height);
        listener = JBJGLListener.create(canvas);

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

    public static JBJGLWindow createWithoutIcon(
            final String title, final int width, final int height,
            final boolean exitOnClose, final boolean resizable, final boolean maximized
    ) {
        return new JBJGLWindow(title, width, height,
                JBJGLImage.create(20, 20),
                exitOnClose, resizable, maximized);
    }

    public static JBJGLWindow create(
            final String title, final int width, final int height, final JBJGLImage icon,
            final boolean exitOnClose, final boolean resizable, final boolean maximized
    ) {
        return new JBJGLWindow(title, width, height, icon, exitOnClose, resizable, maximized);
    }

    public void setSize(final int width, final int height) {
        this.width = width;
        this.height = height;

        canvas.setSizeFromWindow(width, height);
        frame.pack();
    }

    public void draw(final JBJGLImage image) {
        canvas.draw(image);
    }

    public void clearCanvas() {
        canvas.clear();
    }

    public JBJGLListener getListener() {
        return listener;
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
