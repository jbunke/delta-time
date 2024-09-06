package com.jordanbunke.delta_time.scripting.ast.nodes.expression.native_calls.global.io;

import com.jordanbunke.delta_time.scripting.Interpreter;
import com.jordanbunke.delta_time.scripting.ast.nodes.expression.ExpressionNode;
import com.jordanbunke.delta_time.scripting.ast.nodes.types.TypeNode;
import com.jordanbunke.delta_time.scripting.ast.symbol_table.SymbolTable;
import com.jordanbunke.delta_time.scripting.util.TextPosition;

public final class PromptNode extends ExpressionNode {
    private final ExpressionNode message;

    public PromptNode(
            final TextPosition position, final ExpressionNode message
    ) {
        super(position);

        this.message = message;
    }

    @Override
    public void semanticErrorCheck(final SymbolTable symbolTable) {
        message.semanticErrorCheck(symbolTable);
    }

    @Override
    public String evaluate(final SymbolTable symbolTable) {
        final String m = String.valueOf(message.evaluate(symbolTable));

        return Interpreter.prompt(m);
    }

    @Override
    public TypeNode getType(final SymbolTable symbolTable) {
        return TypeNode.getString();
    }

    @Override
    public String toString() {
        return "prompt(" + message + ")";
    }
}
