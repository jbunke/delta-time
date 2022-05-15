package com.jordanbunke.jbjgl.menus.menu_elements;

import com.jordanbunke.jbjgl.contexts.JBJGLMenuManager;
import com.jordanbunke.jbjgl.debug.JBJGLGameDebugger;
import com.jordanbunke.jbjgl.io.JBJGLListener;

import java.awt.*;

public class JBJGLMenuElementGrouping extends JBJGLMenuElement {
    private final JBJGLMenuElement[] menuElements;

    private JBJGLMenuElementGrouping(
            final JBJGLMenuElement[] menuElements
    ) {
        super(new int[] { 0, 0 }, new int[] { 1, 1 }, Anchor.LEFT_TOP, false);

        this.menuElements = menuElements;
    }

    public static JBJGLMenuElementGrouping generateOf(final JBJGLMenuElement... menuElements) {
        return new JBJGLMenuElementGrouping(menuElements);
    }

    public static JBJGLMenuElementGrouping generate(final JBJGLMenuElement[] menuElements) {
        return new JBJGLMenuElementGrouping(menuElements);
    }

    @Override
    public void update() {
        for (JBJGLMenuElement menuElement : menuElements)
            menuElement.update();
    }

    @Override
    public void render(final Graphics g, final JBJGLGameDebugger debugger) {
        for (JBJGLMenuElement menuElement : menuElements)
            menuElement.render(g, debugger);
    }

    @Override
    public void process(final JBJGLListener listener, final JBJGLMenuManager menuManager) {
        for (JBJGLMenuElement menuElement : menuElements)
            menuElement.process(listener, menuManager);
    }
}
