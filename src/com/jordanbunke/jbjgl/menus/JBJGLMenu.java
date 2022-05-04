package com.jordanbunke.jbjgl.menus;

import com.jordanbunke.jbjgl.contexts.JBJGLMenuManager;
import com.jordanbunke.jbjgl.debug.JBJGLMessageLog;
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

    public void update(final JBJGLMessageLog messageLog) {
        for (JBJGLMenuElement element : menuElements)
            element.update(messageLog);
    }

    public void render(final Graphics g) {
        for (JBJGLMenuElement element : menuElements)
            element.render(g);
    }

    public void process(final JBJGLListener listener, final JBJGLMenuManager menuManager) {
        for (JBJGLMenuElement element : menuElements)
            element.process(listener, menuManager);
    }
}
