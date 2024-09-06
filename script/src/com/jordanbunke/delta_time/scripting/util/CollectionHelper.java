package com.jordanbunke.delta_time.scripting.util;

import com.jordanbunke.delta_time.scripting.ast.nodes.expression.ExpressionNode;
import com.jordanbunke.delta_time.scripting.ast.nodes.types.BaseTypeNode;
import com.jordanbunke.delta_time.scripting.ast.nodes.types.TypeNode;
import com.jordanbunke.delta_time.scripting.ast.symbol_table.SymbolTable;

import java.util.Arrays;

public final class CollectionHelper {
    public static TypeNode getConcreteType(
            final ExpressionNode[] elements, final SymbolTable symbolTable
    ) {
        if (elements.length == 0)
            return TypeNode.wildcard();

        return Arrays.stream(elements)
                .map(e -> e.getType(symbolTable))
                .filter(t -> t instanceof BaseTypeNode st &&
                        !st.isWildcard())
                .reduce((a, b) -> a).orElse(
                        elements[0].getType(symbolTable));
    }

    public static void checkInternalTypeConsistency(
            final ExpressionNode[] elements, final SymbolTable symbolTable,
            final String initDescriptor
    ) {
        if (elements.length == 0)
            return;

        final TypeNode firstElemType = elements[0].getType(symbolTable);

        for (int i = 1; i < elements.length; i++) {
            final TypeNode type = elements[i].getType(symbolTable);

            if (!type.equals(firstElemType))
                ScriptErrorLog.fireError(
                        ScriptErrorLog.Message.INCONSISTENT_COL_TYPES,
                        elements[i].getPosition(),
                        String.valueOf(i), initDescriptor,
                        String.valueOf(firstElemType),
                        String.valueOf(type));
        }
    }
}
