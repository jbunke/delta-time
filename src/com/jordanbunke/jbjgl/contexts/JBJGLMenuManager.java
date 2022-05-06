package com.jordanbunke.jbjgl.contexts;

import com.jordanbunke.jbjgl.debug.JBJGLGameDebugger;
import com.jordanbunke.jbjgl.io.JBJGLListener;
import com.jordanbunke.jbjgl.menus.JBJGLMenu;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class JBJGLMenuManager extends ProgramContext {
    private String activeMenuID;
    private final Map<String, JBJGLMenu> menuMap;

    private JBJGLMenuManager(final Map<String, JBJGLMenu> menuMap, final String initialMenuID) {
        this.menuMap = menuMap;
        activeMenuID = initialMenuID;
    }

    private JBJGLMenuManager(final JBJGLMenu firstMenu, final String firstMenuID) {
        this.menuMap = new HashMap<>();
        menuMap.put(firstMenuID, firstMenu);
        activeMenuID = firstMenuID;
    }

    public static JBJGLMenuManager create(
            final Map<String, JBJGLMenu> menuMap, final String initialMenuID
    ) {
        return new JBJGLMenuManager(menuMap, initialMenuID);
    }

    public static JBJGLMenuManager initialize(
            final JBJGLMenu firstMenu, final String firstMenuID
    ) {
        return new JBJGLMenuManager(firstMenu, firstMenuID);
    }

    public void addMenu(final String menuID, final JBJGLMenu menu, final boolean setActive) {
        menuMap.put(menuID, menu);
        if (setActive)
            activeMenuID = menuID;
    }

    public void setActiveMenuID(final String activeMenuID) {
        this.activeMenuID = activeMenuID;
    }

    @Override
    public void update() {
        if (menuMap.containsKey(activeMenuID))
            menuMap.get(activeMenuID).update();
    }

    @Override
    public void render(final Graphics g, final JBJGLGameDebugger debugger) {
        if (menuMap.containsKey(activeMenuID))
            menuMap.get(activeMenuID).render(g, debugger);
    }

    @Override
    public void process(final JBJGLListener listener) {
        if (menuMap.containsKey(activeMenuID))
            menuMap.get(activeMenuID).process(listener, this);
    }

    @Override
    public String toString() {
        return "Menu manager (active menu: \"" + activeMenuID + "\")";
    }
}
