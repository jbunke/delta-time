package com.jordanbunke.jbjgl.game_world.map;

import com.jordanbunke.jbjgl.game_world.Vector;
import com.jordanbunke.jbjgl.game_world.Vector2D;
import com.jordanbunke.jbjgl.game_world.Vector3D;

public class TileMap<E extends Vector> {
    // TODO
    private final int width, height;

    private final E topLeftVectorAnchor;
    private final double tileWorldWidth, tileWorldLength;
    private final JBJGLTile[][] tiles;

    // TODO - temp
    public TileMap(
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

    public void populateTiles(final JBJGLTile[][] tiles) {
        for (int x = 0; x < width; x++)
            if (height >= 0)
                System.arraycopy(tiles[x], 0, this.tiles[x], 0, height);
    }

    public boolean tileAtIsValidPathComponent(final int x, final int y) {
        return tiles[x][y].isValidPathComponent();
    }

    public E getWorldPosition(final int x, final int y) {
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

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public JBJGLTile getTileAt(final int x, final int y) {
        return tiles[x][y];
    }
}
