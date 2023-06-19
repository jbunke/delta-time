package com.jordanbunke.jbjgl.contexts;

import com.jordanbunke.jbjgl.debug.GameDebugger;
import com.jordanbunke.jbjgl.io.InputEventLogger;
import com.jordanbunke.jbjgl.menus.Menu;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class MenuManager extends ProgramContext {
    private String activeMenuID;
    private final Map<String, Menu> menuMap;

    public MenuManager(final Map<String, Menu> menuMap, final String initialMenuID) {
        this.menuMap = menuMap;
        activeMenuID = initialMenuID;
    }

    public MenuManager(final Menu firstMenu, final String firstMenuID) {
        this.menuMap = new HashMap<>();
        menuMap.put(firstMenuID, firstMenu);
        activeMenuID = firstMenuID;
    }

    public void addMenu(final String menuID, final Menu menu, final boolean setActive) {
        menuMap.put(menuID, menu);
        if (setActive)
            activeMenuID = menuID;
    }

    public void setActiveMenuID(final String activeMenuID) {
        this.activeMenuID = activeMenuID;
    }

    public String getActiveMenuID() {
        return activeMenuID;
    }

    @Override
    public void update() {
        if (menuMap.containsKey(activeMenuID))
            menuMap.get(activeMenuID).update();
    }

    @Override
    public void render(final Graphics2D g) {
        if (menuMap.containsKey(activeMenuID))
            menuMap.get(activeMenuID).render(g);
    }

    @Override
    public void debugRender(final Graphics2D g, final GameDebugger debugger) {
        if (menuMap.containsKey(activeMenuID))
            menuMap.get(activeMenuID).debugRender(g, debugger);
    }

    @Override
    public void process(final InputEventLogger eventLogger) {
        if (menuMap.containsKey(activeMenuID))
            menuMap.get(activeMenuID).process(eventLogger);
    }

    @Override
    public String toString() {
        return "Menu manager (active menu: \"" + activeMenuID + "\")";
    }
}
