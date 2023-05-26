package com.jordanbunke.jbjgl.menus;

import com.jordanbunke.jbjgl.contexts.JBJGLMenuManager;
import com.jordanbunke.jbjgl.debug.JBJGLGameDebugger;
import com.jordanbunke.jbjgl.io.JBJGLListener;
import com.jordanbunke.jbjgl.menus.menu_elements.JBJGLMenuElement;
import com.jordanbunke.jbjgl.menus.menu_elements.JBJGLSelectableMenuElement;

import java.awt.*;
import java.util.function.BiConsumer;

public class JBJGLMenu {
    public enum Direction {
        LEFT, RIGHT, DOWN, UP
    }

    private static final int NO_SELECTION = -1;

    private final JBJGLMenuElement[] menuElements;
    private final BiConsumer<JBJGLListener, JBJGLMenu> selectionLogic;

    private int selection = NO_SELECTION;

    private JBJGLMenu(
            final JBJGLMenuElement[] menuElements,
            final BiConsumer<JBJGLListener, JBJGLMenu> selectionLogic
    ) {
        this.menuElements = menuElements;
        this.selectionLogic = selectionLogic;
    }

    public static JBJGLMenu generate(final JBJGLMenuElement[] menuElements) {
        return new JBJGLMenu(menuElements, null);
    }

    public static JBJGLMenu of(final JBJGLMenuElement... menuElements) {
        return new JBJGLMenu(menuElements, null);
    }

    public static JBJGLMenu generate(
            final BiConsumer<JBJGLListener, JBJGLMenu> selectionLogic,
            final JBJGLMenuElement[] menuElements
    ) {
        return new JBJGLMenu(menuElements, selectionLogic);
    }

    public static JBJGLMenu of(
            final BiConsumer<JBJGLListener, JBJGLMenu> selectionLogic,
            final JBJGLMenuElement... menuElements
    ) {
        return new JBJGLMenu(menuElements, selectionLogic);
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
        if (selectionLogic != null)
            selectionLogic.accept(listener, this);

        for (JBJGLMenuElement element : menuElements)
            element.process(listener, menuManager);
    }

    public boolean attemptChoose() {
        if (selection != NO_SELECTION &&
                menuElements[selection] instanceof JBJGLSelectableMenuElement s)
            return s.attemptChoose();

        return false;
    }

    public void deselect() {
        if (selection != NO_SELECTION &&
                menuElements[selection] instanceof JBJGLSelectableMenuElement s)
            s.deselect();

        selection = NO_SELECTION;
    }

    public boolean select(final Direction direction) {
        final JBJGLMenuElement checkSelected = selection == NO_SELECTION
                ? null
                : menuElements[selection];

        final boolean validSelection = checkSelected instanceof JBJGLSelectableMenuElement &&
                        checkSelected.isVisible();

        if (validSelection) {
            final JBJGLSelectableMenuElement selected = (JBJGLSelectableMenuElement) checkSelected;

            int candidateSelection = NO_SELECTION, candidateSuitability = JBJGLSelectableMenuElement.UNSUITABLE;

            for (int i = 0; i < menuElements.length; i++) {
                if (menuElements[i] instanceof JBJGLSelectableMenuElement s &&
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
                if (menuElements[selection] instanceof JBJGLSelectableMenuElement s)
                    s.select();

                return true;
            }
        } else {
            // select first valid menu element
            for (int i = 0; i < menuElements.length; i++)
                if (menuElements[i] instanceof JBJGLSelectableMenuElement s) {
                    selection = i;
                    s.select();
                    return true;
                }
        }

        return false;
    }

    public JBJGLMenuElement[] getMenuElements() {
        return menuElements;
    }
}
