package com.jordanbunke.delta_time.menus;

import com.jordanbunke.delta_time.io.InputEventLogger;
import com.jordanbunke.delta_time.menus.menu_elements.MenuElement;
import com.jordanbunke.delta_time.menus.menu_elements.container.MenuElementContainer;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;

public class MenuBuilder {

    private final List<MenuElement> menuElements;

    public MenuBuilder() {
        menuElements = new ArrayList<>();
    }

    public MenuBuilder(final MenuElement initial) {
        this();
        add(initial);
    }

    public Menu build(final BiConsumer<InputEventLogger, Menu> selectionLogic) {
        return new Menu(selectionLogic, menuElements.toArray(new MenuElement[0]));
    }

    public Menu build() {
        return new Menu(menuElements.toArray(new MenuElement[0]));
    }

    public MenuBuilder add(final MenuElement toAdd) {
        if (toAdd instanceof MenuElementContainer container) {
            final MenuElement[] contained = container.getMenuElements();

            for (MenuElement e : contained)
                add(e);

            if (container.hasNonTrivialBehaviour())
                menuElements.add(container);
        } else
            menuElements.add(toAdd);

        return this;
    }
}
