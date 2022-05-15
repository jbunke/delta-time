package com.jordanbunke.jbjgl.menus.menu_elements;

import com.jordanbunke.jbjgl.contexts.JBJGLMenuManager;
import com.jordanbunke.jbjgl.debug.JBJGLGameDebugger;
import com.jordanbunke.jbjgl.io.JBJGLListener;

import java.awt.*;

public class JBJGLTimedMenuElement extends JBJGLMenuElement {
    private final int timeOut;
    private final Runnable behaviour;

    private int count;

    private JBJGLTimedMenuElement(
            final int timeOut, final Runnable behaviour
    ) {
        super(new int[] { 0, 0 }, new int[] { 1, 1 }, Anchor.CENTRAL, false);

        this.timeOut = timeOut;
        this.behaviour = behaviour;

        count = 0;
    }

    public static JBJGLTimedMenuElement generate(
            final int timeOut, final Runnable behaviour
    ) {
        return new JBJGLTimedMenuElement(timeOut, behaviour);
    }

    @Override
    public void update() {
        count++;

        if (count >= timeOut) {
            behaviour.run();
            count = 0;
        }
    }

    @Override
    public void render(final Graphics g, final JBJGLGameDebugger debugger) {

    }

    @Override
    public void process(final JBJGLListener listener, final JBJGLMenuManager menuManager) {

    }
}
