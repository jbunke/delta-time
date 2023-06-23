package com.jordanbunke.jbjgl.game_world.object;

import com.jordanbunke.jbjgl.debug.GameDebugger;
import com.jordanbunke.jbjgl.game_world.Vector;
import com.jordanbunke.jbjgl.image.GameImage;

public abstract class RenderGameObject<E extends Vector<E>> extends GameObject<E> {
    public RenderGameObject(final E position) {
        super(position);
    }

    public abstract void render(final Camera<E> camera, final GameImage canvas);

    public abstract void debugRender(final Camera<E> camera, final GameImage canvas, final GameDebugger debugger);
}
