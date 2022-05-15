package com.jordanbunke.jbjgl.menus.menu_elements;

import com.jordanbunke.jbjgl.contexts.JBJGLMenuManager;
import com.jordanbunke.jbjgl.debug.JBJGLGameDebugger;
import com.jordanbunke.jbjgl.image.JBJGLImage;
import com.jordanbunke.jbjgl.io.JBJGLListener;
import com.jordanbunke.jbjgl.text.JBJGLText;

import java.awt.*;

public class JBJGLTextMenuElement extends JBJGLMenuElement {
    private final JBJGLImage image;

    private JBJGLTextMenuElement(
            final int[] position, final int[] dimensions, final Anchor anchor, final JBJGLImage image
    ) {
        super(position, dimensions, anchor, true);
        this.image = image;
    }

    public static JBJGLTextMenuElement generate(
            final int[] position, final Anchor anchor, final JBJGLText text
    ) {
        final JBJGLImage image = text.draw();
        final int[] dimensions = new int[] { image.getWidth(), image.getHeight() };
        return new JBJGLTextMenuElement(position, dimensions, anchor, image);
    }

    @Override
    public void update() {

    }

    @Override
    public void render(final Graphics g, final JBJGLGameDebugger debugger) {
        draw(image, g);

        // Debug
        renderBoundingBox(g, debugger);
    }

    @Override
    public void process(final JBJGLListener listener, final JBJGLMenuManager menuManager) {

    }
}
