package com.jordanbunke.delta_time.scripting.ast.collection;

import java.util.stream.Stream;

public interface ScriptCollection {
    void add(final int index, final Object element);
    void add(final Object element);
    void removeAt(final int index);
    void remove(final Object element);
    boolean contains(final Object element);
    Object get(final int index);
    void set(final int index, final Object element);
    int size();
    Stream<Object> stream();
}
