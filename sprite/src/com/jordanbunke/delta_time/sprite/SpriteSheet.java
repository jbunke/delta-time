package com.jordanbunke.delta_time.sprite;

import com.jordanbunke.delta_time.error.GameError;
import com.jordanbunke.delta_time.image.GameImage;
import com.jordanbunke.delta_time.utility.math.Coord2D;

public class SpriteSheet {
    private final GameImage sheet;
    public final int singleSpriteWidth, singleSpriteHeight, spritesX, spritesY;
    private final GameImage[][] sprites;

    public SpriteSheet(
            final GameImage sheet, final int singleSpriteWidth,
            final int singleSpriteHeight
    ) {
        assert sheet.getWidth() % singleSpriteWidth == 0 &&
                sheet.getHeight() % singleSpriteHeight == 0;

        this.sheet = sheet;
        this.singleSpriteWidth = singleSpriteWidth;
        this.singleSpriteHeight = singleSpriteHeight;

        spritesX = sheet.getWidth() / singleSpriteWidth;
        spritesY = sheet.getHeight() / singleSpriteHeight;

        this.sprites = new GameImage[spritesX][spritesY];

        populateSprites();
    }

    private void populateSprites() {
        for (int x = 0; x < spritesX; x++) {
            for (int y = 0; y < spritesY; y++) {
                final GameImage sprite = new GameImage(singleSpriteWidth, singleSpriteHeight);

                sprite.draw(sheet, -x * singleSpriteWidth, -y * singleSpriteHeight);

                sprites[x][y] = sprite.submit();
            }
        }
    }

    public GameImage getSprite(final Coord2D coordinate) {
        if (coordinate.x >= 0 && coordinate.x < spritesX &&
                coordinate.y >= 0 && coordinate.y < spritesY)
            return sprites[coordinate.x][coordinate.y];

        GameError.send(
                "Attempted to get sprite at position: " + coordinate +
                        " in a sprite sheet with the dimensions " +
                        spritesX + "x" + spritesY
        );
        return new GameImage(singleSpriteWidth, singleSpriteHeight);
    }
}
