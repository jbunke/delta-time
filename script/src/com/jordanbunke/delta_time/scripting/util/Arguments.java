package com.jordanbunke.delta_time.scripting.util;

import com.jordanbunke.delta_time.scripting.ast.nodes.expression.ExpressionNode;
import com.jordanbunke.delta_time.scripting.ast.nodes.types.TypeNode;
import com.jordanbunke.delta_time.scripting.ast.symbol_table.SymbolTable;

import java.util.Arrays;

public record Arguments(ExpressionNode[] args, TypeNode[]... expectedArgs) {

    public static TypeNode[][] exact(final TypeNode... expectedArgs) {
        return Arrays.stream(expectedArgs)
                .map(ea -> new TypeNode[] { ea })
                .toArray(TypeNode[][]::new);
    }

    public Object[] getValues(final SymbolTable symbolTable) {
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

    private boolean contains(final TypeNode[] expected, final TypeNode actual) {
        for (TypeNode option : expected)
            if (option.equals(actual))
                return true;

        return false;
    }

    private String expectedString(final TypeNode[] expected) {
        if (expected.length == 1) return expected[0].toString();

        return Arrays.stream(expected).map(TypeNode::toString)
                .reduce((a, b) -> a + "\" or \"").orElse("");
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
