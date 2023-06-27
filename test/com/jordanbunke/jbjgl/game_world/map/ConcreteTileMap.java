package com.jordanbunke.jbjgl.game_world.map;

import com.jordanbunke.jbjgl.game_world.physics.vector.Vector;

public class ConcreteTileMap<E extends Vector<E>> extends TileMap<BasicTile, E> {
    public ConcreteTileMap(
            final int width, final int height,
            final E topLeftVectorAnchor,
            final double tileWorldWidth,
            final double tileWorldLength
    ) {
        super(BasicTile.class, width, height, topLeftVectorAnchor,
                tileWorldWidth, tileWorldLength);
    }

    @Override
    public double calculateGCostMultiplier(int x, int y) {
        return 1d;
    }
}
