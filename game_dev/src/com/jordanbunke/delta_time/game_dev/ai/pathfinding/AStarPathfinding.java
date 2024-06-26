package com.jordanbunke.delta_time.game_dev.ai.pathfinding;

import com.jordanbunke.delta_time.game_dev.map.Tile;
import com.jordanbunke.delta_time.game_dev.map.TileMap;
import com.jordanbunke.delta_time.game_dev.physics.vector.Vector;
import com.jordanbunke.delta_time.utility.math.Coord2D;

import java.util.*;
import java.util.function.BiFunction;

public class AStarPathfinding {
    public static <T extends Tile, E extends Vector<E>> List<Coord2D> findPath(
            final Coord2D start, final Coord2D goal,
            final TileMap<T, E> environment, final boolean canMoveDiagonally
    ) {
        return findDynamicPath(
                start, goal, environment, canMoveDiagonally,
                environment::calculateGCostMultiplier);
    }

    public static <T extends Tile, E extends Vector<E>> List<Coord2D> findDynamicPath(
            final Coord2D start, final Coord2D goal,
            final TileMap<T, E> environment, final boolean canMoveDiagonally,
            final BiFunction<Integer, Integer, Double> dynamicGCostFunction
    ) {
        // 1: initialize open and closed collections
        final PriorityQueue<PathfindingNode> open = new PriorityQueue<>();
        final Set<PathfindingNode> closed = new HashSet<>();

        // 2: initialize node map
        final PathfindingNode[][] nodeMap = new PathfindingNode[environment.getWidth()][environment.getHeight()];

        for (int x = 0; x < nodeMap.length; x++) {
            for (int y = 0; y < nodeMap[x].length; y++) {
                final Coord2D position = new Coord2D(x, y);
                final double fCost = Coord2D.unitDistanceBetween(position, goal);
                final PathfindingNode node = new PathfindingNode(position, fCost);

                if (!environment.tileAtIsValidPathComponent(x, y))
                    closed.add(node);

                nodeMap[x][y] = node;
            }
        }

        // 3: initialize start and goal NODES
        final PathfindingNode startNode = nodeMap[start.x][start.y];
        final PathfindingNode goalNode = nodeMap[goal.x][goal.y];

        open.add(startNode);

        // 4: search
        while (!open.isEmpty()) {
            PathfindingNode checking = open.poll();
            closed.add(checking);

            if (checking.equals(goalNode))
                return extractPath(checking);

            // 4a: check neighbours
            for (int x = checking.coordinate.x - 1; x < checking.coordinate.x + 2; x++) {
                for (int y = checking.coordinate.y - 1; y < checking.coordinate.y + 2; y++) {
                    if (x < 0 || x >= environment.getWidth() || y < 0 || y >= environment.getHeight())
                        continue;

                    if (!canMoveDiagonally && x != checking.coordinate.x && y != checking.coordinate.y)
                        continue;

                    final PathfindingNode neighbour = nodeMap[x][y];

                    if (closed.contains(neighbour))
                        continue;

                    final double gCost = Coord2D.unitDistanceBetween(checking.coordinate, neighbour.coordinate) *
                            dynamicGCostFunction.apply(neighbour.coordinate.x, neighbour.coordinate.y);
                    final double updatedNeighbourTotal = gCost + neighbour.getHCost() + checking.getTotalCost();

                    if (updatedNeighbourTotal < neighbour.getTotalCost() || !open.contains(neighbour)) {
                        neighbour.setCost(updatedNeighbourTotal - neighbour.getHCost());
                        neighbour.setParent(checking);

                        if (!open.contains(neighbour))
                            open.add(neighbour);
                    }
                }
            }
        }

        return new ArrayList<>(List.of(start));
    }

    private static List<Coord2D> extractPath(final PathfindingNode destination) {
        List<Coord2D> path = new ArrayList<>();

        PathfindingNode step = destination;

        while (step.getParent() != null) {
            path.add(0, step.coordinate);
            step = step.getParent();
        }

        return path;
    }
}
