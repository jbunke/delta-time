package com.jordanbunke.jbjgl.game_world.map.pathfinding;

import com.jordanbunke.jbjgl.game_world.Coord2D;

public class PathfindingNode implements Comparable<PathfindingNode> {
    public final Coord2D coordinate;
    private double gCost, totalCost;
    private final double hCost;

    private PathfindingNode parent;

    public PathfindingNode(
            final Coord2D coordinate, final double fCost
    ) {
        this.coordinate = coordinate;

        this.gCost = 1d;
        this.hCost = fCost;

        this.parent = null;
    }

    public void setCost(final double gCost) {
        this.gCost = gCost;

        totalCost = this.gCost + hCost;
    }

    public void setParent(final PathfindingNode parent) {
        this.parent = parent;
    }

    public PathfindingNode getParent() {
        return parent;
    }

    public double getGCost() {
        return gCost;
    }

    public double getHCost() {
        return hCost;
    }

    public double getTotalCost() {
        return totalCost;
    }

    @Override
    public int compareTo(final PathfindingNode that) {
        return Double.compare(this.totalCost, that.totalCost);
    }
}
