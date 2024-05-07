package com.jordanbunke.delta_time.scripting.ast.nodes.expression.assignable;

import com.jordanbunke.delta_time.scripting.ast.nodes.types.TypeNode;
import com.jordanbunke.delta_time.scripting.ast.symbol_table.SymbolTable;
import com.jordanbunke.delta_time.scripting.ast.symbol_table.Variable;
import com.jordanbunke.delta_time.scripting.util.ScriptErrorLog;
import com.jordanbunke.delta_time.scripting.util.TextPosition;

public final class IdentifierNode extends AssignableNode {
    public IdentifierNode(
            final TextPosition position, final String name
    ) {
        super(position, name);
    }

    @Override
    public void update(
            final SymbolTable symbolTable,
            final Object value
    ) {
        symbolTable.update(getName(), value);
    }

    @Override
    public Object evaluate(final SymbolTable symbolTable) {
        final Variable var = symbolTable.get(getName());
        assert var != null;

        final Object value = var.get();

        if (value == null)
            ScriptErrorLog.fireError(
                    ScriptErrorLog.Message.UNINITIALIZED_VAR,
                    getPosition(), getName());

        return value;
    }

    @Override
    public TypeNode getType(final SymbolTable symbolTable) {
        final Variable var = symbolTable.get(getName());

        // this if statement should never pass; consider refactoring
        if (var == null) {
            ScriptErrorLog.fireError(
                    ScriptErrorLog.Message.UNDEFINED_VAR,
                    getPosition(), getName());
            return null;
        }

        return var.getType();
    }

    @Override
    public String toString() {
        return getName();
    }
}
