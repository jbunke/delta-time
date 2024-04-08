package com.jordanbunke.delta_time.utility.math;

import java.util.Arrays;
import java.util.function.BiFunction;
import java.util.function.Function;

public final class MathPlus {
    public static double bounded(
            final double min, final double value, final double max
    ) {
        return Math.min(max, Math.max(value, min));
    }

    public static int bounded(
            final int min, final int value, final int max
    ) {
        return Math.min(max, Math.max(value, min));
    }

    public static double minMagnitude(final double... values) {
        return Arrays.stream(values)
                .reduce(Double.MAX_VALUE, MathPlus::minMagnitude);
    }

    public static double maxMagnitude(final double... values) {
        return Arrays.stream(values)
                .reduce(0d, MathPlus::maxMagnitude);
    }

    public static double min(final double... values) {
        return Arrays.stream(values).reduce(Double.MAX_VALUE, Math::min);
    }

    public static double max(final double... values) {
        return Arrays.stream(values).reduce(Double.MIN_VALUE, Math::max);
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
