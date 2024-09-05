package com.jordanbunke.delta_time.scripting.util;

import com.jordanbunke.delta_time.scripting.ast.nodes.expression.ExpressionNode;
import com.jordanbunke.delta_time.scripting.ast.nodes.function.ChildFuncNode;
import com.jordanbunke.delta_time.scripting.ast.nodes.function.HelperFuncNode;
import com.jordanbunke.delta_time.scripting.ast.symbol_table.SymbolTable;

import java.util.Arrays;

public final class FuncHelper {
    public static Object evaluate(
            final ChildFuncNode func,
            final ExpressionNode[] args,
            final SymbolTable symbolTable
    ) {
        final SymbolTable funcTable = func instanceof HelperFuncNode
                ? symbolTable.getRoot().getChild(func)
                : new SymbolTable(func, symbolTable);
        final Object[] argVals = Arrays.stream(args)
                .map(a -> a.evaluate(symbolTable)).toArray(Object[]::new);

        return func.execute(funcTable, argVals);
    }

    public static void execute(
            final ChildFuncNode func,
            final ExpressionNode[] args,
            final SymbolTable symbolTable
    ) {
        evaluate(func, args, symbolTable);
    }
}
