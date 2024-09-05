package com.jordanbunke.delta_time.scripting.ast.nodes.statement.control_flow;

import com.jordanbunke.delta_time.scripting.ast.nodes.expression.ExpressionNode;
import com.jordanbunke.delta_time.scripting.ast.nodes.function.ChildFuncNode;
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
        final ChildFuncNode p = (ChildFuncNode) predicate.evaluate(symbolTable);

        if (p != null)
            return (Boolean) FuncHelper.evaluate(p,
                    new ExpressionNode[] { control }, symbolTable);

        return false;
    }

    @Override
    public String toString() {
        return "passes " + predicate + super.toString();
    }
}
