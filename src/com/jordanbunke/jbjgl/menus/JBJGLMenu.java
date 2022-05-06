package com.jordanbunke.jbjgl.menus;

import com.jordanbunke.jbjgl.contexts.JBJGLMenuManager;
import com.jordanbunke.jbjgl.debug.JBJGLGameDebugger;
import com.jordanbunke.jbjgl.io.JBJGLListener;
import com.jordanbunke.jbjgl.menus.menu_elements.JBJGLMenuElement;

import java.awt.*;

public class JBJGLMenu {
    private final JBJGLMenuElement[] menuElements;

    private JBJGLMenu(final JBJGLMenuElement[] menuElements) {
        this.menuElements = menuElements;
    }

    public static JBJGLMenu generate(final JBJGLMenuElement[] menuElements) {
        return new JBJGLMenu(menuElements);
    }

    public static JBJGLMenu of(final JBJGLMenuElement... menuElements) {
        return new JBJGLMenu(menuElements);
    }

    public void update() {
        for (JBJGLMenuElement element : menuElements)
            element.update();
    }

    public void render(final Graphics g, final JBJGLGameDebugger debugger) {
        for (JBJGLMenuElement element : menuElements)
            element.render(g, debugger);
    }

    public void process(final JBJGLListener listener, final JBJGLMenuManager menuManager) {
        for (JBJGLMenuElement element : menuElements)
            element.process(listener, menuManager);
    }
}
