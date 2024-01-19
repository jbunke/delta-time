package com.jordanbunke.delta_time.utility;

import java.util.HashSet;
import java.util.Set;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;

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

    public static <E> Set<E> union(final Set<E> a, final Set<E> b) {
        final Set<E> s = new HashSet<>(a);
        s.addAll(b);
        return s;
    }

    public static <E> Set<E> intersection(final Set<E> a, final Set<E> b) {
        return a.stream().filter(b::contains).collect(Collectors.toSet());
    }

    public static <E> Set<E> difference(final Set<E> a, final Set<E> b) {
        return a.stream().filter(element -> !b.contains(element))
                .collect(Collectors.toSet());
    }
}
