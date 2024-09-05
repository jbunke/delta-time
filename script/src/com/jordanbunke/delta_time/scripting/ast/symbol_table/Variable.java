package com.jordanbunke.delta_time.scripting.ast.symbol_table;

import com.jordanbunke.delta_time.image.GameImage;
import com.jordanbunke.delta_time.scripting.ast.nodes.types.TypeNode;

import java.awt.*;

public final class Variable {
    private final boolean mutable;
    private final TypeNode type;
    private Object value;

    public Variable(
            final boolean mutable,
            final TypeNode type,
            final Object value
    ) {
        this.mutable = mutable;
        this.type = type;
        this.value = value;
    }

    public Variable(
            final boolean mutable,
            final TypeNode type
    ) {
        this(mutable, type, null);
    }

    public boolean isMutable() {
        return mutable;
    }

    public TypeNode getType() {
        return type;
    }

    public Object get() {
        return value;
    }

    public void set(final Object value) {
        if (!type.complies(value)) {
            // TODO - fire error
            return;
        }

        this.value = value;
    }

    @Override
    public String toString() {
        final String base = (mutable ? "" : "final ") + type;

        if (value == null)
            return base;

        return base + ":" + valueText(value);
    }

    public static String valueText(final Object value) {
        if (value instanceof GameImage img)
            return "Image of " + img.getWidth() + "x" + img.getHeight() + " px";
        else if (value instanceof Color c)
            return "[ R=" + c.getRed() + " G=" + c.getGreen() +
                    " B=" + c.getBlue() + (c.getAlpha() != 0xFF
                    ? " A=" + c.getAlpha() : "") + " ]";

        return value.toString();
    }
}
