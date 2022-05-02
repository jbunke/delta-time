package com.jordanbunke.jbjgl;

import com.jordanbunke.jbjgl.fonts.JBJGLFonts;
import com.jordanbunke.jbjgl.text.JBJGLText;
import com.jordanbunke.jbjgl.text.JBJGLTextBuilder;
import com.jordanbunke.jbjgl.window.JBJGLWindow;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Tutorial {
    public static void main(String[] args) {
        JBJGLWindow window = JBJGLWindow.create(
                "Example", 200, 200, true, false, false
        );
        window.setSize(800, 450);
        window.draw(one());
        window.clearCanvas();
        window.draw(two());
        window.draw(three());
    }

    private static BufferedImage one() {
        BufferedImage image = new BufferedImage(800, 450, BufferedImage.TYPE_INT_ARGB);
        Graphics g = image.getGraphics();
        g.setColor(new Color(255, 100, 0, 255));
        g.fillRect(100, 100, 300, 300);
        g.dispose();

        return image;
    }

    private static BufferedImage two() {
        BufferedImage image = new BufferedImage(800, 450, BufferedImage.TYPE_INT_ARGB);
        Graphics g = image.getGraphics();
        g.setColor(new Color(255, 0, 0, 255));
        g.fillRect(400, 100, 300, 300);
        g.dispose();

        return image;
    }

    private static BufferedImage three() {
        BufferedImage image = new BufferedImage(800, 450, BufferedImage.TYPE_INT_ARGB);
        Graphics g = image.getGraphics();

        BufferedImage text = JBJGLTextBuilder.initialize(
                1, JBJGLText.Orientation.LEFT,
                new Color(0, 255, 0, 255), JBJGLFonts.BASIC_BOLD()
        ).addText("Now isn't that something?").build().draw();
        g.drawImage(text, 0, 0, null);

        g.dispose();

        return image;
    }
}
