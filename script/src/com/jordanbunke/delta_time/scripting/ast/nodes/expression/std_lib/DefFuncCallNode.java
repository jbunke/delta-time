package com.jordanbunke.delta_time.scripting.ast.nodes.expression.std_lib;

import com.jordanbunke.delta_time.scripting.ast.nodes.expression.ExpressionNode;
import com.jordanbunke.delta_time.scripting.ast.nodes.types.TypeNode;
import com.jordanbunke.delta_time.scripting.ast.symbol_table.SymbolTable;
import com.jordanbunke.delta_time.scripting.util.Arguments;
import com.jordanbunke.delta_time.scripting.util.TextPosition;

public abstract class DefFuncCallNode extends ExpressionNode {
    protected final Arguments arguments;
    private final TypeNode returnType;

    public DefFuncCallNode(
            final Arguments arguments, final TypeNode returnType,
            final TextPosition position
    ) {
        super(position);

        this.arguments = arguments;
        this.returnType = returnType;
    }

    @Override
    public void semanticErrorCheck(final SymbolTable symbolTable) {
        arguments.semanticErrorCheck(symbolTable, getPosition());
    }

    @Override
    public TypeNode getType(final SymbolTable symbolTable) {
        return returnType;
    }

    protected abstract String funcName();

    @Override
    public String toString() {
        return funcName() + arguments;
    }
}
