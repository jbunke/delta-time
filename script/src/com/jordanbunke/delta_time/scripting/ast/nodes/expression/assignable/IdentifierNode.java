package com.jordanbunke.delta_time.scripting.ast.nodes.expression.assignable;

import com.jordanbunke.delta_time.scripting.ast.nodes.types.TypeNode;
import com.jordanbunke.delta_time.scripting.ast.symbol_table.SymbolTable;
import com.jordanbunke.delta_time.scripting.ast.symbol_table.Variable;
import com.jordanbunke.delta_time.scripting.util.ScriptErrorLog;
import com.jordanbunke.delta_time.scripting.util.TextPosition;

public final class IdentifierNode extends AssignableNode {
    private static final String REPLACER = "_";

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
        final Variable var = get(symbolTable);
        assert var != null;

        final Object value = var.get();

        if (value == null)
            ScriptErrorLog.runtimeError(getPosition(),
                    "Attempted to use the variable \"" + this +
                            "\" before it was initialized");

        return value;
    }

    @Override
    public TypeNode getType(final SymbolTable symbolTable) {
        final Variable var = get(symbolTable);

        // this if statement should never pass; consider refactoring
        if (var == null) {
            ScriptErrorLog.semanticError(getPosition(), "Variable \"" + this +
                    "\" is referenced in a scope where it is not defined");
            return null;
        }

        return var.getType();
    }

    @Override
    public void semanticErrorCheck(final SymbolTable symbolTable) {
        if (get(symbolTable) == null)
            ScriptErrorLog.semanticError(getPosition(), "Variable \"" + this +
                    "\" is referenced in a scope where it is not defined");
    }

    private Variable get(final SymbolTable symbolTable) {
        final String name = getName();
        return name.equals(REPLACER)
                ? symbolTable.getScopeVar()
                : symbolTable.get(name);
    }

    @Override
    public String toString() {
        return getName();
    }
}
