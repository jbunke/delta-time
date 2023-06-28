package com.jordanbunke.jbjgl.game_world.map;

import com.jordanbunke.jbjgl.utility.Coord2D;
import com.jordanbunke.jbjgl.game_world.physics.vector.Vector2D;
import com.jordanbunke.jbjgl.ai.pathfinding.AStarPathfinding;
import com.jordanbunke.jbjgl.image.ImageProcessing;
import com.jordanbunke.jbjgl.image.GameImage;
import com.jordanbunke.jbjgl.io.GameImageIO;
import com.jordanbunke.jbjgl.io.ResourceLoader;

import java.awt.*;
import java.nio.file.Path;
import java.util.List;

public class TileMapTests {
    private static Coord2D start, goal;

    public static void main(String[] args) {
        start = new Coord2D(0, 0);
        goal = new Coord2D(0, 0);

        final String[] names = new String[] {
                "tilemap_1", "tilemap_2", "tilemap_3", "tilemap_4"
        };

        for (String name : names)
            perform(name);
    }

    private static void perform(final String name) {
        ConcreteTileMap<Vector2D> concreteTileMap = load(name + ".png");

        final long startTime = System.currentTimeMillis();
        List<Coord2D> path = AStarPathfinding.findPath(start, goal, concreteTileMap, false);
        final long elapsed = System.currentTimeMillis() - startTime;

        System.out.printf("Elapsed (%s) : " + elapsed + " ms\n", name);

        drawAndSave(concreteTileMap, path, name);
    }

    private static ConcreteTileMap<Vector2D> load(final String filename) {
        final GameImage tileSource = ResourceLoader.loadImageResource(Path.of("tilemaps", filename));

        final int width = tileSource.getWidth(), height = tileSource.getHeight();

        final ConcreteTileMap<Vector2D> concreteTileMap =
                new ConcreteTileMap<>(width, height,
                        new Vector2D(), 3d, 3d);

        final BasicTile[][] tiles = new BasicTile[width][height];

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                final Color c = ImageProcessing.colorAtPixel(tileSource, x, y);
                tiles[x][y] = BasicTile.fromRead(c);

                if (c.equals(new Color(255, 0, 0, 255)))
                    start = new Coord2D(x, y);
                else if (c.equals(new Color(0, 255, 0, 255)))
                    goal = new Coord2D(x, y);
            }
        }

        concreteTileMap.populateTiles(tiles);
        return concreteTileMap;
    }

    private static void drawAndSave(final ConcreteTileMap<Vector2D> concreteTileMap, final List<Coord2D> path, final String name) {
        final int UNIT_DIM = 20;

        final GameImage image = new GameImage(concreteTileMap.getWidth() * UNIT_DIM,
                concreteTileMap.getHeight() * UNIT_DIM);

        for (int x = 0; x < concreteTileMap.getWidth(); x++)
            for (int y = 0; y < concreteTileMap.getHeight(); y++)
                image.fillRectangle(concreteTileMap.getTileAt(x, y).getColor(),
                        x * UNIT_DIM, y * UNIT_DIM, UNIT_DIM, UNIT_DIM);

        image.setColor(new Color(150, 150, 255, 150));
        for (Coord2D step : path)
            image.fillRectangle(step.x * UNIT_DIM, step.y * UNIT_DIM, UNIT_DIM, UNIT_DIM);

        GameImageIO.writeImage(Path.of("test_out", "tilemaps", name  + " result.png"), image.submit());
    }
}
