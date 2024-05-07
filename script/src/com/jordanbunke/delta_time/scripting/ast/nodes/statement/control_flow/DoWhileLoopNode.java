package com.jordanbunke.delta_time.scripting.ast.nodes.statement.control_flow;

import com.jordanbunke.delta_time.scripting.ast.nodes.expression.ExpressionNode;
import com.jordanbunke.delta_time.scripting.ast.nodes.statement.StatementNode;
import com.jordanbunke.delta_time.scripting.ast.symbol_table.SymbolTable;
import com.jordanbunke.delta_time.scripting.util.FuncControlFlow;
import com.jordanbunke.delta_time.scripting.util.TextPosition;

public final class DoWhileLoopNode extends WhileLoopNode {
    public DoWhileLoopNode(
            final TextPosition position,
            final ExpressionNode loopCondition,
            final StatementNode loopBody
    ) {
        super(position, loopCondition, loopBody);
    }

    @Override
    public FuncControlFlow execute(final SymbolTable symbolTable) {
        FuncControlFlow status = getLoopBody().execute(symbolTable);

        if (!status.cont)
            return status;

        while (evaluateCondition(symbolTable)) {
            status = getLoopBody().execute(symbolTable);

            if (!status.cont)
                return status;
        }

        return status;
    }

    @Override
    public String toString() {
        return "do\n" + getLoopBody() + "\nwhile (" +
                getLoopCondition() + ");";
    }
}
