package com.jordanbunke.jbjgl.image.sprite.constituents;

import com.jordanbunke.jbjgl.image.GameImage;
import com.jordanbunke.jbjgl.image.sprite.SpriteSheet;
import com.jordanbunke.jbjgl.utility.Coord2D;

import java.util.function.Function;

public final class InterpretedSpriteSheet<R> implements SpriteConstituent<R> {
    private final Function<R, Coord2D> coordinateFunction;
    private final SpriteSheet spriteSheet;

    public InterpretedSpriteSheet(
            final SpriteSheet spriteSheet, final Function<R, Coord2D> coordinateFunction
    ) {
        this.coordinateFunction = coordinateFunction;
        this.spriteSheet = spriteSheet;
    }

    @Override
    public GameImage getSprite(R spriteID) {
        final Coord2D coordinate = coordinateFunction.apply(spriteID);
        return spriteSheet.getSprite(coordinate);
    }
}
