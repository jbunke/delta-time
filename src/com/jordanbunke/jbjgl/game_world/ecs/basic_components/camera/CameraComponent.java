package com.jordanbunke.jbjgl.game_world.ecs.basic_components.camera;

import com.jordanbunke.jbjgl.debug.GameDebugger;
import com.jordanbunke.jbjgl.game_world.ecs.basic_components.EntityComponent;
import com.jordanbunke.jbjgl.game_world.ecs.GameEntity;
import com.jordanbunke.jbjgl.game_world.physics.vector.Vector;
import com.jordanbunke.jbjgl.image.GameImage;

import java.util.Collection;

public abstract class CameraComponent<E extends Vector<E>> extends EntityComponent<E> {

    public abstract void draw(final GameImage canvas, final Collection<GameEntity<E>> entities);

    public abstract void debugDraw(
            final GameImage canvas, final GameDebugger debugger,
            final Collection<GameEntity<E>> entities
    );
}
