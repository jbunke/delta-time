package com.jordanbunke.delta_time.scripting.ast.nodes.expression;

import com.jordanbunke.delta_time.scripting.ast.nodes.ASTNode;
import com.jordanbunke.delta_time.scripting.ast.nodes.types.TypeNode;
import com.jordanbunke.delta_time.scripting.ast.symbol_table.SymbolTable;
import com.jordanbunke.delta_time.scripting.util.TextPosition;

public abstract class ExpressionNode extends ASTNode {
    public ExpressionNode(final TextPosition position) {
        super(position);
    }

    public abstract Object evaluate(final SymbolTable symbolTable);

    public abstract TypeNode getType(final SymbolTable symbolTable);
}
