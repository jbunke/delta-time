package com.jordanbunke.delta_time.scripting.ast.nodes.expression.literal;

import com.jordanbunke.delta_time.scripting.ast.nodes.types.TypeNode;
import com.jordanbunke.delta_time.scripting.ast.symbol_table.SymbolTable;
import com.jordanbunke.delta_time.scripting.util.TextPosition;

public final class StringLiteralNode extends LiteralNode {
    private final String value;

    public StringLiteralNode(
            final TextPosition position, final String value
    ) {
        super(position);

        this.value = value;
    }

    @Override
    public String evaluate(final SymbolTable symbolTable) {
        return value;
    }

    @Override
    public TypeNode getType(final SymbolTable symbolTable) {
        return TypeNode.getString();
    }

    @Override
    public String toString() {
        return "\"" + value + "\"";
    }
}
