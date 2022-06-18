package com.jordanbunke.jbjgl.menus.menu_elements;

import com.jordanbunke.jbjgl.contexts.JBJGLMenuManager;
import com.jordanbunke.jbjgl.debug.JBJGLGameDebugger;
import com.jordanbunke.jbjgl.io.JBJGLListener;

import java.awt.*;

public class JBJGLPlaceholderMenuElement extends JBJGLMenuElement {
    private JBJGLPlaceholderMenuElement() {
        super(new int[] { 0, 0 }, new int[] { 1, 1 }, Anchor.LEFT_TOP, false);
    }

    public static JBJGLPlaceholderMenuElement generate() {
        return new JBJGLPlaceholderMenuElement();
    }

    @Override
    public void update() {

    }

    @Override
    public void render(final Graphics g, final JBJGLGameDebugger debugger) {

    }

    @Override
    public void process(final JBJGLListener listener, final JBJGLMenuManager menuManager) {

    }
}
