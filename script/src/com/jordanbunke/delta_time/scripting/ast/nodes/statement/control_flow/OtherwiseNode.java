package com.jordanbunke.delta_time.scripting.ast.nodes.statement.control_flow;

import com.jordanbunke.delta_time.scripting.ast.nodes.expression.ExpressionNode;
import com.jordanbunke.delta_time.scripting.ast.nodes.statement.StatementNode;
import com.jordanbunke.delta_time.scripting.ast.symbol_table.SymbolTable;
import com.jordanbunke.delta_time.scripting.util.TextPosition;

public final class OtherwiseNode extends CaseNode {
    public OtherwiseNode(
            final TextPosition position, final StatementNode executionBody
    ) {
        super(position, executionBody);
    }

    @Override
    boolean test(final ExpressionNode control, final SymbolTable symbolTable) {
        return true;
    }

    @Override
    public String toString() {
        return "otherwise" + super.toString();
    }
}
