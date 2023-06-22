package com.jordanbunke.jbjgl.image.sprite.constituents;

import com.jordanbunke.jbjgl.image.GameImage;

public interface SpriteConstituent<R> {
    GameImage getSprite(final R spriteID);
}
