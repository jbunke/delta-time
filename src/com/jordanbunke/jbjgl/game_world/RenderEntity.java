package com.jordanbunke.jbjgl.game_world;

import com.jordanbunke.jbjgl.debug.JBJGLGameDebugger;

import java.awt.*;

public abstract class RenderEntity<E extends Vector> extends Entity<E> {
    public RenderEntity(final E position) {
        super(position);
    }

    public abstract void render(final Camera<E> camera, final Graphics g);

    public abstract void debugRender(final Camera<E> camera, final Graphics g, final JBJGLGameDebugger debugger);
}
