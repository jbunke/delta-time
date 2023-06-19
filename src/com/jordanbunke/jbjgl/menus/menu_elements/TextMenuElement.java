package com.jordanbunke.jbjgl.menus.menu_elements;

import com.jordanbunke.jbjgl.contexts.JBJGLMenuManager;
import com.jordanbunke.jbjgl.debug.JBJGLGameDebugger;
import com.jordanbunke.jbjgl.image.GameImage;
import com.jordanbunke.jbjgl.io.JBJGLListener;
import com.jordanbunke.jbjgl.text.Text;

import java.awt.*;

public class TextMenuElement extends MenuElement {
    private final GameImage image;

    private TextMenuElement(
            final int[] position, final int[] dimensions, final Anchor anchor, final GameImage image
    ) {
        super(position, dimensions, anchor, true);
        this.image = image;
    }

    public static TextMenuElement generate(
            final int[] position, final Anchor anchor, final Text text
    ) {
        final GameImage image = text.draw();
        final int[] dimensions = new int[] { image.getWidth(), image.getHeight() };
        return new TextMenuElement(position, dimensions, anchor, image);
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
