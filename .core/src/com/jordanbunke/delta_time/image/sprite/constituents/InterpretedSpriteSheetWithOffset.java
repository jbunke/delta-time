package com.jordanbunke.delta_time.image.sprite.constituents;

import com.jordanbunke.delta_time.image.GameImage;
import com.jordanbunke.delta_time.image.sprite.SpriteSheet;
import com.jordanbunke.delta_time.utility.math.Coord2D;

import java.util.function.Function;

public final class InterpretedSpriteSheetWithOffset<R> extends InterpretedSpriteSheet<R> {
    private final Function<R, Coord2D> offsetFunction;

    public InterpretedSpriteSheetWithOffset(
            final SpriteSheet spriteSheet,
            final Function<R, Coord2D> coordinateFunction,
            final Function<R, Coord2D> offsetFunction
    ) {
        super(spriteSheet, coordinateFunction);

        this.offsetFunction = offsetFunction;
    }

    @Override
    public GameImage getSprite(final R spriteID) {
        return new OffsetSingleSpriteConstituent<>(
                super.getSprite(spriteID), offsetFunction
        ).getSprite(spriteID);
    }
}
