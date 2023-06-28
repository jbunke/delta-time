package com.jordanbunke.delta_time.image.sprite.constituents;

import com.jordanbunke.delta_time.image.GameImage;
import com.jordanbunke.delta_time.utility.Coord2D;

import java.util.function.Function;

public class OffsetSingleSpriteConstituent<R> implements SpriteConstituent<R> {
    private final GameImage content;
    private final Function<R, Coord2D> offsetFunction;

    public OffsetSingleSpriteConstituent(
            final GameImage content, final Function<R, Coord2D> offsetFunction
    ) {
        this.content = content;
        this.offsetFunction = offsetFunction;
    }

    @Override
    public GameImage getSprite(final R spriteID) {
        final Coord2D offset = offsetFunction.apply(spriteID);

        if (offset.equals(new Coord2D()))
            return content;

        final GameImage offsetContent = new GameImage(content.getWidth(), content.getHeight());

        offsetContent.draw(content, offset.x, offset.y);

        return offsetContent.submit();
    }
}
