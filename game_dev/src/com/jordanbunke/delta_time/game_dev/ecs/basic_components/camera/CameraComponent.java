package com.jordanbunke.delta_time.game_dev.ecs.basic_components.camera;

import com.jordanbunke.delta_time.debug.GameDebugger;
import com.jordanbunke.delta_time.game_dev.physics.vector.Vector;
import com.jordanbunke.delta_time.game_dev.ecs.basic_components.EntityComponent;
import com.jordanbunke.delta_time.game_dev.ecs.GameEntity;
import com.jordanbunke.delta_time.image.GameImage;

import java.util.Collection;

public abstract class CameraComponent<E extends Vector<E>> extends EntityComponent<E> {

    public abstract void draw(final GameImage canvas, final Collection<GameEntity<E>> entities);

    public abstract void debugDraw(
            final GameImage canvas, final GameDebugger debugger,
            final Collection<GameEntity<E>> entities
    );
}
