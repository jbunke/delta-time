package com.jordanbunke.jbjgl;

import com.jordanbunke.jbjgl.fonts.JBJGLFonts;
import com.jordanbunke.jbjgl.image.JBJGLImage;
import com.jordanbunke.jbjgl.text.JBJGLText;
import com.jordanbunke.jbjgl.text.JBJGLTextBuilder;
import com.jordanbunke.jbjgl.window.JBJGLWindow;

import java.awt.*;

public class Tutorial {
    public static void main(String[] args) {
        JBJGLWindow window = JBJGLWindow.create(
                "Example", 200, 200, true, false, false
        );
        window.setSize(800, 450);
        window.draw(one());
        window.draw(two());

        for (int i = 0; i < 256; i++)
            window.draw(three(i));
    }

    private static JBJGLImage one() {
        JBJGLImage image = JBJGLImage.create(800, 450);
        Graphics g = image.getGraphics();
        g.setColor(new Color(255, 100, 0, 255));
        g.fillRect(100, 100, 300, 300);
        g.dispose();

        return image;
    }

    private static JBJGLImage two() {
        JBJGLImage image = JBJGLImage.create(800, 450);
        Graphics g = image.getGraphics();
        g.setColor(new Color(255, 0, 0, 255));
        g.fillRect(400, 100, 300, 300);
        g.dispose();

        return image;
    }

    private static JBJGLImage three(final int i) {
        JBJGLImage image = JBJGLImage.create(800, 450);
        Graphics g = image.getGraphics();

        JBJGLImage text = JBJGLTextBuilder.initialize(
                1, JBJGLText.Orientation.LEFT,
                new Color(i, 255 - i, 0, 255), JBJGLFonts.BASIC_BOLD()
        ).addText("Now isn't that something?").build().draw();
        g.drawImage(text, i, i, null);

        g.dispose();

        return image;
    }
}
