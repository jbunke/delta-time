package com.jordanbunke.delta_time.scripting.ast.nodes.statement.control_flow;

import com.jordanbunke.delta_time.scripting.ast.nodes.expression.ExpressionNode;
import com.jordanbunke.delta_time.scripting.ast.nodes.statement.StatementNode;
import com.jordanbunke.delta_time.scripting.ast.symbol_table.SymbolTable;
import com.jordanbunke.delta_time.scripting.util.TextPosition;

public final class IsCaseNode extends CaseNode {
    final ExpressionNode matcher;

    public IsCaseNode(
            final TextPosition position,
            final ExpressionNode matcher, final StatementNode executionBody
    ) {
        super(position, executionBody);

        this.matcher = matcher;
    }

    @Override
    public void semanticErrorCheck(final SymbolTable symbolTable) {
        matcher.semanticErrorCheck(symbolTable);
        super.semanticErrorCheck(symbolTable);
    }

    @Override
    boolean test(final SymbolTable symbolTable) {
        final Object cv = symbolTable.getScopeVar().get(),
                mv = matcher.evaluate(symbolTable);

        return cv.equals(mv);
    }

    @Override
    public String toString() {
        return "is " + matcher + super.toString();
    }
}
