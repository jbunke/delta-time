package com.jordanbunke.delta_time.scripting.ast.collection;

import com.jordanbunke.delta_time.scripting.ast.symbol_table.Variable;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Stream;

public final class ScriptSet implements ScriptCollection {
    private final List<Object> structure;

    public ScriptSet() {
        structure = new ArrayList<>();
    }

    public ScriptSet(final Stream<Object> elements) {
        this();
        elements.forEach(structure::add);
    }

    @Override
    public void add(final int index, final Object element) {}

    @Override
    public void add(final Object element) {
        if (!contains(element))
            structure.add(element);
    }

    @Override
    public void removeAt(final int index) {}

    @Override
    public void remove(final Object element) {
        structure.remove(element);
    }

    @Override
    public boolean contains(final Object element) {
        return structure.contains(element);
    }

    @Override
    public Object get(int index) {
        return structure.get(index);
    }

    @Override
    public void set(int index, Object element) {}

    @Override
    public int size() {
        return structure.size();
    }

    @Override
    public Stream<Object> stream() {
        return structure.stream();
    }

    @Override
    public boolean equals(final Object o) {
        if (!(o instanceof ScriptSet s))
            return false;

        final HashSet<Object>
                a = new HashSet<>(structure),
                b = new HashSet<>(s.structure);
        return a.containsAll(b) && b.containsAll(a);
    }

    @Override
    public String toString() {
        final List<String> strings = structure.stream()
                .map(Variable::valueText).sorted().toList();

        return switch (structure.size()) {
            case 0 -> "{}";
            case 1 -> "{" + strings.get(0) + "}";
            default -> "{" + strings.stream()
                    .reduce((a, b) -> a + ", " + b).orElse("") + "}";
        };
    }
}
