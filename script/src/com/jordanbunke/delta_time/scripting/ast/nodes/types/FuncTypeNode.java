package com.jordanbunke.delta_time.scripting.ast.nodes.types;

import com.jordanbunke.delta_time.scripting.ast.nodes.function.ChildFuncNode;
import com.jordanbunke.delta_time.scripting.util.TextPosition;

import java.util.Arrays;

public final class FuncTypeNode extends TypeNode {
    private final TypeNode[] paramTypes;
    private final TypeNode returnType;

    public FuncTypeNode(
            final TypeNode[] paramTypes,
            final TypeNode returnType
    ) {
        this(TextPosition.N_A, paramTypes, returnType);
    }

    public FuncTypeNode(
            final TextPosition position,
            final TypeNode[] paramTypes,
            final TypeNode returnType
    ) {
        super(position);

        this.paramTypes = paramTypes;
        this.returnType = returnType;
    }

    public TypeNode[] getParamTypes() {
        return paramTypes;
    }

    public TypeNode getReturnType() {
        return returnType;
    }

    @Override
    public boolean hasSize() {
        return false;
    }

    @Override
    public boolean complies(final Object o) {
        return o instanceof ChildFuncNode;
    }

    @Override
    public boolean equals(final Object o) {
        if (o instanceof FuncTypeNode f) {
            if (paramTypes.length != f.paramTypes.length)
                return false;

            for (int i = 0; i < paramTypes.length; i++)
                if (!paramTypes[i].equals(f.paramTypes[i]))
                    return false;

            return returnType.equals(f.returnType);
        } else
            return o instanceof BaseTypeNode that && that.isWildcard();
    }

    @Override
    public int hashCode() {
        return paramTypes.length;
    }

    @Override
    public String toString() {
        final String params = switch (paramTypes.length) {
            case 0 -> "";
            case 1 -> paramTypes[0].toString();
            default -> Arrays.stream(paramTypes).map(TypeNode::toString)
                    .reduce((a, b) -> a + ", " + b).orElse("");
        };

        return "(" + params + " -> " + returnType + ")";
    }
}
