package com.jordanbunke.jbjgl.menus.menu_elements;

import com.jordanbunke.jbjgl.contexts.JBJGLMenuManager;
import com.jordanbunke.jbjgl.debug.JBJGLGameDebugger;
import com.jordanbunke.jbjgl.image.JBJGLImage;
import com.jordanbunke.jbjgl.io.JBJGLListener;

import java.awt.*;

public class JBJGLStaticMenuElement extends JBJGLMenuElement {
    private final JBJGLImage image;

    private JBJGLStaticMenuElement(
            final int[] position, final int[] dimensions, final Anchor anchor,
            final JBJGLImage image
    ) {
        super(position, dimensions, anchor, true);

        this.image = image;
    }

    public static JBJGLStaticMenuElement generate(
            final int[] position, final Anchor anchor, final JBJGLImage image
    ) {
        return new JBJGLStaticMenuElement(
                position, new int[] { image.getWidth(), image.getHeight() },
                anchor, image
        );
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
