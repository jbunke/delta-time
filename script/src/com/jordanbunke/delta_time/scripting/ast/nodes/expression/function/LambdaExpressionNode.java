package com.jordanbunke.delta_time.scripting.ast.nodes.expression.function;

import com.jordanbunke.delta_time.scripting.ast.nodes.expression.ExpressionNode;
import com.jordanbunke.delta_time.scripting.ast.nodes.function.LambdaFuncNode;
import com.jordanbunke.delta_time.scripting.ast.nodes.types.TypeNode;
import com.jordanbunke.delta_time.scripting.ast.symbol_table.SymbolTable;

public final class LambdaExpressionNode extends ExpressionNode {
    public final LambdaFuncNode f;

    public LambdaExpressionNode(final LambdaFuncNode f) {
        super(f.getPosition());

        this.f = f;
    }

    @Override
    public void semanticErrorCheck(final SymbolTable symbolTable) {
        f.semanticErrorCheck(symbolTable);
    }

    @Override
    public LambdaFuncNode evaluate(final SymbolTable symbolTable) {
        return f;
    }

    @Override
    public TypeNode getType(final SymbolTable symbolTable) {
        return f.getType();
    }

    @Override
    public String toString() {
        return f.toString();
    }
}
