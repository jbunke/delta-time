package com.jordanbunke.delta_time.scripting.ast.nodes.expression.function;

import com.jordanbunke.delta_time.scripting.ast.nodes.expression.ExpressionNode;
import com.jordanbunke.delta_time.scripting.ast.nodes.function.HelperFuncNode;
import com.jordanbunke.delta_time.scripting.ast.nodes.types.TypeNode;
import com.jordanbunke.delta_time.scripting.ast.symbol_table.SymbolTable;
import com.jordanbunke.delta_time.scripting.util.*;

import java.util.Arrays;

public final class FuncCallNode extends ExpressionNode implements IHookable {
    private final ExpressionNode[] args;
    private final String name;

    public FuncCallNode(
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
            // TODO: improve error reporting with unique messages for length mismatch vs. type mismatch
            ScriptErrorLog.semanticError(getPosition(),
                    "Attempting to call the function \"" + name +
                            "\" with a set of arguments that do not match its signature");

        if (func.getReturnType() == null)
            ScriptErrorLog.semanticError(getPosition(),
                    "The function \"" + name +
                            "\" is void; it cannot be invoked as an expression");
    }

    @Override
    public Object evaluate(final SymbolTable symbolTable) {
        final HelperFuncNode func = FunctionHook.getFunc(symbolTable, this);
        assert func != null;

        return FuncHelper.evaluate(func, args, symbolTable);
    }

    @Override
    public TypeNode getType(final SymbolTable symbolTable) {
        final HelperFuncNode func = FunctionHook.getFunc(symbolTable, this);

        return func == null ? null : func.getReturnType();
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

        return name + "(" + contents + ")";
    }
}
