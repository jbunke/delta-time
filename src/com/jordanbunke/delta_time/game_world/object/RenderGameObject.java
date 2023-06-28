package com.jordanbunke.delta_time.game_world.object;

import com.jordanbunke.delta_time.debug.GameDebugger;
import com.jordanbunke.delta_time.game_world.physics.vector.Vector;
import com.jordanbunke.delta_time.image.GameImage;

public abstract class RenderGameObject<E extends Vector<E>> extends GameObject<E> {
    public RenderGameObject(final E position) {
        super(position);
    }

    public abstract void render(final Camera<E> camera, final GameImage canvas);

    public abstract void debugRender(final Camera<E> camera, final GameImage canvas, final GameDebugger debugger);
}
