package com.jordanbunke.delta_time.sprite.constituents;

import com.jordanbunke.delta_time.image.GameImage;
import com.jordanbunke.delta_time.sprite.SpriteSheet;
import com.jordanbunke.delta_time.utility.math.Coord2D;

import java.util.function.Function;

public class InterpretedSpriteSheet<R> implements SpriteConstituent<R> {
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
