package com.jordanbunke.delta_time.utility.math;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public final class SetOps {
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

    public static <E> Set<E> xor(final Set<E> a, final Set<E> b) {
        return union(difference(a, b), difference(b, a));
    }
}
