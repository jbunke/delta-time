package com.jordanbunke.delta_time.scripting.ast.symbol_table;

import com.jordanbunke.delta_time.scripting.ast.nodes.types.TypeNode;

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
        this.value = value;
    }

    @Override
    public String toString() {
        final String base = (mutable ? "" : "final ") + type;

        if (value == null)
            return base;

        return base + ":" + value;
    }
}
