package com.jordanbunke.delta_time.scripting.ast.collection;

import java.util.Arrays;
import java.util.stream.Stream;

public final class ScriptArray implements ScriptCollection {
    private final Object[] structure;

    public ScriptArray(final Stream<Object> elements) {
        structure = elements.toArray(Object[]::new);
    }

    public ScriptArray(final int length) {
        structure = new Object[length];
    }

    public static ScriptArray of(final Object... elements) {
        return new ScriptArray(Arrays.stream(elements));
    }

    @Override
    public void add(final int index, final Object element) {}

    @Override
    public void add(final Object element) {}

    @Override
    public void remove(final int index) {}

    @Override
    public boolean contains(final Object element) {
        for (int i = 0; i < size(); i++)
            if (structure[i].equals(element))
                return true;

        return false;
    }

    @Override
    public Object get(final int index) {
        return structure[index];
    }

    @Override
    public void set(final int index, final Object element) {
        structure[index] = element;
    }

    @Override
    public int size() {
        return structure.length;
    }

    @Override
    public Stream<Object> stream() {
        return Arrays.stream(structure);
    }

    @Override
    public boolean equals(final Object o) {
        if (!(o instanceof ScriptArray a))
            return false;

        if (size() != a.size())
            return false;

        for (int i = 0; i < size(); i++)
            if (!get(i).equals(a.get(i)))
                return false;

        return true;
    }

    @Override
    public String toString() {
        return switch (structure.length) {
            case 0 -> "[]";
            case 1 -> "[" + structure[0] + "]";
            default -> "[" + Arrays.stream(structure)
                    .map(Object::toString)
                    .reduce((a, b) -> a + ", " + b).orElse("") + "]";
        };
    }
}
