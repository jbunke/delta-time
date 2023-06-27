package com.jordanbunke.jbjgl.game_world.map;

import com.jordanbunke.jbjgl.game_world.physics.vector.Vector;
import com.jordanbunke.jbjgl.utility.Coord2D;

import java.lang.reflect.Array;

public abstract class TileMap<T extends Tile, E extends Vector<E>> {
    private final int width, height;

    private final E topLeftVectorAnchor;
    private final double tileWorldWidth, tileWorldLength;
    private final T[][] tiles;

    public TileMap(
            final Class<T> tileClass,
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

        tiles = (T[][]) Array.newInstance(tileClass, width, height);
    }

    public abstract double calculateGCostMultiplier(final int x, final int y);

    public final void populateTiles(final T[][] tiles) {
        for (int x = 0; x < width; x++)
            if (height >= 0)
                System.arraycopy(tiles[x], 0, this.tiles[x], 0, height);
    }

    public final boolean tileAtIsValidPathComponent(final int x, final int y) {
        if (!areCoordinatesValid(x, y))
            return false;

        final T tile = getTileAt(x, y);

        if (tile == null)
            return false;

        return tile.isValidPathComponent();
    }

    public final E getWorldPosition(
            final int x, final int y
    ) {
        return getWorldPosition(x, y, 0, 1);
    }

    public final E getWorldPosition(
            final int x, final int y,
            final int xEquivalentAxisIndex,
            final int yEquivalentAxisIndex
    ) {
        final double[] displacements = new double
                [Math.max(xEquivalentAxisIndex, yEquivalentAxisIndex) + 1];
        displacements[xEquivalentAxisIndex] = (tileWorldWidth / 2d) + x * tileWorldWidth;
        displacements[yEquivalentAxisIndex] = (tileWorldLength / 2d) + y * tileWorldLength;

        return topLeftVectorAnchor.displace(displacements);
    }

    public final Coord2D getCoordinates(
            final E position, final int xEquivalentAxisIndex,
            final int yEquivalentAxisIndex
    ) {
        final E offset = position.displace(topLeftVectorAnchor.scale(-1d));

        final int x = (int)(offset.getAxisValue(xEquivalentAxisIndex) / tileWorldWidth);
        final int y = (int)(offset.getAxisValue(yEquivalentAxisIndex) / tileWorldLength);

        return new Coord2D(x, y);
    }

    public final boolean areCoordinatesValid(final Coord2D coordinates) {
        return areCoordinatesValid(coordinates.x, coordinates.y);
    }

    public final boolean areCoordinatesValid(final int x, final int y) {
        return x >= 0 && y >= 0 && x < width && y < height;
    }

    public final int getWidth() {
        return width;
    }

    public final int getHeight() {
        return height;
    }

    public final T getTileAt(final int x, final int y) {
        if (!areCoordinatesValid(x, y))
            return null;

        return tiles[x][y];
    }
}
