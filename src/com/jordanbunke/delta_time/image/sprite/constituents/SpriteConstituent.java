package com.jordanbunke.delta_time.image.sprite.constituents;

import com.jordanbunke.delta_time.image.GameImage;

public interface SpriteConstituent<R> {
    GameImage getSprite(final R spriteID);
}
