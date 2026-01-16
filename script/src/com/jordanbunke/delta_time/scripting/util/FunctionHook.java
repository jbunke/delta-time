package com.jordanbunke.delta_time.scripting.util;

import com.jordanbunke.delta_time.scripting.ast.nodes.function.HelperFuncNode;
import com.jordanbunke.delta_time.scripting.ast.symbol_table.SymbolTable;
import com.jordanbunke.delta_time.scripting.ast.symbol_table.Variable;

public abstract class FunctionHook {
    public static HelperFuncNode getFunc(
            final SymbolTable symbolTable, final IHookable h
    ) {
        final Variable var = symbolTable.get(SymbolTable.funcWithName(h.getName()));

        if (var == null) {
            ScriptErrorLog.semanticError(h.getPosition(),
                    "Attempted to call function \"" + h.getName() +
                            "\" that is not defined in this script");

            return null;
        }

        return (HelperFuncNode) var.get();
    }
}
