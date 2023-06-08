package com.jordanbunke.jbjgl.game_world.map;

import com.jordanbunke.jbjgl.Example;
import com.jordanbunke.jbjgl.game_world.Coord2D;
import com.jordanbunke.jbjgl.game_world.Vector2D;
import com.jordanbunke.jbjgl.game_world.map.pathfinding.AStarPathfinding;
import com.jordanbunke.jbjgl.image.JBJGLImage;
import com.jordanbunke.jbjgl.io.JBJGLFileIO;
import com.jordanbunke.jbjgl.io.JBJGLImageIO;
import com.jordanbunke.jbjgl.io.JBJGLResourceLoader;

import java.awt.*;
import java.nio.file.Path;
import java.util.List;

public class TileMapTests {
    private static Coord2D start, goal;

    public static void main(String[] args) {
        start = new Coord2D(0, 0);
        goal = new Coord2D(0, 0);

        final String[] names = new String[] {
                "tilemap_1"
        };

        for (String name : names)
            perform(name);
    }

    private static void perform(final String name) {
        TileMap<Vector2D> tileMap = load(name + ".txt");

        final long startTime = System.currentTimeMillis();
        List<Coord2D> path = AStarPathfinding.findPath(start, goal, tileMap);
        final long elapsed = System.currentTimeMillis() - startTime;

        System.out.println("Elapsed: " + elapsed + " ms");

        drawAndSave(tileMap, path, name);
    }

    private static TileMap<Vector2D> load(final String filename) {
        String fileContents = JBJGLFileIO.readResource(
                JBJGLResourceLoader.loadResource(Example.class, Path.of("tilemaps", filename)), ""
        );
        String[] lines = fileContents.split("\n");

        final int width, height;

        final String firstLine = lines[0];
        width = Integer.parseInt(firstLine.substring(0, firstLine.indexOf(',')));
        height = Integer.parseInt(firstLine.substring(firstLine.indexOf(',') + 1));

        final TileMap<Vector2D> tileMap = new TileMap<>(width, height, new Vector2D(), 3d, 3d);

        final BasicTile[][] tiles = new BasicTile[width][height];

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                final char c = lines[y + 1].charAt(x);
                tiles[x][y] = BasicTile.fromRead(c);

                if (c == 's')
                    start = new Coord2D(x, y);
                else if (c == 'g')
                    goal = new Coord2D(x, y);
            }
        }

        tileMap.populateTiles(tiles);
        return tileMap;
    }

    private static void drawAndSave(final TileMap<Vector2D> tileMap, final List<Coord2D> path, final String name) {
        final int UNIT_DIM = 20;

        final JBJGLImage image = JBJGLImage.create(
                tileMap.getWidth() * UNIT_DIM, tileMap.getHeight() * UNIT_DIM
        );
        final Graphics g = image.getGraphics();

        for (int x = 0; x < tileMap.getWidth(); x++) {
            for (int y = 0; y < tileMap.getHeight(); y++) {
                g.setColor(((BasicTile)tileMap.getTileAt(x, y)).getColor());
                g.fillRect(x * UNIT_DIM, y * UNIT_DIM, UNIT_DIM, UNIT_DIM);
            }
        }

        g.setColor(new Color(150, 150, 255, 150));
        for (Coord2D step : path)
            g.fillRect(step.x * UNIT_DIM, step.y * UNIT_DIM, UNIT_DIM, UNIT_DIM);

        g.dispose();
        JBJGLImageIO.writeImage(Path.of("test_out", "tilemaps", name  + " result.png"), image);
    }
}
