package com.jordanbunke.jbjgl.window;

import com.jordanbunke.jbjgl.image.GameImage;

import javax.swing.*;
import java.awt.*;

public class JBJGLCanvas extends JPanel {
    private GameImage image;

    private JBJGLCanvas(final int width, final int height) {
        setSize(width, height);
        setPreferredSize(new Dimension(width, height));

        image = new GameImage(width, height);
    }

    static JBJGLCanvas create(final int width, final int height) {
        return new JBJGLCanvas(width, height);
    }

    void draw(final GameImage image) {
        this.image = image;
        repaint();
    }

    void clear() {
        image = new GameImage(getWidth(), getHeight());
        repaint();
    }

    void setSizeFromWindow(final int width, final int height) {
        setSize(width, height);
        setPreferredSize(new Dimension(width, height));
        clear();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, null);
        g.dispose();
    }
}
