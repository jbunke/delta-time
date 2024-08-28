package com.jordanbunke.delta_time.scripting.ast.collection;

import com.jordanbunke.delta_time.scripting.ast.symbol_table.Variable;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public final class ScriptList implements ScriptCollection {
    private final List<Object> structure;

    public ScriptList() {
        structure = new ArrayList<>();
    }

    public ScriptList(final Stream<Object> elements) {
        this();
        elements.forEach(structure::add);
    }

    @Override
    public void add(final int index, final Object element) {
        structure.add(index, element);
    }

    @Override
    public void add(final Object element) {
        structure.add(element);
    }

    @Override
    public void remove(final int index) {
        structure.remove(index);
    }

    @Override
    public boolean contains(final Object element) {
        return structure.contains(element);
    }

    @Override
    public Object get(final int index) {
        return structure.get(index);
    }

    @Override
    public void set(final int index, final Object element) {
        structure.set(index, element);
    }

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
        if (!(o instanceof ScriptList l))
            return false;

        if (size() != l.size())
            return false;

        for (int i = 0; i < size(); i++)
            if (!get(i).equals(l.get(i)))
                return false;

        return true;
    }

    @Override
    public String toString() {
        return switch (structure.size()) {
            case 0 -> "<>";
            case 1 -> "<" + structure.get(0) + ">";
            default -> "<" + structure.stream()
                    .map(Variable::valueText)
                    .reduce((a, b) -> a + ", " + b).orElse("") + ">";
        };
    }
}
