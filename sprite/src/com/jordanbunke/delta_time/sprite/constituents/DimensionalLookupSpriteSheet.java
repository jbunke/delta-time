package com.jordanbunke.delta_time.sprite.constituents;

import com.jordanbunke.delta_time.error.GameError;
import com.jordanbunke.delta_time.image.GameImage;
import com.jordanbunke.delta_time.sprite.SpriteSheet;
import com.jordanbunke.delta_time.utility.math.Coord2D;

import java.util.HashMap;
import java.util.Map;

public final class DimensionalLookupSpriteSheet implements SpriteConstituent<String> {
    private final Map<String, Integer> xPatternMap, yPatternMap;
    private final SpriteSheet spriteSheet;

    public DimensionalLookupSpriteSheet(
            final SpriteSheet spriteSheet,
            final Map<String, Integer> xPatternMap,
            final Map<String, Integer> yPatternMap
    ) {
        this.spriteSheet = spriteSheet;
        this.xPatternMap = new HashMap<>(xPatternMap);
        this.yPatternMap = new HashMap<>(yPatternMap);
    }

    @Override
    public GameImage getSprite(final String spriteID) {
        final int NOT_FOUND = -1;
        int x = NOT_FOUND, y = NOT_FOUND;

        for (String xPattern : xPatternMap.keySet()) {
            if (spriteID.contains(xPattern)) {
                x = xPatternMap.get(xPattern);
                break;
            }
        }

        for (String yPattern : yPatternMap.keySet()) {
            if (spriteID.contains(yPattern)) {
                y = yPatternMap.get(yPattern);
                break;
            }
        }

        if (x == NOT_FOUND || y == NOT_FOUND) {
            GameError.send(
                    "Failed to find matching patterns for BOTH the x and " +
                            "y dimensions for sprite ID \"" + spriteID + "\"."
            );
            return GameImage.dummy();
        }

        return spriteSheet.getSprite(new Coord2D(x, y));
    }

    public void definePatternForX(final String pattern, final int index) {
        xPatternMap.put(pattern, index);
    }

    public void definePatternForY(final String pattern, final int index) {
        yPatternMap.put(pattern, index);
    }
}
