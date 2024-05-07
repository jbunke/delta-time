package com.jordanbunke.delta_time.scripting.ast.nodes.expression.literal;

import com.jordanbunke.delta_time.scripting.ast.nodes.types.TypeNode;
import com.jordanbunke.delta_time.scripting.ast.symbol_table.SymbolTable;
import com.jordanbunke.delta_time.scripting.util.TextPosition;

public final class FloatLiteralNode extends LiteralNode {
    private final double value;

    public FloatLiteralNode(
            final TextPosition position,
            final double value
    ) {
        super(position);

        this.value = value;
    }

    @Override
    public Double evaluate(final SymbolTable symbolTable) {
        return value;
    }

    @Override
    public TypeNode getType(final SymbolTable symbolTable) {
        return TypeNode.getFloat();
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
