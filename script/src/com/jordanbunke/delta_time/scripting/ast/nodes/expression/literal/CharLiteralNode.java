package com.jordanbunke.delta_time.scripting.ast.nodes.expression.literal;

import com.jordanbunke.delta_time.scripting.ast.nodes.types.TypeNode;
import com.jordanbunke.delta_time.scripting.ast.symbol_table.SymbolTable;
import com.jordanbunke.delta_time.scripting.util.TextPosition;

public final class CharLiteralNode extends LiteralNode {
    private final char value;

    public CharLiteralNode(
            final TextPosition position,
            final char value
    ) {
        super(position);

        this.value = value;
    }

    @Override
    public Character evaluate(final SymbolTable symbolTable) {
        return value;
    }

    @Override
    public TypeNode getType(final SymbolTable symbolTable) {
        return TypeNode.getChar();
    }

    @Override
    public String toString() {
        return "'" + value + "'";
    }
}
