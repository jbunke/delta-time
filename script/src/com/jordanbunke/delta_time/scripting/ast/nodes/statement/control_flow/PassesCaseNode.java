package com.jordanbunke.delta_time.scripting.ast.nodes.statement.control_flow;

import com.jordanbunke.delta_time.scripting.ast.nodes.expression.ExpressionNode;
import com.jordanbunke.delta_time.scripting.ast.nodes.function.HelperFuncNode;
import com.jordanbunke.delta_time.scripting.ast.nodes.statement.StatementNode;
import com.jordanbunke.delta_time.scripting.ast.symbol_table.SymbolTable;
import com.jordanbunke.delta_time.scripting.util.FuncHelper;
import com.jordanbunke.delta_time.scripting.util.TextPosition;

public final class PassesCaseNode extends CaseNode {
    final ExpressionNode predicate;

    public PassesCaseNode(
            final TextPosition position,
            final ExpressionNode predicate, final StatementNode executionBody
    ) {
        super(position, executionBody);

        this.predicate = predicate;
    }

    @Override
    public void semanticErrorCheck(final SymbolTable symbolTable) {
        predicate.semanticErrorCheck(symbolTable);
        super.semanticErrorCheck(symbolTable);
    }

    @Override
    boolean test(final ExpressionNode control, final SymbolTable symbolTable) {
        // TODO - lambdas may require an abstraction of HelperFuncNode
        final HelperFuncNode p = (HelperFuncNode) predicate.evaluate(symbolTable);

        if (p == null)
            return false;

        return (Boolean) FuncHelper.evaluate(p,
                new ExpressionNode[] { control }, symbolTable);
    }

    @Override
    public String toString() {
        return "passes " + predicate + super.toString();
    }
}
