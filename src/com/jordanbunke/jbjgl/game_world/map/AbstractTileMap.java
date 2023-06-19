package com.jordanbunke.jbjgl.game_world.map;

import com.jordanbunke.jbjgl.game_world.Vector;

public abstract class AbstractTileMap<E extends Vector<E>> {
    // TODO
    private final int width, height;

    private final E topLeftVectorAnchor;
    private final double tileWorldWidth, tileWorldLength;
    private final AbstractTile[][] tiles;

    // TODO - temp
    public AbstractTileMap(
            final int width, final int height,
            final E topLeftVectorAnchor,
            final double tileWorldWidth,
            final double tileWorldLength
    ) {
        this.width = width;
        this.height = height;

        this.topLeftVectorAnchor = topLeftVectorAnchor;
        this.tileWorldWidth = tileWorldWidth;
        this.tileWorldLength = tileWorldLength;

        tiles = new AbstractTile[width][height];
    }

    public abstract double calculateGCostMultiplier(final int x, final int y);

    public final void populateTiles(final AbstractTile[][] tiles) {
        for (int x = 0; x < width; x++)
            if (height >= 0)
                System.arraycopy(tiles[x], 0, this.tiles[x], 0, height);
    }

    public final boolean tileAtIsValidPathComponent(final int x, final int y) {
        return tiles[x][y].isValidPathComponent();
    }

    public final E getWorldPosition(final int x, final int y) {
        return displaceBy(topLeftVectorAnchor,
                (tileWorldWidth / 2d) + x * tileWorldWidth,
                (tileWorldLength / 2d) + y * tileWorldLength
        );
    }

    private E displaceBy(final E reference, final double deltaX, final double deltaY) {
        return reference.displace(deltaX, deltaY);
    }

    public final int getWidth() {
        return width;
    }

    public final int getHeight() {
        return height;
    }

    public final AbstractTile getTileAt(final int x, final int y) {
        return tiles[x][y];
    }
}
