package com.jordanbunke.delta_time.image.sprite;

import com.jordanbunke.delta_time.error.GameError;
import com.jordanbunke.delta_time.image.GameImage;
import com.jordanbunke.delta_time.io.GameImageIO;
import com.jordanbunke.delta_time.io.ResourceLoader;
import com.jordanbunke.delta_time.utility.Coord2D;

import java.nio.file.Path;

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

    public static SpriteSheet fromResource(
            final Path resourcePath, final int singleSpriteWidth,
            final int singleSpriteHeight
    ) {
        final GameImage sheet = ResourceLoader.loadImageResource(resourcePath);

        return new SpriteSheet(sheet, singleSpriteWidth,
                singleSpriteHeight);
    }

    public static SpriteSheet fromFile(
            final Path filePath, final int singleSpriteWidth,
            final int singleSpriteHeight
    ) {
        final GameImage sheet = GameImageIO.readImage(filePath);

        return new SpriteSheet(sheet, singleSpriteWidth,
                singleSpriteHeight);
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
