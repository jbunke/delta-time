package com.jordanbunke.delta_time.scripting.util;

import com.jordanbunke.delta_time.scripting.ast.nodes.types.TypeNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class ScriptErrorLog {
    private static final List<Error> errors;

    static {
        errors = new ArrayList<>();
    }

    private static void fireError(
            final Error.Type type, final TextPosition position,
            final String message
    ) {
        errors.add(new Error(type, position, message));
    }

    public static void syntaxError(
            final TextPosition position, final String message
    ) {
        fireError(Error.Type.SYNTAX, position, message);
    }

    public static void semanticError(
            final TextPosition position, final String message
    ) {
        fireError(Error.Type.SEMANTIC, position, message);
    }

    public static void runtimeError(
            final TextPosition position, final String message
    ) {
        fireError(Error.Type.RUNTIME, position, message);
    }

    public static String expectedNumberButGot(final TypeNode actual) {
        return expectedButGot(
                // use instead of TypeNode.numTypes().toArray(TypeNode[]::new) for consistent ordering
                TypeUtils.options(TypeNode.getInt(), TypeNode.getFloat()),
                actual);
    }

    public static String expectedButGot(
            final TypeNode expected, final TypeNode actual
    ) {
        return expectedButGot(String.valueOf(expected), String.valueOf(actual));
    }

    public static String expectedButGot(
            final TypeNode[] expectedOptions, final TypeNode actual
    ) {
        final String expected = Arrays.stream(expectedOptions)
                .map(String::valueOf)
                .reduce((a, b) -> a + "\", \"" + b)
                .orElse("");

        return expectedButGot(expected, String.valueOf(actual));
    }

    public static String expectedButGot(
            final String expected, final String actual
    ) {
        return "expected \"" + expected + "\" but got \"" + actual + "\"";
    }

    public static String expected(final TypeNode expected) {
        return "expected \"" + expected + "\"";
    }

    public static String typeMismatch(
            final String offenderDescription,
            final String specificationDescription
    ) {
        return offenderDescription + " does not match " +
                specificationDescription;
    }

    public static String typeMismatch(
            final String offenderDescription,
            final String specificationDescription,
            final TypeNode expected, final TypeNode actual
    ) {
        return typeMismatch(offenderDescription, specificationDescription) +
                ": " + expectedButGot(expected, actual);
    }

    public static String notBool(
            final String specificationDescription, final TypeNode actual
    ) {
        return specificationDescription + " is not a logical expression: " +
                expectedButGot(TypeNode.getBool(), actual);
    }

    public static String unexpectedNumberOfArgs(
            final int expected, final int actual
    ) {
        return "Attempting to pass " + actual +
                " arguments to a function that expects " + expected;
    }

    public static boolean hasNoErrors() {
        return errors.isEmpty();
    }

    public static Error[] getErrors() {
        return errors.toArray(Error[]::new);
    }

    public static void clearErrors() {
        errors.clear();
    }
}
