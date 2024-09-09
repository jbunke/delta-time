package com.jordanbunke.delta_time.scripting.ast.nodes.statement.control_flow;

import com.jordanbunke.delta_time.scripting.ast.nodes.expression.ExpressionNode;
import com.jordanbunke.delta_time.scripting.ast.nodes.statement.StatementNode;
import com.jordanbunke.delta_time.scripting.ast.symbol_table.SymbolTable;
import com.jordanbunke.delta_time.scripting.util.TextPosition;

public final class MatchesCaseNode extends CaseNode {
    final ExpressionNode condition;

    public MatchesCaseNode(
            final TextPosition position,
            final ExpressionNode condition, final StatementNode executionBody
    ) {
        super(position, executionBody);

        this.condition = condition;
    }

    @Override
    public void semanticErrorCheck(final SymbolTable symbolTable) {
        condition.semanticErrorCheck(symbolTable);
        super.semanticErrorCheck(symbolTable);
    }

    @Override
    boolean test(
            final ExpressionNode control, final SymbolTable symbolTable
    ) {
        symbolTable.evaluateScopeVar(control);

        return (boolean) condition.evaluate(symbolTable);
    }

    @Override
    public String toString() {
        return "matches " + condition + super.toString();
    }
}
