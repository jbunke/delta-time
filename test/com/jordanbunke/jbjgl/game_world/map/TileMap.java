package com.jordanbunke.jbjgl.game_world.map;

import com.jordanbunke.jbjgl.game_world.Vector;

public class TileMap<E extends Vector> extends JBJGLTileMap<E> {
    public TileMap(
            final int width, final int height,
            final E topLeftVectorAnchor,
            final double tileWorldWidth,
            final double tileWorldLength
    ) {
        super(width, height, topLeftVectorAnchor, tileWorldWidth, tileWorldLength);
    }

    @Override
    public double calculateGCostMultiplier(int x, int y) {
        return 1d;
    }
}
