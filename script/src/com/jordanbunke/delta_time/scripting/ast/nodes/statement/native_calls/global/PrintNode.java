package com.jordanbunke.delta_time.scripting.ast.nodes.statement.native_calls.global;

import com.jordanbunke.delta_time.scripting.Interpreter;
import com.jordanbunke.delta_time.scripting.ast.nodes.expression.ExpressionNode;
import com.jordanbunke.delta_time.scripting.ast.nodes.statement.StatementNode;
import com.jordanbunke.delta_time.scripting.ast.symbol_table.SymbolTable;
import com.jordanbunke.delta_time.scripting.util.FuncControlFlow;
import com.jordanbunke.delta_time.scripting.util.TextPosition;

public final class PrintNode extends StatementNode {
    private final ExpressionNode message;

    public PrintNode(
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
    public FuncControlFlow execute(final SymbolTable symbolTable) {
        final String m = String.valueOf(message.evaluate(symbolTable));
        Interpreter.println(m);

        return FuncControlFlow.cont();
    }

    @Override
    public String toString() {
        return "print(" + message + ");";
    }
}
