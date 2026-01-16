package com.jordanbunke.delta_time.scripting.util;

import com.jordanbunke.delta_time.scripting.ast.nodes.expression.ExpressionNode;
import com.jordanbunke.delta_time.scripting.ast.nodes.types.TypeNode;
import com.jordanbunke.delta_time.scripting.ast.symbol_table.SymbolTable;

import static com.jordanbunke.delta_time.scripting.util.TypeUtils.*;
import static com.jordanbunke.delta_time.scripting.util.ScriptErrorLog.*;

public record Receiver(ExpressionNode receiver, TypeNode[] typeOptions) {
    public Receiver(final ExpressionNode receiver, final TypeNode only) {
        this(receiver, options(only));
    }

    public Object evaluate(final SymbolTable symbolTable) {
        return receiver.evaluate(symbolTable);
    }

    public TypeNode getType(final SymbolTable symbolTable) {
        return receiver.getType(symbolTable);
    }

    public void semanticErrorCheck(final SymbolTable symbolTable) {
        receiver.semanticErrorCheck(symbolTable);

        final TypeNode type = receiver.getType(symbolTable);

        if (!contains(typeOptions, type))
            semanticError(receiver.getPosition(),
                    "Receiver expression is of an invalid type: " +
                            expectedButGot(typeOptions, type));
    }

    @Override
    public String toString() {
        return receiver.toString() + ".";
    }
}
