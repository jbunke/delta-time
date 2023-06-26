package com.jordanbunke.jbjgl.utility;

import java.util.function.BiFunction;
import java.util.function.Function;

public class MathPlus {
    public static double minMagnitude(final double... values) {
        return findBestDouble(0d, Double.MAX_VALUE, Math::abs,
                (x, y) -> x < y, values);
    }

    public static double maxMagnitude(final double... values) {
        return findBestDouble(Double.MAX_VALUE, 0d, Math::abs,
                (x, y) -> x > y, values);
    }

    public static double min(final double... values) {
        return findBestDouble(0d, Double.MAX_VALUE, x -> x,
                (x, y) -> x < y, values);
    }

    public static double max(final double... values) {
        return findBestDouble(Double.MAX_VALUE, -Double.MAX_VALUE, x -> x,
                (x, y) -> x > y, values);
    }

    public static <T> double findBestDouble(
            final double defaultReturn, final T worstCase,
            final Function<Double, T> function,
            final BiFunction<T, T, Boolean> operator,
            final double... values
    ) {
        if (values.length == 0)
            return defaultReturn;

        T best = worstCase;
        double pole = values[0];

        for (double value : values) {
            final T candidate = function.apply(value);
            if (operator.apply(candidate, best)) {
                best = candidate;
                pole = value;
            }
        }

        return pole;
    }

    @SafeVarargs
    public static <R, T> R findBest(
            final R defaultReturn, final T worstCase,
            final Function<R, T> function,
            final BiFunction<T, T, Boolean> operator,
            final R... values
    ) {
        if (values.length == 0)
            return defaultReturn;

        T best = worstCase;
        R pole = values[0];

        for (R value : values) {
            final T candidate = function.apply(value);
            if (operator.apply(candidate, best)) {
                best = candidate;
                pole = value;
            }
        }

        return pole;
    }

    public static double minMagnitude(final double a, final double b) {
        return Math.abs(a) < Math.abs(b) ? a : b;
    }

    public static double maxMagnitude(final double a, final double b) {
        return Math.abs(a) > Math.abs(b) ? a : b;
    }
}
