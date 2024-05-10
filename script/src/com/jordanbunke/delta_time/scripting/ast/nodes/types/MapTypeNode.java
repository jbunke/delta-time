package com.jordanbunke.delta_time.scripting.ast.nodes.types;

import com.jordanbunke.delta_time.scripting.ast.collection.ScriptMap;
import com.jordanbunke.delta_time.scripting.util.TextPosition;

public final class MapTypeNode extends TypeNode {
    private final TypeNode keyType;
    private final TypeNode valueType;

    public MapTypeNode(
            final TypeNode keyType, final TypeNode valueType
    ) {
        this(TextPosition.N_A, keyType, valueType);
    }

    public MapTypeNode(
            final TextPosition position,
            final TypeNode keyType, final TypeNode valueType
    ) {
        super(position);

        this.keyType = keyType;
        this.valueType = valueType;
    }

    public TypeNode getKeyType() {
        return keyType;
    }

    public TypeNode getValueType() {
        return valueType;
    }

    @Override
    public String toString() {
        return "{ " + keyType + " : " + valueType + " }";
    }

    @Override
    public boolean equals(final Object o) {
        if (o instanceof MapTypeNode that)
            return this.keyType.equals(that.keyType) &&
                this.valueType.equals(that.valueType);
        else
            return o instanceof BaseTypeNode that && that.isWildcard();
    }

    @Override
    public int hashCode() {
        return 0;
    }

    @Override
    public boolean hasSize() {
        return true;
    }

    @Override
    public boolean complies(final Object o) {
        return o instanceof ScriptMap;
    }
}
