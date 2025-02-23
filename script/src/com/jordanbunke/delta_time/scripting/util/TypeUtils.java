package com.jordanbunke.delta_time.scripting.util;

import com.jordanbunke.delta_time.scripting.ast.nodes.types.TypeNode;

import java.util.Arrays;

public final class TypeUtils {
    public static TypeNode[] options(final TypeNode... options) {
        return options;
    }

    public static TypeNode[][] expectExact(final TypeNode... expectedArgs) {
        return Arrays.stream(expectedArgs)
                .map(ea -> new TypeNode[] { ea })
                .toArray(TypeNode[][]::new);
    }

    public static boolean contains(final TypeNode[] expected, final TypeNode actual) {
        for (TypeNode option : expected)
            if (option.equals(actual))
                return true;

        return false;
    }

    public static String expectedString(final TypeNode[] expected) {
        if (expected.length == 1) return expected[0].toString();

        return Arrays.stream(expected).map(TypeNode::toString)
                .reduce((a, b) -> a + "\" or \"").orElse("");
    }
}
