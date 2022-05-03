package com.jordanbunke.jbjgl.window;

import com.jordanbunke.jbjgl.image.JBJGLImage;

import javax.swing.*;
import java.awt.*;

public class JBJGLCanvas extends JPanel {
    private JBJGLImage image;

    private JBJGLCanvas(final int width, final int height) {
        setSize(width, height);
        setPreferredSize(new Dimension(width, height));

        image = JBJGLImage.create(width, height);
    }

    static JBJGLCanvas create(final int width, final int height) {
        return new JBJGLCanvas(width, height);
    }

    void draw(final JBJGLImage image) {
        this.image = image;
        repaint();
    }

    void clear() {
        image = JBJGLImage.create(getWidth(), getHeight());
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
