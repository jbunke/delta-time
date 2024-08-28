package com.jordanbunke.delta_time.window;

import com.jordanbunke.delta_time.image.GameImage;
import com.jordanbunke.delta_time.io.InputEventLogger;
import com.jordanbunke.delta_time.utility.math.Bounds2D;
import com.jordanbunke.delta_time.utility.math.MathPlus;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class GameWindow {
    private final JFrame frame;
    private final GameCanvas canvas;
    private final InputEventLogger eventLogger;

    private String title;
    private int width, height;
    private int minWidth, minHeight, maxWidth, maxHeight;

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

        this.minWidth = width;
        this.maxWidth = width;
        this.minHeight = height;
        this.maxHeight = height;

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

        if (resizable)
            setOnResizeBehaviour();

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

    private void setOnResizeBehaviour() {
        frame.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(final ComponentEvent e) {
                enforceSizeBounds();
            }
        });
    }

    private void enforceSizeBounds() {
        final Dimension realSize = canvas.getSize();

        final int width = minWidth <= maxWidth
                ? MathPlus.bounded(minWidth, realSize.width, maxWidth)
                : realSize.width;
        final int height = minHeight <= maxHeight
                ? MathPlus.bounded(minHeight, realSize.height, maxHeight)
                : realSize.height;

        setSize(width, height);
    }

    public void setSizeBounds(final Bounds2D minSize, final Bounds2D maxSize) {
        if (minSize != null)
            setMinSize(minSize.width(), minSize.height());

        if (maxSize != null)
            setMaxSize(maxSize.width(), maxSize.height());
    }

    public void setMinSize(final int minWidth, final int minHeight) {
        if (minWidth <= 0 || minHeight <= 0)
            return;

        canvas.setMinimumSize(new Dimension(minWidth, minHeight));

        this.minWidth = minWidth;
        this.minHeight = minHeight;

        enforceSizeBounds();
    }

    public void setMaxSize(final int maxWidth, final int maxHeight) {
        if (maxWidth <= 0 || maxHeight <= 0)
            return;

        canvas.setMaximumSize(new Dimension(maxWidth, maxHeight));

        this.maxWidth = maxWidth;
        this.maxHeight = maxHeight;

        enforceSizeBounds();
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

    public void focus() {
        canvas.grabFocus();
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
