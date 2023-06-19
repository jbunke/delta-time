package com.jordanbunke.jbjgl.menus;

import com.jordanbunke.jbjgl.contexts.JBJGLMenuManager;
import com.jordanbunke.jbjgl.debug.JBJGLGameDebugger;
import com.jordanbunke.jbjgl.io.JBJGLListener;
import com.jordanbunke.jbjgl.menus.menu_elements.MenuElement;
import com.jordanbunke.jbjgl.menus.menu_elements.SelectableMenuElement;

import java.awt.*;
import java.util.function.BiConsumer;

public class Menu {
    public enum Direction {
        LEFT, RIGHT, DOWN, UP
    }

    private static final int NO_SELECTION = -1;

    private final MenuElement[] menuElements;
    private final BiConsumer<JBJGLListener, Menu> selectionLogic;

    private int selection = NO_SELECTION;

    public Menu(
            final BiConsumer<JBJGLListener, Menu> selectionLogic,
            final MenuElement... menuElements
    ) {
        this.menuElements = menuElements;
        this.selectionLogic = selectionLogic;
    }

    public Menu(
            final MenuElement... menuElements
    ) {
        this(null, menuElements);
    }

    public void update() {
        for (MenuElement element : menuElements)
            element.update();
    }

    public void render(final Graphics g, final JBJGLGameDebugger debugger) {
        for (MenuElement element : menuElements)
            element.render(g, debugger);
    }

    public void process(final JBJGLListener listener, final JBJGLMenuManager menuManager) {
        if (selectionLogic != null)
            selectionLogic.accept(listener, this);

        for (MenuElement element : menuElements)
            element.process(listener, menuManager);
    }

    public boolean attemptChoose() {
        if (selection != NO_SELECTION &&
                menuElements[selection] instanceof SelectableMenuElement s)
            return s.attemptChoose();

        return false;
    }

    public void deselect() {
        if (selection != NO_SELECTION &&
                menuElements[selection] instanceof SelectableMenuElement s)
            s.deselect();

        selection = NO_SELECTION;
    }

    public boolean select(final Direction direction) {
        final MenuElement checkSelected = selection == NO_SELECTION
                ? null
                : menuElements[selection];

        final boolean validSelection = checkSelected instanceof SelectableMenuElement &&
                        checkSelected.isVisible();

        if (validSelection) {
            final SelectableMenuElement selected = (SelectableMenuElement) checkSelected;

            int candidateSelection = NO_SELECTION, candidateSuitability = SelectableMenuElement.UNSUITABLE;

            for (int i = 0; i < menuElements.length; i++) {
                if (menuElements[i] instanceof SelectableMenuElement s &&
                        !menuElements[i].equals(selected)) {
                    int sSuitability = s.selectionSuitability(selected, direction);

                    if (sSuitability < candidateSuitability) {
                        candidateSuitability = sSuitability;
                        candidateSelection = i;
                    }
                }
            }

            if (candidateSelection != NO_SELECTION) {
                selected.deselect();

                selection = candidateSelection;
                if (menuElements[selection] instanceof SelectableMenuElement s)
                    s.select();

                return true;
            }
        } else {
            // select first valid menu element
            for (int i = 0; i < menuElements.length; i++)
                if (menuElements[i] instanceof SelectableMenuElement s) {
                    selection = i;
                    s.select();
                    return true;
                }
        }

        return false;
    }

    public MenuElement[] getMenuElements() {
        return menuElements;
    }
}
