package com.jordanbunke.delta_time.scripting.ast.nodes.statement.assignment;

import com.jordanbunke.delta_time.scripting.ast.nodes.expression.assignable.AssignableNode;
import com.jordanbunke.delta_time.scripting.ast.nodes.expression.assignable.IdentifierNode;
import com.jordanbunke.delta_time.scripting.ast.nodes.statement.StatementNode;
import com.jordanbunke.delta_time.scripting.ast.symbol_table.SymbolTable;
import com.jordanbunke.delta_time.scripting.ast.symbol_table.Variable;
import com.jordanbunke.delta_time.scripting.util.ScriptErrorLog;
import com.jordanbunke.delta_time.scripting.util.TextPosition;

public abstract class AssignmentNode extends StatementNode {
    private final AssignableNode assignable;

    public AssignmentNode(
            final TextPosition position, final AssignableNode assignable
    ) {
        super(position);

        this.assignable = assignable;
    }

    public AssignableNode getAssignable() {
        return assignable;
    }

    @Override
    public void semanticErrorCheck(final SymbolTable symbolTable) {
        assignable.semanticErrorCheck(symbolTable);

        final Variable var = symbolTable.get(assignable.getName());

        if (assignable instanceof IdentifierNode &&
                var != null && !var.isMutable())
            ScriptErrorLog.fireError(
                    ScriptErrorLog.Message.REASSIGN_FINAL,
                    assignable.getPosition(), assignable.getName());
    }
}
