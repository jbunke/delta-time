package com.jordanbunke.delta_time.sprite.constituents;

import com.jordanbunke.delta_time.image.GameImage;
import com.jordanbunke.delta_time.utility.math.Coord2D;

public final class IdentitySpriteConstituent<R> extends OffsetSingleSpriteConstituent<R> {
    public IdentitySpriteConstituent(
            final GameImage content
    ) {
        super(content, x -> new Coord2D());
    }
}
