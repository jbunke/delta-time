package com.jordanbunke.delta_time.game_world.ecs.basic_components.sprite;

import com.jordanbunke.delta_time.game_world.ecs.basic_components.EntityComponent;
import com.jordanbunke.delta_time.game_world.physics.vector.Vector;
import com.jordanbunke.delta_time.image.GameImage;
import com.jordanbunke.delta_time.utility.math.Coord2D;

public abstract class SpriteComponent<E extends Vector<E>> extends EntityComponent<E> {
    public abstract GameImage getSprite();
    public abstract GameImage getSprite(final String spriteID);
    public abstract Coord2D getSpriteOffset();
}
