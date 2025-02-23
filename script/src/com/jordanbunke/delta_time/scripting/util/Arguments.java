package com.jordanbunke.delta_time.scripting.util;

import com.jordanbunke.delta_time.scripting.ast.nodes.expression.ExpressionNode;
import com.jordanbunke.delta_time.scripting.ast.nodes.types.TypeNode;
import com.jordanbunke.delta_time.scripting.ast.symbol_table.SymbolTable;

import java.util.Arrays;

import static com.jordanbunke.delta_time.scripting.util.TypeUtils.*;

public record Arguments(ExpressionNode[] args, TypeNode[]... expectedArgs) {
    public static Arguments none() {
        return new Arguments(argsOf());
    }

    public static ExpressionNode[] argsOf(final ExpressionNode... args) {
        return args;
    }

    public Object[] evaluate(final SymbolTable symbolTable) {
        return Arrays.stream(args)
                .map(a -> a.evaluate(symbolTable))
                .toArray(Object[]::new);
    }

    public void semanticErrorCheck(
            final SymbolTable symbolTable, final TextPosition position
    ) {
        Arrays.stream(args).forEach(arg -> arg.semanticErrorCheck(symbolTable));

        final TypeNode[] argTypes = Arrays.stream(args)
                .map(a -> a.getType(symbolTable))
                .toArray(TypeNode[]::new);

        if (argTypes.length != expectedArgs.length) {
            ScriptErrorLog.fireError(
                    ScriptErrorLog.Message.CUSTOM_CT,
                    args.length > 0 ? args[0].getPosition() : position,
                    "Passing " + args.length +
                            " arguments into a function expecting " +
                            expectedArgs.length + " arguments");
            return;
        }

        for (int i = 0; i < expectedArgs.length; i++) {
            final TypeNode[] expected = expectedArgs[i];
            final TypeNode actual = argTypes[i];

            if (!contains(expected, actual))
                ScriptErrorLog.fireError(ScriptErrorLog.Message.ARG_NOT_TYPE,
                        args[i].getPosition(), "function",
                        expectedString(expected), actual.toString());
        }
    }

    public ExpressionNode get(final int index) {
        return args[index];
    }

    @Override
    public String toString() {
        return "(" + switch (args.length) {
            case 0 -> "";
            case 1 -> args[0].toString();
            default -> Arrays.stream(args).map(ExpressionNode::toString)
                    .reduce((a, b) -> a + ", " + b).orElse("");
        } + ")";
    }
}
