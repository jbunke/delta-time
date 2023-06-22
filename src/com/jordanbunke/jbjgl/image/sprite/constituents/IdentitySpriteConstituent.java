package com.jordanbunke.jbjgl.image.sprite.constituents;

import com.jordanbunke.jbjgl.image.GameImage;
import com.jordanbunke.jbjgl.utility.Coord2D;

public final class IdentitySpriteConstituent<R> extends OffsetSingleSpriteConstituent<R> {
    public IdentitySpriteConstituent(
            final GameImage content
    ) {
        super(content, x -> new Coord2D());
    }
}
