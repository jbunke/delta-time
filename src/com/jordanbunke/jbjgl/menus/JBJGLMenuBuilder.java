package com.jordanbunke.jbjgl.menus;

import com.jordanbunke.jbjgl.io.JBJGLListener;
import com.jordanbunke.jbjgl.menus.menu_elements.JBJGLMenuElementContainer;
import com.jordanbunke.jbjgl.menus.menu_elements.JBJGLMenuElement;
import com.jordanbunke.jbjgl.menus.menu_elements.JBJGLMenuElementGrouping;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;

public class JBJGLMenuBuilder {

    public static JBJGLMenu build(
            final JBJGLMenuElementGrouping grouping,
            final BiConsumer<JBJGLListener, JBJGLMenu> selectionLogic
    ) {
        final List<JBJGLMenuElement> menuElements = new ArrayList<>();

        add(grouping, menuElements);

        return JBJGLMenu.generate(selectionLogic, menuElements.toArray(new JBJGLMenuElement[0]));
    }

    private static void add(final JBJGLMenuElement toAdd, final List<JBJGLMenuElement> menuElements) {
        if (toAdd instanceof JBJGLMenuElementContainer container) {
            final JBJGLMenuElement[] contained = container.getMenuElements();

            for (JBJGLMenuElement e : contained)
                add(e, menuElements);

            if (container.hasNonTrivialBehaviour())
                add(container, menuElements);
        } else
            menuElements.add(toAdd);
    }
}
