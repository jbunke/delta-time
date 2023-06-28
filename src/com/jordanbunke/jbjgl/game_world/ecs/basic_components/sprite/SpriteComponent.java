package com.jordanbunke.jbjgl.game_world.ecs.basic_components.sprite;

import com.jordanbunke.jbjgl.game_world.ecs.basic_components.EntityComponent;
import com.jordanbunke.jbjgl.game_world.physics.vector.Vector;
import com.jordanbunke.jbjgl.image.GameImage;
import com.jordanbunke.jbjgl.utility.Coord2D;

public abstract class SpriteComponent<E extends Vector<E>> extends EntityComponent<E> {
    @Override
    public void update(final double deltaTime) {

    }

    public abstract GameImage getSprite();
    public abstract GameImage getSprite(final String spriteID);
    public abstract Coord2D getSpriteOffset();
}
