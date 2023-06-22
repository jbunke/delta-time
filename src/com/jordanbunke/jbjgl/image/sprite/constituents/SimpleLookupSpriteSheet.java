package com.jordanbunke.jbjgl.image.sprite.constituents;

import com.jordanbunke.jbjgl.error.GameError;
import com.jordanbunke.jbjgl.image.GameImage;
import com.jordanbunke.jbjgl.image.sprite.SpriteSheet;
import com.jordanbunke.jbjgl.utility.Coord2D;

import java.util.HashMap;
import java.util.Map;

public final class SimpleLookupSpriteSheet<R> implements SpriteConstituent<R> {
    private final Map<R, Coord2D> spriteIDMap;
    private final SpriteSheet spriteSheet;

    public SimpleLookupSpriteSheet(
            final SpriteSheet spriteSheet, final Map<R, Coord2D> spriteIDMap
    ) {
        this.spriteSheet = spriteSheet;
        this.spriteIDMap = new HashMap<>(spriteIDMap);
    }

    @Override
    public GameImage getSprite(final R spriteID) {
        if (spriteIDMap.containsKey(spriteID))
            return spriteSheet.getSprite(spriteIDMap.get(spriteID));

        GameError.send(
                "Attempted to get sprite with ID \"" + spriteID +
                        "\", which was not found in the map."
        );
        return GameImage.dummy();
    }

    public void putSpriteIDInMap(final R spriteID, final Coord2D coordinate) {
        spriteIDMap.put(spriteID, coordinate);
    }
}
