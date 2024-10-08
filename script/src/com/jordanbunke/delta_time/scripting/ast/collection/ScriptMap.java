package com.jordanbunke.delta_time.scripting.ast.collection;

import com.jordanbunke.delta_time.scripting.ast.symbol_table.Variable;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public final class ScriptMap extends HashMap<Object, Object> {
    @Override
    public boolean equals(final Object o) {
        if (!(o instanceof ScriptMap m))
            return false;

        final Set<Object> keysA = keySet(), keysB = m.keySet();
        if (!keysA.containsAll(keysB) || !keysB.containsAll(keysA))
            return false;

        for (Object key : keysA)
            if (!get(key).equals(m.get(key)))
                return false;

        return true;
    }

    @Override
    public String toString() {
        final List<Object> keys = keySet().stream()
                .sorted(Comparator.comparing(Variable::valueText)).toList();

        return switch (keys.size()) {
            case 0 -> "{:}";
            case 1 -> "{ " + Variable.valueText(keys.get(0)) +
                    ":" + Variable.valueText(get(keys.get(0))) + " }";
            default -> "{ " + keys.stream()
                    .map(k -> Variable.valueText(k) + ":" +
                            Variable.valueText(get(k)))
                    .reduce((a, b) -> a + ", " + b).orElse("") + " }";
        };
    }
}
