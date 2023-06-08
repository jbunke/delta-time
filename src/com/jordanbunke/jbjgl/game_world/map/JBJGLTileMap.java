package com.jordanbunke.jbjgl.game_world.map;

import com.jordanbunke.jbjgl.game_world.Vector;
import com.jordanbunke.jbjgl.game_world.Vector2D;
import com.jordanbunke.jbjgl.game_world.Vector3D;

public abstract class JBJGLTileMap<E extends Vector> {
    // TODO
    private final int width, height;

    private final E topLeftVectorAnchor;
    private final double tileWorldWidth, tileWorldLength;
    private final JBJGLTile[][] tiles;

    // TODO - temp
    public JBJGLTileMap(
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

        tiles = new JBJGLTile[width][height];
    }

    public abstract double calculateGCostMultiplier(final int x, final int y);

    public final void populateTiles(final JBJGLTile[][] tiles) {
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
        if (reference instanceof Vector2D _2d)
            return (E) _2d.displace(deltaX, deltaY);
        else if (reference instanceof Vector3D _3d)
            return (E) _3d.displace(deltaX, deltaY, 0d);

        return reference;
    }

    public final int getWidth() {
        return width;
    }

    public final int getHeight() {
        return height;
    }

    public final JBJGLTile getTileAt(final int x, final int y) {
        return tiles[x][y];
    }
}
