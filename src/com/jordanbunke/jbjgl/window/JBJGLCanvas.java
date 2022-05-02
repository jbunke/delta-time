package com.jordanbunke.jbjgl.window;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class JBJGLCanvas extends JPanel {
    private JBJGLCanvas(final int width, final int height) {
        setSize(width, height);
        setPreferredSize(new Dimension(width, height));
    }

    public static JBJGLCanvas create(final int width, final int height) {
        return new JBJGLCanvas(width, height);
    }

    public void draw(final BufferedImage image) {
        Graphics g = this.getGraphics();
        g.drawImage(image, 0, 0, null);
        g.dispose();
    }

    public void clear() {
        Graphics g = this.getGraphics();
        g.setColor(new Color(0, 0, 0, 255));
        g.fillRect(0, 0, this.getWidth(), this.getHeight());
        g.dispose();
    }

    public void setSizeFromWindow(final int width, final int height) {
        setSize(width, height);
        setPreferredSize(new Dimension(width, height));
    }
}
