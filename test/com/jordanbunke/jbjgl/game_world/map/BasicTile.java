package com.jordanbunke.jbjgl.game_world.map;

import java.awt.*;

public class BasicTile extends JBJGLTile {
    private final Type type;

    public enum Type {
        WALL, FLOOR, START, GOAL, INVALID
    }

    public BasicTile(final Type type) {
        this.type = type;
    }

    public static BasicTile fromRead(final Color c) {
        if (c.equals(new Color(0, 255, 0, 255)))
            return new BasicTile(Type.START);
        else if (c.equals(new Color(255, 0, 0, 255)))
            return new BasicTile(Type.GOAL);
        else if (c.equals(new Color(255, 255, 255, 255)))
            return new BasicTile(Type.FLOOR);
        else if (c.equals(new Color(0, 0, 0, 255)))
            return new BasicTile(Type.WALL);

        return new BasicTile(Type.INVALID);
    }

    public Color getColor() {
        return switch (type) {
            case WALL -> new Color(0, 0, 0, 255);
            case FLOOR -> new Color(255, 255, 255, 255);
            case START -> new Color(0, 255, 0, 255);
            case GOAL -> new Color(255, 0, 0, 255);
            default -> new Color(100, 100, 100, 255);
        };
    }

    @Override
    public boolean isValidPathComponent() {
        return (type != Type.WALL && type != Type.INVALID);
    }
}
