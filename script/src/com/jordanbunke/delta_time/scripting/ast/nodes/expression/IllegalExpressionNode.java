package com.jordanbunke.delta_time.scripting.ast.nodes.expression;

import com.jordanbunke.delta_time.scripting.ast.nodes.IllegalLanguageFeature;
import com.jordanbunke.delta_time.scripting.ast.nodes.types.TypeNode;
import com.jordanbunke.delta_time.scripting.ast.symbol_table.SymbolTable;
import com.jordanbunke.delta_time.scripting.util.TextPosition;

public final class IllegalExpressionNode extends ExpressionNode
        implements IllegalLanguageFeature {
    private final String description;

    public IllegalExpressionNode(
            final TextPosition position, final String description
    ) {
        super(position);

        this.description = description;

        fireError();
    }

    @Override
    public void semanticErrorCheck(final SymbolTable symbolTable) {
        fireError();
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public Object evaluate(final SymbolTable symbolTable) {
        return null;
    }

    @Override
    public TypeNode getType(final SymbolTable symbolTable) {
        return null;
    }

    @Override
    public String toString() {
        return getError();
    }
}
