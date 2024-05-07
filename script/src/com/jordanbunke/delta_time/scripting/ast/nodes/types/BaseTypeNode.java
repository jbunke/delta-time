package com.jordanbunke.delta_time.scripting.ast.nodes.types;

import com.jordanbunke.delta_time.image.GameImage;
import com.jordanbunke.delta_time.scripting.util.TextPosition;

import java.awt.*;

public final class BaseTypeNode extends TypeNode {
    private final Type type;

    public enum Type {
        BOOL, INT, FLOAT, CHAR, STRING, COLOR, IMAGE, WILDCARD;

        @Override
        public String toString() {
            if (this == WILDCARD)
                return "_";

            return name().toLowerCase();
        }

        private boolean isNum() {
            return switch (this) {
                case INT, FLOAT -> true;
                default -> false;
            };
        }
    }

    BaseTypeNode(final Type type) {
        this(TextPosition.N_A, type);
    }

    public BaseTypeNode(
            final TextPosition position, final Type type
    ) {
        super(position);
        this.type = type;
    }

    public Type getType() {
        return type;
    }

    @Override
    public String toString() {
        return type.toString();
    }

    @Override
    public boolean equals(final Object o) {
        return o instanceof BaseTypeNode that && (this.type == that.type ||
                this.type == Type.WILDCARD || that.type == Type.WILDCARD);
    }

    @Override
    public int hashCode() {
        return type.ordinal();
    }

    @Override
    public boolean isNum() {
        return type.isNum();
    }

    @Override
    public boolean hasSize() {
        return type == Type.STRING;
    }

    public boolean isWildcard() {
        return type == Type.WILDCARD;
    }

    @Override
    public boolean complies(final Object o) {
        return switch (type) {
            case INT -> o instanceof Integer;
            case BOOL -> o instanceof Boolean;
            case STRING -> o instanceof String;
            case COLOR -> o instanceof Color;
            case FLOAT -> o instanceof Double;
            case CHAR -> o instanceof Character;
            case IMAGE -> o instanceof GameImage;
            case WILDCARD -> true;
        };
    }
}
