package com.jordanbunke.delta_time.scripting.util;

import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

@FunctionalInterface
public interface FlexParamFunc<R> {
    R apply(Object... args);

    static <R> FlexParamFunc<R> zero(final Supplier<R> func) {
        return args -> func.get();
    }

    static <A, R> FlexParamFunc<R> one(final Function<A, R> func, Class<A> c) {
        return args -> {
            if (args.length == 0)
                return null;

            final Object a = args[0];

            if (c.isInstance(a))
                return func.apply(c.cast(a));

            return null;
        };
    }

    static <A, B, R> FlexParamFunc<R> two(
            final BiFunction<A, B, R> func, Class<A> ca, final Class<B> cb
    ) {
        return args -> {
            if (args.length < 2)
                return null;

            final Object a = args[0], b = args[1];

            if (ca.isInstance(a) && cb.isInstance(b))
                return func.apply(ca.cast(a), cb.cast(b));

            return null;
        };
    }
}
