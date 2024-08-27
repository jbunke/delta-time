package com.jordanbunke.delta_time.scripting.ast.nodes.statement.function;

import com.jordanbunke.delta_time.scripting.ast.nodes.expression.ExpressionNode;
import com.jordanbunke.delta_time.scripting.ast.nodes.function.HelperFuncNode;
import com.jordanbunke.delta_time.scripting.ast.nodes.statement.StatementNode;
import com.jordanbunke.delta_time.scripting.ast.nodes.types.TypeNode;
import com.jordanbunke.delta_time.scripting.ast.symbol_table.SymbolTable;
import com.jordanbunke.delta_time.scripting.util.*;

import java.util.Arrays;

public final class FuncExecuteNode extends StatementNode implements IHookable {
    private final ExpressionNode[] args;
    private final String name;

    public FuncExecuteNode(
            final TextPosition position,
            final String name, final ExpressionNode[] args
    ) {
        super(position);

        this.name = name;
        this.args = args;
    }

    @Override
    public void semanticErrorCheck(final SymbolTable symbolTable) {
        for (ExpressionNode arg : args)
            arg.semanticErrorCheck(symbolTable);

        final HelperFuncNode func = FunctionHook.getFunc(symbolTable, this);

        if (func == null)
            return;

        final TypeNode[] argTypes = Arrays.stream(args)
                .map(a -> a.getType(symbolTable))
                .toArray(TypeNode[]::new);

        if (!func.paramsMatch(argTypes))
            ScriptErrorLog.fireError(
                    ScriptErrorLog.Message.ARGS_SIGNATURE_MISMATCH,
                    getPosition(), name);
    }

    @Override
    public FuncControlFlow execute(final SymbolTable symbolTable) {
        final HelperFuncNode func = FunctionHook.getFunc(symbolTable, this);
        assert func != null;

        FuncHelper.execute(func, args, symbolTable);

        return FuncControlFlow.cont();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        final String contents = args.length == 1
                ? args[0].toString()
                : Arrays.stream(args)
                .map(ExpressionNode::toString)
                .reduce((a, b) -> a + ", " + b).orElse("");

        return name + "(" + contents + ");";
    }
}
