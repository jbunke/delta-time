package com.jordanbunke.delta_time.scripting.ast.nodes.expression.collection_init;

import com.jordanbunke.delta_time.scripting.ast.collection.ScriptMap;
import com.jordanbunke.delta_time.scripting.ast.nodes.expression.ExpressionNode;
import com.jordanbunke.delta_time.scripting.ast.nodes.types.MapTypeNode;
import com.jordanbunke.delta_time.scripting.ast.nodes.types.TypeNode;
import com.jordanbunke.delta_time.scripting.ast.symbol_table.SymbolTable;
import com.jordanbunke.delta_time.scripting.util.CollectionHelper;
import com.jordanbunke.delta_time.scripting.util.TextPosition;

import java.util.Arrays;

public final class ExplicitMapInitNode extends ExpressionNode {
    private final ExpressionNode[] keys, vals;

    public ExplicitMapInitNode(
            final TextPosition position,
            final ExpressionNode[] keys,
            final ExpressionNode[] vals
    ) {
        super(position);

        this.keys = keys;
        this.vals = vals;
    }

    @Override
    public void semanticErrorCheck(final SymbolTable symbolTable) {
        for (ExpressionNode key : keys)
            key.semanticErrorCheck(symbolTable);
        for (ExpressionNode val : vals)
            val.semanticErrorCheck(symbolTable);

        CollectionHelper.checkInternalTypeConsistency(
                keys, symbolTable, "map key");
        CollectionHelper.checkInternalTypeConsistency(
                vals, symbolTable, "map value");
    }

    @Override
    public ScriptMap evaluate(final SymbolTable symbolTable) {
        final ScriptMap map = new ScriptMap();

        final int n = keys.length;

        for (int i = 0; i < n; i++) {
            final Object k = keys[i].evaluate(symbolTable),
                    v = vals[i].evaluate(symbolTable);

            map.put(k, v);
        }

        return map;
    }

    public ExpressionNode[] getKeys() {
        return keys;
    }

    public ExpressionNode[] getVals() {
        return vals;
    }

    @Override
    public TypeNode getType(final SymbolTable symbolTable) {
        final TypeNode
                kType = CollectionHelper.getConcreteType(keys, symbolTable),
                vType = CollectionHelper.getConcreteType(vals, symbolTable);

        return new MapTypeNode(kType, vType);
    }

    @Override
    public String toString() {
        final String[] pairs = new String[keys.length];

        for (int i = 0; i < pairs.length; i++)
            pairs[i] = keys[i] + " : " + vals[i];

        final String contents = pairs.length == 1 ? pairs[0]
                : Arrays.stream(pairs)
                .reduce((a, b) -> a + ", " + b).orElse("");

        return "{ " + contents + " }";
    }
}
