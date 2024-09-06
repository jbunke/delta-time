package com.jordanbunke.delta_time.scripting.ast.nodes.statement.control_flow;

import com.jordanbunke.delta_time.scripting.ast.nodes.expression.ExpressionNode;
import com.jordanbunke.delta_time.scripting.ast.nodes.statement.StatementNode;
import com.jordanbunke.delta_time.scripting.ast.symbol_table.SymbolTable;
import com.jordanbunke.delta_time.scripting.util.FuncControlFlow;
import com.jordanbunke.delta_time.scripting.util.TextPosition;

public abstract class CaseNode extends StatementNode {
    private final StatementNode executionBody;

    CaseNode(
            final TextPosition position, final StatementNode executionBody
    ) {
        super(position);

        this.executionBody = executionBody;
    }

    abstract boolean test(final ExpressionNode control, final SymbolTable symbolTable);

    @Override
    public void semanticErrorCheck(final SymbolTable symbolTable) {
        executionBody.semanticErrorCheck(symbolTable);
    }

    @Override
    public FuncControlFlow execute(final SymbolTable symbolTable) {
        return executionBody.execute(symbolTable);
    }

    @Override
    public String toString() {
        return " -> " + executionBody;
    }
}
