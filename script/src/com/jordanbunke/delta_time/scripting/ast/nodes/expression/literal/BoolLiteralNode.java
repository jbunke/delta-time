package com.jordanbunke.delta_time.scripting.ast.nodes.expression.literal;

import com.jordanbunke.delta_time.scripting.ast.nodes.types.TypeNode;
import com.jordanbunke.delta_time.scripting.ast.symbol_table.SymbolTable;
import com.jordanbunke.delta_time.scripting.util.TextPosition;

public final class BoolLiteralNode extends LiteralNode {
    private final boolean value;

    public BoolLiteralNode(
            final TextPosition position,
            final boolean value
    ) {
        super(position);

        this.value = value;
    }

    @Override
    public Boolean evaluate(final SymbolTable symbolTable) {
        return value;
    }

    @Override
    public TypeNode getType(final SymbolTable symbolTable) {
        return TypeNode.getBool();
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
