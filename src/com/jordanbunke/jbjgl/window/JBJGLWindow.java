package com.jordanbunke.jbjgl.window;

import javax.swing.*;
import java.awt.image.BufferedImage;

public class JBJGLWindow {
    private final JFrame frame;
    private final JBJGLCanvas canvas;

    private JBJGLWindow(
            final String title, final int width, final int height, final BufferedImage icon,
            final boolean exitOnClose, final boolean resizable, final boolean maximized
    ) {
        this.frame = new JFrame(title);
        this.canvas = JBJGLCanvas.create(width, height);
        frame.setContentPane(canvas);
        frame.pack();

        // frame.setSize(width, height);
        frame.setIconImage(icon);
        frame.setResizable(resizable);
        frame.setDefaultCloseOperation(
                exitOnClose ? JFrame.EXIT_ON_CLOSE : JFrame.DISPOSE_ON_CLOSE
        );

        if (maximized) {
            frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
            frame.setUndecorated(true);
        }

        frame.setVisible(true);
        clearCanvas();
    }

    public static JBJGLWindow create(
            final String title, final int width, final int height,
            final boolean exitOnClose, final boolean resizable, final boolean maximized
    ) {
        return new JBJGLWindow(title, width, height,
                new BufferedImage(20, 20, BufferedImage.TYPE_INT_ARGB),
                exitOnClose, resizable, maximized);
    }

    public static JBJGLWindow create(
            final String title, final int width, final int height, final BufferedImage icon,
            final boolean exitOnClose, final boolean resizable, final boolean maximized
    ) {
        return new JBJGLWindow(title, width, height, icon, exitOnClose, resizable, maximized);
    }

    public void setSize(final int width, final int height) {
        canvas.setSizeFromWindow(width, height);
        frame.pack();
        clearCanvas();
    }

    public void draw(final BufferedImage image) {
        canvas.draw(image);
    }

    public void clearCanvas() {
        canvas.clear();
    }
}
