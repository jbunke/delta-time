package com.jordanbunke.delta_time.menus;

import com.jordanbunke.delta_time.io.InputEventLogger;
import com.jordanbunke.delta_time.menus.menu_elements.MenuElement;
import com.jordanbunke.delta_time.menus.menu_elements.container.MenuElementContainer;

import java.util.ArrayList;
import java.util.Arrays;
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
            if (container.hasNonTrivialBehaviour())
                menuElements.add(container);
            else
                addAll(container.getMenuElements());
        } else
            menuElements.add(toAdd);

        return this;
    }

    public MenuBuilder addAll(final MenuElement... toAdd) {
        Arrays.stream(toAdd).forEach(this::add);

        return this;
    }
}
