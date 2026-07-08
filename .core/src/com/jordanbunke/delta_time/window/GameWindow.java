package com.jordanbunke.delta_time.window;

import com.jordanbunke.delta_time.image.GameImage;
import com.jordanbunke.delta_time.io.InputEventLogger;
import com.jordanbunke.delta_time.utility.math.Bounds2D;
import com.jordanbunke.delta_time.utility.math.MathPlus;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.function.BiConsumer;

public class GameWindow {
    private final JFrame frame;
    private final GameCanvas canvas;
    private final InputEventLogger eventLogger;

    private final BiConsumer<Integer, Integer> onResize;
    private final Runnable onClose;

    private String title;

    private int width, height;
    private int minWidth, minHeight, maxWidth, maxHeight;

    private GameWindow(
            final String title, final GameImage icon,
            final int width, final int height,
            final int minWidth, final int minHeight,
            final int maxWidth, final int maxHeight,
            final boolean fullscreen, final boolean canResize,
            final boolean exitOnClose,
            final BiConsumer<Integer, Integer> onResize,
            final Runnable onClose
    ) {
        this.title = title;

        this.width = width;
        this.height = height;

        this.minWidth = minWidth;
        this.maxWidth = maxWidth;
        this.minHeight = minHeight;
        this.maxHeight = maxHeight;

        this.onResize = onResize;
        this.onClose = onClose;

        frame = new JFrame(title);
        canvas = new GameCanvas(width, height);
        eventLogger = InputEventLogger.create(canvas);

        if (fullscreen) {
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

        frame.setResizable(canResize);

        if (canResize)
            addResizeListener();

        frame.setDefaultCloseOperation(
                exitOnClose ? JFrame.EXIT_ON_CLOSE : JFrame.DISPOSE_ON_CLOSE
        );

        frame.setVisible(true);
        clearCanvas();
    }

    @Deprecated
    public GameWindow(final String title, final GameImage icon) {
        this(title, Toolkit.getDefaultToolkit().getScreenSize().width,
                Toolkit.getDefaultToolkit().getScreenSize().height,
                icon, true);
    }

    @Deprecated
    public GameWindow(final String title, final int width, final int height,
                      final GameImage icon, final boolean fullscreen) {
        this(title, width, height, icon, true, false, fullscreen);
    }

    @Deprecated
    public GameWindow(
            final String title, final int width, final int height, final GameImage icon,
            final boolean exitOnClose, final boolean canResize, final boolean fullscreen
    ) {
        this(title, icon, width, height, width, height, width, height,
                fullscreen, canResize, exitOnClose, (w, h) -> {}, () -> {});
    }

    public void setSize(final int width, final int height) {
        this.width = width;
        this.height = height;

        canvas.setSizeFromWindow(width, height);
        frame.pack();
    }

    private void addResizeListener() {
        frame.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(final ComponentEvent e) {
                resized();
            }
        });
    }

    private void resized() {
        enforceSizeBounds();
        onResize.accept(width, height);
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
        if (minWidth < 1 || minHeight < 1)
            return;

        canvas.setMinimumSize(new Dimension(minWidth, minHeight));

        this.minWidth = minWidth;
        this.minHeight = minHeight;

        enforceSizeBounds();
    }

    public void setMaxSize(final int maxWidth, final int maxHeight) {
        if (maxWidth < 1 || maxHeight < 1)
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
        if (onClose != null)
            onClose.run();
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

    public static class Builder {
        private String title;
        private GameImage icon;

        private int width, height, minWidth, minHeight, maxWidth, maxHeight;

        private boolean canResize, fullscreen, exitOnClose;

        private BiConsumer<Integer, Integer> onResize;
        private Runnable onClose;

        public Builder() {
            title = "";
            icon = GameImage.dummy();

            final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            width = screenSize.width;
            height = screenSize.height;
            minWidth = width;
            minHeight = height;
            maxWidth = width;
            maxHeight = height;

            canResize = false;
            fullscreen = true;
            exitOnClose = true;

            onResize = (w, h) -> {};
            onClose = () -> {};
        }

        public Builder setMaxWidth(final int maxWidth) {
            this.maxWidth = Math.max(maxWidth, 1);
            return this;
        }

        public Builder setMaxHeight(final int maxHeight) {
            this.maxHeight = Math.max(maxHeight, 1);
            return this;
        }

        public Builder setMinWidth(final int minWidth) {
            this.minWidth = Math.max(minWidth, 1);
            return this;
        }

        public Builder setMinHeight(final int minHeight) {
            this.minHeight = Math.max(minHeight, 1);
            return this;
        }

        public Builder setWidth(final int width) {
            return setWidth(width, true);
        }

        public Builder setWidth(final int width, final boolean overrideMinMax) {
            this.width = Math.max(width, 1);

            if (overrideMinMax) {
                if (this.width > maxWidth)
                    setMaxWidth(this.width);
                if (this.width < minWidth)
                    setMinWidth(this.width);
            }

            return this;
        }

        public Builder setHeight(final int height) {
            return setHeight(height, true);
        }

        public Builder setHeight(final int height, final boolean overrideMinMax) {
            this.height = Math.max(height, 1);

            if (overrideMinMax) {
                if (this.height > maxHeight)
                    setMaxHeight(this.height);
                if (this.height < minHeight)
                    setMinHeight(this.height);
            }

            return this;
        }

        public Builder setCanResize(final boolean canResize) {
            this.canResize = canResize;
            return this;
        }

        public Builder setOnResize(final BiConsumer<Integer, Integer> onResize) {
            this.onResize = onResize;
            return setCanResize(true);
        }

        public Builder setOnClose(final Runnable onClose) {
            this.onClose = onClose;
            return this;
        }

        public Builder setExitOnClose(final boolean exitOnClose) {
            this.exitOnClose = exitOnClose;
            return this;
        }

        public Builder setFullscreen(final boolean fullscreen) {
            this.fullscreen = fullscreen;
            return this;
        }

        public Builder setTitle(final String title) {
            this.title = title;
            return this;
        }

        public Builder setIcon(final GameImage icon) {
            this.icon = icon;
            return this;
        }

        public GameWindow build() {
            return new GameWindow(title, icon, width, height,
                    minWidth, minHeight, maxWidth, maxHeight,
                    fullscreen, canResize, exitOnClose, onResize, onClose);
        }
    }
}
