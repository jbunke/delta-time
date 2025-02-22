package com.jordanbunke.delta_time.scripting.util;

import com.jordanbunke.delta_time.scripting.ast.nodes.expression.ExpressionNode;
import com.jordanbunke.delta_time.scripting.ast.nodes.types.TypeNode;
import com.jordanbunke.delta_time.scripting.ast.symbol_table.SymbolTable;

public record Receiver(ExpressionNode receiver, TypeNode expectedType) {
    public Object evaluate(final SymbolTable symbolTable) {
        return receiver.evaluate(symbolTable);
    }

    public void semanticErrorCheck(
            final SymbolTable symbolTable, final TextPosition position
    ) {
        receiver.semanticErrorCheck(symbolTable);

        final TypeNode type = receiver.getType(symbolTable);

        if (!type.equals(expectedType))
            ScriptErrorLog.fireError(ScriptErrorLog.Message.ARG_NOT_TYPE,
                    position, "receiver", expectedType.toString(),
                    type.toString());
    }

    @Override
    public String toString() {
        return receiver.toString() + ".";
    }
}
