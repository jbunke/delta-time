package com.jordanbunke.delta_time.menus;

import com.jordanbunke.delta_time.contexts.ProgramContext;
import com.jordanbunke.delta_time.debug.GameDebugger;
import com.jordanbunke.delta_time.image.GameImage;
import com.jordanbunke.delta_time.io.InputEventLogger;
import com.jordanbunke.delta_time.menus.menu_elements.MenuElement;
import com.jordanbunke.delta_time.menus.menu_elements.SelectableMenuElement;

import java.util.function.BiConsumer;

public class Menu implements ProgramContext {
    public enum Direction {
        LEFT, RIGHT, DOWN, UP
    }

    private static final int NO_SELECTION = -1;

    private final MenuElement[] menuElements;
    private final BiConsumer<InputEventLogger, Menu> selectionLogic;

    private int selection = NO_SELECTION;

    public Menu(
            final BiConsumer<InputEventLogger, Menu> selectionLogic,
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

    public void update(final double deltaTime) {
        for (MenuElement element : menuElements)
            element.update(deltaTime);
    }

    @Override
    public void render(final GameImage canvas) {
        final MenuElement[] renderOrder =
                MenuElement.sortForRender(menuElements);

        for (MenuElement element : renderOrder)
            element.render(canvas);
    }

    @Override
    public void debugRender(final GameImage canvas, final GameDebugger debugger) {
        final MenuElement[] renderOrder =
                MenuElement.sortForRender(menuElements);

        for (MenuElement element : renderOrder)
            element.debugRender(canvas, debugger);
    }

    public void process(final InputEventLogger eventLogger) {
        if (selectionLogic != null)
            selectionLogic.accept(eventLogger, this);

        for (MenuElement element : menuElements)
            element.process(eventLogger);
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
