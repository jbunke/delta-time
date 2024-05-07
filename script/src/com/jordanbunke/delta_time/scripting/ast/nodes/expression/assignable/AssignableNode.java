package com.jordanbunke.delta_time.scripting.ast.nodes.expression.assignable;

import com.jordanbunke.delta_time.scripting.ast.nodes.expression.ExpressionNode;
import com.jordanbunke.delta_time.scripting.ast.symbol_table.SymbolTable;
import com.jordanbunke.delta_time.scripting.util.ScriptErrorLog;
import com.jordanbunke.delta_time.scripting.util.TextPosition;

public abstract class AssignableNode extends ExpressionNode {
    private final String name;

    public AssignableNode(
            final TextPosition position,
            final String name
    ) {
        super(position);

        this.name = name;
    }

    @Override
    public void semanticErrorCheck(final SymbolTable symbolTable) {
        if (symbolTable.get(name) == null)
            ScriptErrorLog.fireError(
                    ScriptErrorLog.Message.UNDEFINED_VAR,
                    getPosition(), name);
    }

    public String getName() {
        return name;
    }

    public abstract void update(
            final SymbolTable symbolTable, final Object value);
}
