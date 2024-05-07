package com.jordanbunke.delta_time.scripting.ast.nodes.expression.literal;

import com.jordanbunke.delta_time.scripting.ast.nodes.expression.ExpressionNode;
import com.jordanbunke.delta_time.scripting.ast.symbol_table.SymbolTable;
import com.jordanbunke.delta_time.scripting.util.TextPosition;

public abstract class LiteralNode extends ExpressionNode {
    public LiteralNode(final TextPosition position) {
        super(position);
    }

    @Override
    public final void semanticErrorCheck(final SymbolTable symbolTable) {}
}
